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

package com.aliyun.odps.mma.io.converter;

import com.aliyun.odps.OdpsType;
import com.aliyun.odps.type.TypeInfo;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DateObjectInspector;

public class HiveDateObjectConverter extends AbstractHiveObjectConverter {

  @Override
  public Object convert(ObjectInspector objectInspector, Object o, TypeInfo odpsTypeInfo) {
    if (o == null) {
      return null;
    }

    DateObjectInspector dateObjectInspector = (DateObjectInspector) objectInspector;
    java.sql.Date value = dateObjectInspector.getPrimitiveJavaObject(o);
    if (OdpsType.STRING.equals(odpsTypeInfo.getOdpsType())) {
      return value.toString();
    } else if (OdpsType.DATETIME.equals(odpsTypeInfo.getOdpsType())) {
      return new java.sql.Date(value.getTime());
    } else {
      String msg = String.format("Unsupported implicit type conversion: from %s to %s",
                                 "Hive.Date",
                                 "ODPS." + odpsTypeInfo.getOdpsType());
      throw new IllegalArgumentException(msg);
    }
  }
}
