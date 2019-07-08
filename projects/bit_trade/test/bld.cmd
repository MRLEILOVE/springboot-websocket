@echo off

:cmd.exe /K

:call _bld-common.cmd

title "build test"
: sync
:cd test
call mvn clean package -Dmaven.test.skip=true
:cd ..

:pause

echo on