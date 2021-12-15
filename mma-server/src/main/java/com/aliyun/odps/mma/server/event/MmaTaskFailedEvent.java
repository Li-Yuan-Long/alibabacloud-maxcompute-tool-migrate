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

package com.aliyun.odps.mma.server.event;

import java.util.List;
import java.util.Objects;

public class MmaTaskFailedEvent extends BaseMmaEvent {

  private String id;
  private List<String> actionIds;

  public MmaTaskFailedEvent(String id, List<String> actionIds) {
    this.id = Objects.requireNonNull(id);
    this.actionIds = actionIds;
  }

  @Override
  public MmaEventType getType() {
    return MmaEventType.TASK_FAILED;
  }

  @Override
  public String toString() {
    if (actionIds != null) {
      return String.format(
          "Task failed: %s (caused by %s)", id, String.join(", ", actionIds));
    } else {
      return String.format("Task failed: %s", id);
    }
  }
}
