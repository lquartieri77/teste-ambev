# Process Orders Application

## Visão Geral
A **Process Orders Application** é um serviço desenvolvido em Spring Boot para consumir e processar pedidos de vendas. Utiliza PostgreSQL para armazenamento de dados e RabbitMQ para gerenciamento de mensagens.

## Requisitos
- Java 17
- Maven 3.6+
- Docker
- Docker Compose

## Configuração do Ambiente de Desenvolvimento
### 1. Subir os containers do RabbitMQ e do Postgres
Isso pode ser feito executando o script abiaxo, disponível para linux e windows:
```bash
start_docker_services.bat
```
```bash
start_docker_services.sh
```

### 2. Buildando a aplicação
```bash
mvn clean install
```

### 3. Rodando a aplicação (neste momento a fila de integracao com o sistema A é criada, o banco de dados também)
```bash
mvn spring-boot:run
```

### 4. Enviando mensagens de pedidos de venda a nossa fila (Esta a *integracao A*, optei por uma fila para consumir os pedidos)
Acesse: http://localhost:15672/#/ (Painel de gerenciamento do RabbitMQ), vá em Queues, copie o conteudo do arquivo 'src/main/resources/order_list_message.json',
cole o json na caixa de texto e clique no botão 'Publish Message'.
Com a aplicacção rodando, será possivel ver nos logs a mesma consumindo as mensagens e salvando no banco de dados.

### 5. Recuperando as Orders (Esta a *integracao B*, optei por fornecere estes dados via Api REST, implementar uma fila neste ponto também era uma opção, que pode vir numa outra versao)
Utilizando o seguinte comando cURL, pode se importar o mesmo no Postman, por exemplo para ter um teste da recuperacao dos dados.
```bash
curl --location 'http://localhost:8080/orders?orderReceived=05%2F12%2F2024&sellerCode=325&clienteDocument=987.654.321-00&page=0&size=10&sort=orderReceived%2Casc'
```
Esta consulta retorna os dados dos pedidos somados com valor total. É possível filtrar os dados de acordo com os parametros informados, podem ser analisados no cURL acima.
Esta busca é paginada.

### 6. Podemos também utilizar a aplicação na versao em container docker. 
Preparei um Dockerfile e um docker-compose que sobe todas as instancias necessárias para rodar nossa aplicação.
Sobe o RabbitMQ, o Postgres e nossa aplicação, todas numa mesma network.

Vamos criar nossa imagem:
```bash
docker build .
```
Vamos subir nosso compose
```bash
docker-compose up
```
Agora a aplicação está rodando direto do container docker.

Dúvidas e melhorias:
Leonardo Quartieri
leoquartieri@gmail.com





