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

package com.aliyun.odps.mma.exception;

import java.util.List;

import com.aliyun.odps.mma.config.ObjectType;

public class MmaExceptionFactory {
  public static MmaException getFailedToCreateConnectionException(Throwable e) {
    return new MmaException("Failed to create connection", e);
  }

  public static MmaException getRunningJobExistsException(String db, String object) {
    String errorMsg = String.format("Running job exists, db: %s, object: %s", db, object);
    return new MmaException(errorMsg);
  }

  public static MmaException getFailedToAddMigrationJobException(
      String db, String tbl, Throwable e) {
    String errorMsg = String.format("Failed to add migration job, db: %s, tbl: %s", db, tbl);
    return new MmaException(errorMsg, e);
  }

  public static MmaException getFailedToAddBackupJobException(
      String db, String tbl, ObjectType type, Throwable e) {
    String errorMsg = String.format(
        "Failed to add backup job, db: %s, tbl: %s, type: %s", db, tbl, type.name());
    return new MmaException(errorMsg, e);
  }

  public static MmaException getFailedToRemoveMigrationJobException(
      String db, String tbl, Throwable e) {
    String errorMsg = String.format("Failed to remove migration job, db: %s, tbl: %s", db, tbl);
    return new MmaException(errorMsg, e);
  }

  public static MmaException getFailedToUpdateMigrationJobException(
      String db, String tbl, Throwable e) {
    String errorMsg = String.format("Failed to update migration job, db: %s, tbl: %s", db, tbl);
    return new MmaException(errorMsg, e);
  }

  public static MmaException getFailedToGetMigrationJobException(
      String db, String tbl, Throwable e) {
    String errorMsg = String.format("Failed to get migration job, db: %s, tbl: %s", db, tbl);
    return new MmaException(errorMsg, e);
  }

  public static MmaException getFailedToGetMigrationJobException(String db, String tbl) {
    String errorMsg = String.format("Failed to get migration job, db: %s, tbl: %s", db, tbl);
    return new MmaException(errorMsg);
  }

  public static MmaException getFailedToGetMigrationJobPtException(
      String db, String tbl, List<String> partitionValues) {
    String errorMsg = String.format(
        "Failed to get migration job, db: %s, tbl: %s, pt: %s", db, tbl, partitionValues);
    return new MmaException(errorMsg);
  }

  public static MmaException getFailedToListMigrationJobsException(Throwable e) {
    return new MmaException("Failed to list migration jobs", e);
  }

  public static MmaException getFailedToGetPendingJobsException(Throwable e) {
    return new MmaException("Failed to get pending jobs", e);
  }

  public static MmaException getMigrationJobNotExistedException(String db, String tbl) {
    String errorMsg = String.format("Migration job not existed, db: %s, tbl: %s", db, tbl);
    return new MmaException(errorMsg);
  }

  public static MmaException getMigrationJobPtNotExistedException(
      String db, String tbl, List<String> partitionValues) {
    String errorMsg = String.format(
        "Migration job partition not existed, db: %s, tbl: %s, pt: %s", db, tbl, partitionValues);
    return new MmaException(errorMsg);
  }

  public static MmaException getAttributeCannotBeNullOrEmptyException(String attributeName) {
    String errorMsg = String.format("%s cannot be null or empty", attributeName);
    return new MmaException(errorMsg);
  }
}
