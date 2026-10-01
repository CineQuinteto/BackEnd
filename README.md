Projeto Cinema - Back-end

Este é o repositório do Projeto Cinema, desenvolvida em Java com Spring Boot. A aplicação é responsável por gerenciar todo o catálogo de filmes do sistema, realizando a comunicação com o banco de dados e fornecendo os dados para o Front-end.

Tecnologias Utilizadas
 Java 17
 Spring Boot
 Spring Data JPA
 Banco de Dados PostgreSQL
 Maven

Funcionalidades e Rotas (CRUD)
A API foi estruturada com as seguintes rotas para gerenciar os filmes:

 Listar todos os filmes: `GET /api/filmes`
 Buscar filme por ID: `GET /api/filmes/{id}`
 Cadastrar filme: `POST /api/filmes`
 Atualizar filme: `PUT /api/filmes/{id}`
 Excluir filme: `DELETE /api/filmes/{id}`

Tratamento de Erros
Para evitar que a aplicação mostre erros nativos do Java no console do usuário, implementamos um controlador de exceções global utilizando `@ControllerAdvice`. Se um filme não for encontrado ou se faltar alguma informação obrigatória no cadastro, a API devolve uma mensagem de erro clara em formato JSON para o Front-end.

Demonstração do Funcionamento do CRUD
Abaixo está demonstrado o funcionamento prático das requisições e respostas da nossa API durante os testes de integração das operações de CRUD:

1. Cadastro de Filme (`POST`)
    Requisição:
```json
{
  "titulo": "Interestelar",
  "genero": "Ficção Científica",
  "diretor": "Christopher Nolan",
  "anoLancamento": 2014,
  "descriçao": "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço."
}
```
   Resposta (Status `201 Created`):
```json
{
  "id": 1,
  "titulo": "Interestelar",
  "genero": "Ficção Científica",
  "diretor": "Christopher Nolan",
  "anoLancamento": 2014,
  "descriçao": "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço."
}
```

2. Consulta de Filme por ID (`GET`)
 Requisição:** `GET /api/filmes/1`
 Resposta (Status `200 OK`):
```json
{
  "id": 1,
  "titulo": "Interestelar",
  "genero": "Ficção Científica",
  "diretor": "Christopher Nolan",
  "anoLancamento": 2014,
  "descriçao": "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço."
}
```

3. Atualização de Dados (`PUT`)
 Requisição: `PUT /api/filmes/1`
```json
{
  "titulo": "Interestelar (Versão IMAX)",
  "genero": "Ficção Científica",
  "diretor": "Christopher Nolan",
  "anoLancamento": 2014,
  "descriçao": "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço."
}
```
 Resposta (Status `200 OK`):
```json
{
  "id": 1,
  "titulo": "Interestelar (Versão IMAX)",
  "genero": "Ficção Científica",
  "diretor": "Christopher Nolan",
  "anoLancamento": 2014,
  "descriçao": "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço."
}
```

4. Remoção de Filme (`DELETE`)
 Requisição: `DELETE /api/filmes/1`
 Resposta: Status `204 No Content`

Integrantes do Grupo

[Luis Gustavo / Henrick] - Back-end

[Lucas santos / Henrick] - Front-end

[Leandro Barbosa] - Devops

[Erick Conceição] - Banco de dados
