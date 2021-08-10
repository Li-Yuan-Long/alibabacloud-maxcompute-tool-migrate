/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.aliyun.odps.mma.server.task;

//import java.util.Objects;
//
//import org.jgrapht.graph.DefaultEdge;
//import org.jgrapht.graph.DirectedAcyclicGraph;
//
//import com.aliyun.odps.datacarrier.taskscheduler.MmaConfig;
//import com.aliyun.odps.datacarrier.taskscheduler.MmaException;
//import com.aliyun.odps.datacarrier.taskscheduler.action.Action;
//import com.aliyun.odps.datacarrier.taskscheduler.meta.MetaSource;
//import com.aliyun.odps.datacarrier.taskscheduler.meta.MmaMetaManager;
//
//public class OdpsRestoreTablePrepareTask extends AbstractTask {
//  private final MetaSource.TableMetaModel tableMetaModel;
//
//  public OdpsRestoreTablePrepareTask(
//      String id,
//      String jobId,
//      MetaSource.TableMetaModel tableMetaModel,
//      DirectedAcyclicGraph<Action, DefaultEdge> dag,
//      MmaMetaManager mmaMetaManager) {
//    super(id, jobId, dag, mmaMetaManager);
//    this.tableMetaModel = Objects.requireNonNull(tableMetaModel);
//    this.actionExecutionContext.setTableMetaModel(tableMetaModel);
//  }
//
//  @Override
//  void updateMetadata() throws MmaException {
//    if (TaskProgress.PENDING.equals(progress)
//        || TaskProgress.RUNNING.equals(progress)
//        || TaskProgress.SUCCEEDED.equals(progress)) {
//      return;
//    }
//    MmaMetaManager.JobStatus status = MmaMetaManager.JobStatus.FAILED;
//    mmaMetaManager.updateStatus(
//        jobId,
//        MmaConfig.JobType.RESTORE.name(),
//        MmaConfig.ObjectType.TABLE.name(),
//        tableMetaModel.databaseName, tableMetaModel.tableName,
//        status);
//  }
//}
