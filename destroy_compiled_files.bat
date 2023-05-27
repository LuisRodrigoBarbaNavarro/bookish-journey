@echo off
setlocal

REM Files to delete
set "file1=Lexer.java"
set "file2=LexerCup.java"
set "file3=Sintax.java"
set "file4=sym.java"

REM Delete the files
if exist "%file1%" (
    del "%file1%"
    echo %file1% has been deleted.
    pause
)

if exist "%file2%" (
    del "%file2%"
    echo %file2% has been deleted.
    pause
)

if exist "%file3%" (
    del "%file3%"
    echo %file3% has been deleted.
    pause
)

if exist "%file4%" (
    del "%file4%"
    echo %file4% has been deleted.
    pause
)

endlocal
