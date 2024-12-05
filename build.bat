@echo off
REM Parar o script em caso de erro
setlocal enabledelayedexpansion

REM Executar o Maven clean install
echo Executando mvn clean install...
mvn clean install

REM Verificar se o comando anterior foi bem-sucedido
if %errorlevel% neq 0 (
    echo Erro ao executar mvn clean install
    exit /b %errorlevel%
)

REM Construir a imagem Docker
echo Construindo a imagem Docker...
docker build .

REM Verificar se o comando anterior foi bem-sucedido
if %errorlevel% neq 0 (
    echo Erro ao construir a imagem Docker
    exit /b %errorlevel%
)

echo Build concluído com sucesso!
