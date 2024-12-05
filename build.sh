#!/bin/bash

# Parar o script em caso de erro
# necessaria permissao de execucao = chmod +x build.sh
set -e

# Executar o Maven clean install
echo "Executando mvn clean install..."
mvn clean install

# Construir a imagem Docker
echo "Construindo a imagem Docker..."
docker build .

echo "Build concluído com sucesso!"
