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

//package com.aliyun.odps.mma.server.action;
//
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.aliyun.odps.datacarrier.taskscheduler.Constants;
//import com.aliyun.odps.datacarrier.taskscheduler.DataSource;
//import com.aliyun.odps.datacarrier.taskscheduler.GsonUtils;
//import com.aliyun.odps.datacarrier.taskscheduler.MmaConfig;
//import com.aliyun.odps.datacarrier.taskscheduler.MmaConfigUtils;
//import com.aliyun.odps.datacarrier.taskscheduler.MmaException;
//import com.aliyun.odps.datacarrier.taskscheduler.OssUtils;
//import com.aliyun.odps.datacarrier.taskscheduler.meta.MetaSource;
//import com.aliyun.odps.datacarrier.taskscheduler.meta.MetaSourceFactory;

//public class OdpsResetTableMetaModelAction extends DefaultAction {
//  private static final Logger LOG = LogManager.getLogger(OdpsResetTableMetaModelAction.class);
//
//  private String sourceDatabase;
//  private String sourceTable;
//  private MmaConfig.ObjectRestoreConfig restoreConfig;
//
//  public OdpsResetTableMetaModelAction(
//      String id,
//      String sourceDatabase,
//      String sourceTable,
//      MmaConfig.ObjectRestoreConfig restoreConfig) {
//    super(id);
//    this.sourceDatabase = sourceDatabase;
//    this.sourceTable = sourceTable;
//    this.restoreConfig = restoreConfig;
//  }
//
//  @Override
//  public Object call() throws MmaException {
//    try {
//      MetaSource metaSource = MetaSourceFactory.getOdpsMetaSource(restoreConfig.getOdpsConfig());
//
//      // TODO: this may not be necessary
//      MetaSource.TableMetaModel tableMetaModel =
//          metaSource.getTableMeta(sourceDatabase, sourceTable);
//      LOG.info("GetTableMeta {}.{}, partitions {}",
//               sourceDatabase, sourceTable, tableMetaModel.partitions.size());
//
//      MmaConfig.TableMigrationConfig config = new MmaConfig.TableMigrationConfig(
//          DataSource.ODPS,
//          sourceDatabase,
//          sourceTable,
//          restoreConfig.getDestinationDatabaseName(),
//          restoreConfig.getObjectName(),
//          MmaConfigUtils.DEFAULT_ADDITIONAL_TABLE_CONFIG);
//      config.apply(tableMetaModel);
//
//      String folder = MmaConfig.ObjectType.TABLE.equals(restoreConfig.getObjectType()) ?
//          Constants.EXPORT_TABLE_FOLDER : Constants.EXPORT_VIEW_FOLDER;
//      String ossFileName = OssUtils.getOssPathToExportObject(
//          restoreConfig.getBackupName(),
//          folder,
//          restoreConfig.getSourceDatabaseName(),
//          restoreConfig.getObjectName(),
//          Constants.EXPORT_COLUMN_TYPE_FILE_NAME);
//
//      String content = OssUtils.readFile(actionExecutionContext.getOssConfig(), ossFileName);
//      LOG.info("Meta file {}, content {}", ossFileName, content);
//
//      Map<String, String> columnTypes = GsonUtils.getFullConfigGson().fromJson(
//          content,
//          GsonUtils.STRING_TO_STRING_MAP_TYPE);
//
//      // TODO: this may not be necessary
//      for (MetaSource.ColumnMetaModel columnMetaModel : tableMetaModel.columns) {
//        if (columnTypes.containsKey(columnMetaModel.odpsColumnName)) {
//          columnMetaModel.odpsType = columnTypes.get(columnMetaModel.odpsColumnName);
//          continue;
//        }
//        throw new MmaException("RestoreConfig: " + MmaConfig.ObjectRestoreConfig.toJson(restoreConfig) + ", Column: " + columnMetaModel.odpsColumnName + " not found in exported column types");
//      }
//
//      // TODO: this may not be necessary
//      for (MetaSource.ColumnMetaModel columnMetaModel : tableMetaModel.partitionColumns) {
//        if (columnTypes.containsKey(columnMetaModel.odpsColumnName)) {
//          columnMetaModel.odpsType = columnTypes.get(columnMetaModel.odpsColumnName);
//          continue;
//        }
//        throw new MmaException("RestoreConfig: " + MmaConfig.ObjectRestoreConfig.toJson(restoreConfig) + ", PartitionColumn: " + columnMetaModel.odpsColumnName + " not found in exported column types");
//      }
//
//      this.actionExecutionContext.setTableMetaModel(tableMetaModel);
//      LOG.info("Reset table meta model for {}, source {}.{}, destination {}.{}, partition size {}",
//               id, tableMetaModel.databaseName, tableMetaModel.tableName, tableMetaModel.odpsProjectName, tableMetaModel.odpsTableName, tableMetaModel.partitions.size());
//    } catch (Exception e) {
//      LOG.error("Exception when reset table meta for task {}, table {}.{}, destination {}.{}", id, sourceDatabase, sourceTable, restoreConfig.getSourceDatabaseName(), restoreConfig.getObjectName(), e);
//      throw new MmaException("Reset table meta fail for " + id, e);
//    }
//
//    return null;
//  }
//
//  @Override
//  public String getName() {
//    return "Table metadata restoration";
//  }
//}
