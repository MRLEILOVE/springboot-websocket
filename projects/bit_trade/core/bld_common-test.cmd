@echo off

:cmd.exe /K

set dirs=core-common, core-tool, core-framework

:for %%i in (D:\work\git\git\projects\bit_trade\*) do (
for %%i in (%dirs%) do (
  title "build %%i"
  : sync
  cd %%i
  call mvn clean install -Ptest
  cd ..
)

:pause

echo on