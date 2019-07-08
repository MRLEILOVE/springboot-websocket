@echo off

:cmd.exe /K

:call _bld-common.cmd

title "build bittrade-batch"
: sync
cd bittrade-batch
call mvn clean package -Dmaven.test.skip=true
cd ..

:pause

echo on