/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aliyun.odps.mma.server.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aliyun.odps.Odps;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.account.AliyunAccount;
import com.aliyun.odps.mma.config.AbstractConfiguration;
import com.aliyun.odps.mma.config.JobConfiguration;
import com.aliyun.odps.mma.config.OdpsConfig;
import com.aliyun.odps.mma.util.HiveSqlUtils;
import com.aliyun.odps.mma.server.action.info.HiveSqlActionInfo;
import com.aliyun.odps.mma.meta.MetaSource.TableMetaModel;
import com.aliyun.odps.mma.server.config.MmaServerConfiguration;
import com.aliyun.odps.mma.server.resource.Resource;
import com.aliyun.odps.mma.server.task.Task;

public class HiveToMcTableDataTransmissionAction extends HiveSqlAction {

  private static final Logger LOG = LogManager.getLogger(HiveToMcTableDataTransmissionAction.class);

  private static final Map<String, String> DEFAULT_SETTINGS = new HashMap<>();
  static {
    // DO NOT CHANGE the following settings
    // Make sure the data transmission queries are not converted to FETCH tasks.
    DEFAULT_SETTINGS.put("hive.fetch.task.conversion", "none");
    // Make sure the data transmission queries are executed as MR tasks.
    DEFAULT_SETTINGS.put("hive.execution.engine", "mr");
    // Disable retry, which may result in data corruption.
    DEFAULT_SETTINGS.put("mapreduce.map.maxattempts", "0");
    // Disable speculative execution, which may result in data corruption.
    DEFAULT_SETTINGS.put("mapreduce.map.speculative", "false");

    // The following settings can be changed if necessary
    // Set the timeout of mapreduce task to 1 hour.
    DEFAULT_SETTINGS.put("mapreduce.task.timeout", "3600000");
    // Set the default max split size to 512 MB
    DEFAULT_SETTINGS.put("mapreduce.max.split.size", "512000000");
    // Uses 1 vcore
    DEFAULT_SETTINGS.put("mapreduce.map.cpu.vcores", "1");
    // Uses 4 GB memory
    DEFAULT_SETTINGS.put("mapreduce.map.memory.mb", "4096");
  }

  private String accessKeyId;
  private String accessKeySecret;
  private String executionProject;
  private String endpoint;
  private TableMetaModel hiveTableMetaModel;
  private TableMetaModel mcTableMetaModel;

  public HiveToMcTableDataTransmissionAction(
      String id,
      OdpsConfig odpsConfig,
      String jdbcUrl,
      String username,
      String password,
      TableMetaModel hiveTableMetaModel,
      TableMetaModel mcTableMetaModel,
      Task task,
      ActionExecutionContext actionExecutionContext) {
    super(id, jdbcUrl, username, password, task, actionExecutionContext);
    this.accessKeyId = odpsConfig.getAccessId();
    this.accessKeySecret = odpsConfig.getAccessKey();
    this.executionProject = odpsConfig.getProjectName();
    this.endpoint = odpsConfig.getEndpoint();
    this.hiveTableMetaModel = hiveTableMetaModel;
    this.mcTableMetaModel = mcTableMetaModel;

    // Set the number of data worker
    // Priority: job configuration -> MMA server configuration -> default value
    JobConfiguration config = actionExecutionContext.getConfig();
    MmaServerConfiguration mmaServerConfiguration = MmaServerConfiguration.getInstance();
    long numDataWorkerResource = Long.valueOf(
        config.getOrDefault(
            JobConfiguration.JOB_NUM_DATA_WORKER,
            mmaServerConfiguration.getOrDefault(
                AbstractConfiguration.JOB_NUM_DATA_WORKER,
                AbstractConfiguration.JOB_NUM_DATA_WORKER_DEFAULT_VALUE)
        )
    );

    resourceMap.put(Resource.DATA_WORKER, numDataWorkerResource);
  }

  @Override
  String getSql() throws OdpsException {
    return HiveSqlUtils.getUdtfSql(
        endpoint,
        generateBearerToken(),
        hiveTableMetaModel,
        mcTableMetaModel);
  }

  private String generateBearerToken() throws OdpsException {
    String policy = "{\n"
        + "    \"expires_in_hours\": 24,\n"
        + "    \"policy\": {\n"
        + "        \"Statement\": [{\n"
        + "            \"Action\": [\"odps:*\"],\n"
        + "            \"Effect\": \"Allow\",\n"
        + "            \"Resource\": \"acs:odps:*:projects/" + mcTableMetaModel.getDatabase()
        + "/tables/" + mcTableMetaModel.getTable() + "\"\n"
        + "        }],\n"
        + "        \"Version\": \"1\"\n"
        + "    }\n"
        + "}";
    Odps odps = new Odps(new AliyunAccount(accessKeyId, accessKeySecret));
    odps.setDefaultProject(executionProject);
    odps.setEndpoint(endpoint);
    return odps.projects().get().getSecurityManager().generateAuthorizationToken(policy, "Bearer");
  }

  @Override
  Map<String, String> getSettings() {
    // TODO:
    Map<String, String> settings = new HashMap<>(DEFAULT_SETTINGS);
    settings.put(
        "mapreduce.job.running.map.limit",
        Long.toString(resourceMap.get(Resource.DATA_WORKER)));
    return settings;
  }

  @Override
  public String getName() {
    return "Table data transmission";
  }

  @Override
  public List<List<Object>> getResult() {
    return ((HiveSqlActionInfo) actionInfo).getResult();
  }

  @Override
  void handleResult(List<List<Object>> result) {
    ((HiveSqlActionInfo) actionInfo).setResult(result);
  }
}
