#!/bin/bash

# Parar o script em caso de erro
set -e

# Executar o Docker para RabbitMQ
echo "Iniciando RabbitMQ..."
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 -d rabbitmq:4.0-management

# Executar o Docker para PostgreSQL
echo "Iniciando PostgreSQL..."
docker run --env=POSTGRES_USER=postgres --env=POSTGRES_PASSWORD=1234 -p 5432:5432 -d postgres

echo "Serviços Docker iniciados com sucesso!"
