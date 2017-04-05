@echo off

echo -------------------------------------------------------------------------
echo  Build  Script for Windows
echo -------------------------------------------------------------------------

setlocal
set CURRENT_DIR=%~dp0%

call ant
pause
