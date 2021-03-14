call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo Script run to errors - breaking work
goto fail

:browser
start opera http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Running browser failed - breaking work
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished