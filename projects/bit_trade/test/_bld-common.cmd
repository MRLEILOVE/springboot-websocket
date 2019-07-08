@echo off

:cmd.exe /K

set dirs=test-bittrade-pojo, test-bittrade-api

:for %%i in (D:\work\git\git\projects\bit_trade\*) do (
for %%i in (%dirs%) do (
  title "build %%i"
  : sync
  cd %%i
  call mvn clean install
  cd ..
)

:pause

echo on