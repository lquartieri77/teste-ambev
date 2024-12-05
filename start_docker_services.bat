@echo off
REM Parar o script em caso de erro
setlocal enabledelayedexpansion

REM Executar o Docker para RabbitMQ
echo Iniciando RabbitMQ...
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 -d rabbitmq:4.0-management

REM Verificar se o comando anterior foi bem-sucedido
if %errorlevel% neq 0 (
    echo Erro ao iniciar RabbitMQ
    exit /b %errorlevel%
)

REM Executar o Docker para PostgreSQL
echo Iniciando PostgreSQL...
docker run --env=POSTGRES_USER=postgres --env=POSTGRES_PASSWORD=1234 -p 5432:5432 -d postgres

REM Verificar se o comando anterior foi bem-sucedido
if %errorlevel% neq 0 (
    echo Erro ao iniciar PostgreSQL
    exit /b %errorlevel%
)

echo Serviços Docker iniciados com sucesso!
