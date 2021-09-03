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

DROP DATABASE IF EXISTS MMA_TEST CASCADE;
CREATE DATABASE MMA_TEST;

-- ************************** This table is used to populate data *********************************
CREATE TABLE MMA_TEST.DUMMY(T_TINYINT TINYINT);
INSERT INTO TABLE MMA_TEST.DUMMY VALUES (1);

-- ************************** For storage format tests ********************************************
DROP TABLE MMA_TEST.`TEST_TEXT_1x1K`;
CREATE TABLE MMA_TEST.`TEST_TEXT_1x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
STORED AS TEXTFILE;

DROP TABLE MMA_TEST.`TEST_TEXT_PARTITIONED_10x1K`;
CREATE TABLE MMA_TEST.`TEST_TEXT_PARTITIONED_10x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
PARTITIONED BY (
`P1` STRING,
`P2` BIGINT
)
STORED AS TEXTFILE;

DROP TABLE MMA_TEST.`TEST_ORC_1x1K`;
CREATE TABLE MMA_TEST.`TEST_ORC_1x1K`(
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
STORED AS ORC;

DROP TABLE MMA_TEST.`TEST_ORC_PARTITIONED_10x1K`;
CREATE TABLE MMA_TEST.`TEST_ORC_PARTITIONED_10x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
PARTITIONED BY (
`P1` STRING,
`P2` BIGINT
)
STORED AS ORC;

DROP TABLE MMA_TEST.`TEST_PARQUET_1x1K`;
CREATE TABLE MMA_TEST.`TEST_PARQUET_1x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
STORED AS PARQUET;

DROP TABLE MMA_TEST.`TEST_PARQUET_PARTITIONED_10x1K`;
CREATE TABLE MMA_TEST.`TEST_PARQUET_PARTITIONED_10x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
PARTITIONED BY (
`P1` STRING,
`P2` BIGINT
)
STORED AS PARQUET;

DROP TABLE MMA_TEST.`TEST_RCFILE_1x1K`;
CREATE TABLE MMA_TEST.`TEST_RCFILE_1x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
STORED AS RCFILE;

DROP TABLE MMA_TEST.`TEST_RCFILE_PARTITIONED_10x1K`;
CREATE TABLE MMA_TEST.`TEST_RCFILE_PARTITIONED_10x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
PARTITIONED BY (
`P1` STRING,
`P2` BIGINT
)
STORED AS RCFILE;

DROP TABLE MMA_TEST.`TEST_SEQUENCEFILE_1x1K`;
CREATE TABLE MMA_TEST.`TEST_SEQUENCEFILE_1x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
STORED AS SEQUENCEFILE;

DROP TABLE MMA_TEST.`TEST_SEQUENCEFILE_PARTITIONED_10x1K`;
CREATE TABLE MMA_TEST.`TEST_SEQUENCEFILE_PARTITIONED_10x1K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
PARTITIONED BY (
`P1` STRING,
`P2` BIGINT
)
STORED AS SEQUENCEFILE;

-- ************************** For general function tests ******************************************
DROP TABLE MMA_TEST.`TEST_NON_PARTITIONED_1x100K`;
CREATE TABLE MMA_TEST.`TEST_NON_PARTITIONED_1x100K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
STORED AS TEXTFILE;

DROP TABLE MMA_TEST.`TEST_PARTITIONED_1Kx10K`;
CREATE TABLE MMA_TEST.`TEST_PARTITIONED_1Kx10K` (
`T_TINYINT` TINYINT,
`T_SMALLINT` SMALLINT,
`T_INT` INT,
`T_BIGINT` BIGINT,
`T_FLOAT` FLOAT,
`T_DOUBLE` DOUBLE,
`T_DECIMAL` DECIMAL,
`T_TIMESTAMP` TIMESTAMP,
`T_STRING` STRING,
`T_VARCHAR` VARCHAR(255),
`T_CHAR` CHAR(255),
`T_BOOLEAN` BOOLEAN,
`T_BINARY` BINARY,
`T_ARRAY` ARRAY<STRING>,
`T_MAP` MAP<STRING, STRING>,
`T_STRUCT` STRUCT<c1 : STRING, c2 : BIGINT>)
 PARTITIONED BY (
`P1` STRING,
`P2` BIGINT
)
STORED AS TEXTFILE;

-- ************************** For performance tests ***********************************************
-- CREATE TABLE MMA_TEST.TEST_PARTITIONED_1K_SMALL(T_TINYINT TINYINT, T_SMALLINT SMALLINT, T_INT INT, T_BIGINT BIGINT, T_FLOAT FLOAT, T_DOUBLE DOUBLE, T_DECIMAL DECIMAL, T_TIMESTAMP TIMESTAMP,  T_STRING STRING, T_VARCHAR VARCHAR(255), T_CHAR CHAR(255), T_BOOLEAN BOOLEAN, T_BINARY BINARY, T_ARRAY ARRAY<STRING>, T_MAP MAP<STRING, STRING>, T_STRUCT STRUCT<c1 : STRING, c2 : BIGINT>) STORED AS TEXTFILE;
-- CREATE TABLE MMA_TEST.TEST_PARTITIONED_1K_LARGE(T_TINYINT TINYINT, T_SMALLINT SMALLINT, T_INT INT, T_BIGINT BIGINT, T_FLOAT FLOAT, T_DOUBLE DOUBLE, T_DECIMAL DECIMAL, T_TIMESTAMP TIMESTAMP,  T_STRING STRING, T_VARCHAR VARCHAR(255), T_CHAR CHAR(255), T_BOOLEAN BOOLEAN, T_BINARY BINARY, T_ARRAY ARRAY<STRING>, T_MAP MAP<STRING, STRING>, T_STRUCT STRUCT<c1 : STRING, c2 : BIGINT>) STORED AS TEXTFILE;
-- CREATE TABLE MMA_TEST.TEST_PARTITIONED_10K_SMALL(T_TINYINT TINYINT, T_SMALLINT SMALLINT, T_INT INT, T_BIGINT BIGINT, T_FLOAT FLOAT, T_DOUBLE DOUBLE, T_DECIMAL DECIMAL, T_TIMESTAMP TIMESTAMP,  T_STRING STRING, T_VARCHAR VARCHAR(255), T_CHAR CHAR(255), T_BOOLEAN BOOLEAN, T_BINARY BINARY, T_ARRAY ARRAY<STRING>, T_MAP MAP<STRING, STRING>, T_STRUCT STRUCT<c1 : STRING, c2 : BIGINT>) STORED AS TEXTFILE;
-- CREATE TABLE MMA_TEST.TEST_PARTITIONED_10K_LARGE(T_TINYINT TINYINT, T_SMALLINT SMALLINT, T_INT INT, T_BIGINT BIGINT, T_FLOAT FLOAT, T_DOUBLE DOUBLE, T_DECIMAL DECIMAL, T_TIMESTAMP TIMESTAMP,  T_STRING STRING, T_VARCHAR VARCHAR(255), T_CHAR CHAR(255), T_BOOLEAN BOOLEAN, T_BINARY BINARY, T_ARRAY ARRAY<STRING>, T_MAP MAP<STRING, STRING>, T_STRUCT STRUCT<c1 : STRING, c2 : BIGINT>) STORED AS TEXTFILE;
