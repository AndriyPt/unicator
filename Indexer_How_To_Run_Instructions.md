# How to run #

## Pre-requirements ##

You need to have installed Maven on your machine. Please follow these instructions http://maven.apache.org/download.cgi#Installation_Instructions

_**Note:** mvn should also be in the search path of the OS which you are using._


## To run application you can use following approach ##

  * Download source code of the tool
  * execute only once

```
mvn compile
```

  * run following command line to create index file filesIndex.idx in the folder provided

```
mvn exec:java -Dexec.mainClass="org.unicator.application.FilesIndexer" -Dexec.args="c:\my_photos"
```