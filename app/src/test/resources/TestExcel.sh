#!/bin/bash 

java \
  -XX:+UseG1GC \
  -Xms4096M -Xmx8192M \
  -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m \
  -Dlog4j2.loggerContextFactory=org.apache.logging.log4j.simple.SimpleLoggerContextFactory \
  -Xlog:gc*:file=../../../../target/gclog/gc.log:tags,time,uptime \
  \
 -jar ../../../build/libs/app-all.jar \
  ../../main/resources/Template-KnToolsExcelHulft.xlsx  \
  ../../../../target/shell-targetHULFT.xlsx \
  ./deffiles/snd.def \
  ./deffiles/Rcv.def \
  ./deffiles/Hst.def \
  ./deffiles/TGrp.def \
  ./deffiles/Job.def

exit
