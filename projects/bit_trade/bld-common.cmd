:cmd.exe /K

: sync
cd util
call mvn clean install
cd ..

: sync
cd common
call mvn clean install
cd ..

: sync
cd common-service
call mvn clean install
cd ..
