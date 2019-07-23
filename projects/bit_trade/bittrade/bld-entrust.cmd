@echo off

:cmd.exe /K

:call _bld-common.cmd

title "build bittrade-entrust"
: sync
cd bittrade-entrust
call mvn clean package -Dmaven.test.skip=true
cd ..

:pause

echo on