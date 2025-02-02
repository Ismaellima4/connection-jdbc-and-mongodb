# Como executar o projeto

Este projeto utiliza o Docker Compose para gerenciar os contêineres. 
Você pode escolher entre utilizar o PostgreSQL ou o MongoDB como banco de dados.

## Pré-requisitos

Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.

## Escolhendo o banco de dados

Você precisa escolher qual banco de dados irá utilizar: PostgreSQL ou MongoDB.

*   **PostgreSQL:** Utilize o arquivo `docker-compose.postgres.yaml`.
*   **MongoDB:** Utilize o arquivo `docker-compose.mongodb.yaml`.

## Executando o projeto

1.  Clone este repositório.
2.  Navegue até o diretório do projeto no terminal.
3.  Execute o comando correspondente ao seu banco de dados:

**Para PostgreSQL:**

```bash
docker-compose -f docker-compose.postgres.yaml up -d
```

**Para MongoDB:**

```bash
docker-compose -f docker-compose.mongodb.yaml up -d
