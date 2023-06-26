# allFilms
Ideia:
Um aplicativo que gerencie e controle os filmes que foram assistidos pelos usuários

Recursos:
 - Cadastro de Usuários: 
* O sistema permitirá que os usuários se cadastrem e mantenham (atualizem) um perfil com suas informações pessoais e preferências de filmes (gêneros, atores, diretores, estúdios, plataformas).

- Histórico: 
* O sistema deve permitir que os usuários cadastrem filmes que assistiram, e informe sua avaliação pro filme (nota, e review);
* Deve permitir também que busque filmes ja assistidos no histórico (recomendo paginar)
 
- Busca de filmes: 
* O sistema deve buscar filmes com filtros de gênero, ator, diretor, estúdio e plataforma, com busca por nome  (recomendo paginar)
* O sistema deve buscar filmes com as preferencias cadastradas no perfil do usuário (recomendo paginar)
* Detalhar um filme, exibindo plataforma, nome, nota, data de lançamento, gêneros, duração, mota, sinopse, diretor, etc
* Analises e notas do filme (recomendo paginar)

Requisitos:
- Projeto devera usar o conceito de Programação Orientada a Objetos
- O projeto devera ser documentado com o Swagger
- Deve ser usado streams e lambdas no Java para operações de filtragem, ordenação e transformações de dados
- Uso de JPA para mapear classes Java para tabelas do banco de dados, consultas, transações e outras operações de persistência
- Uso do Hibernate para gerenciar as transcoes, a criação de esquema do banco, e outras tarefas relacionadas com a persistência
- O sistema deve conter teste unitário com Mockito para classes e métodos do sistema
- Consumo de API externa para busca de dados, sugestão: The Movie Database (TMDb): para base de dados grátis de filmes e series (https://developer.themoviedb.org/docs)
 - Um repositorio no github deve ser criado para o projeto
- O projeto deve usar JAVA
- O projeto deve usar Spring Boot
