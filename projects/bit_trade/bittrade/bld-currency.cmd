@echo off

:cmd.exe /K

call _bld-common.cmd

title "build bittrade-currency"
: sync
cd bittrade-currency
call mvn clean package -Dmaven.test.skip=true
cd ..

:pause

echo on