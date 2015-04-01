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

  * run following command line to use index in folder provided and list duplicated files in second folder

```
mvn exec:java -Dexec.mainClass="org.unicator.application.FilesCleaner" -Dexec.args="c:\my_photos c:\my_photos_diplicates_folder"
```

  * run following command line to use index in first folder and delete duplicated files in second folder

```
mvn exec:java -Dexec.mainClass="org.unicator.application.FilesCleaner" -Dexec.args="c:\my_photos c:\my_photos_diplicates_folder true"
```


_**Note:** You need to [run indexer](Indexer_How_To_Run_Instructions.md) before running cleaner tool_