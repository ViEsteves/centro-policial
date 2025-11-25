# centro-policial

 1. Sobre o Projeto
 Nome do projeto: Centro Policial
 O projeto Centro Policial é uma aplicação Web desenvolvida com Java + Spring Boot + Thymeleaf,
 cujo objetivo é gerenciar Chamados ligados a Departamentos policiais. Inclui CRUD completo,
 relacionamento 1:N, upload, autenticação, paginação, ordenação, busca, API REST e Swagger.
 
 2. Tecnologias
 Java 17+, Spring Boot, Thymeleaf, MySQL 8+, Maven, Springdoc OpenAPI.
 
 3. Arquitetura (resumo)
 Controller (web/api) → service → repository; DTOs; upload de imagem; documentação OpenAPI.
 
 4. Requisitos de Ambiente
 JDK 17+, Maven 3.8+, MySQL 8+.
 
 5. Configuração do Banco
 CREATE DATABASE centro_policial;
 
 6. Configuração da Aplicação
 application.properties:
 spring.datasource.url=jdbc:mysql://localhost:3306/centro_policial?useSSL=false&serverTimezone;=UT
 C
 spring.datasource.username=SEU_USUARIO
 spring.datasource.password=SUA_SENHA
 
 7. Instalação e Execução
 git clone https://github.com/ORG/centro-policial.git
 cd centro-policial
 mvn spring-boot:run

 8. Seeds de Usuários
 admin@exemplo.com / 123
user@exemplo.com / 123
 
 9. Segurança
 Rotas públicas, autenticadas e ADMIN; formulário de login; logout.
 
 10. Rotas Web
 CRUD completo de Chamados e Departamentos; busca; paginação.
 
 11. API REST
 Paginação, ordenação e filtros; Swagger: /swagger-ui/index.html.
 
 12. Upload
 Imagem anexada ao Chamado; salva em /uploads; exibida no sistema.
 
 13. Testes
 mvn test

 14. CI
 mvn -B test no GitHub Actions.

 15. Checklist
 Ambiente OK, seeds OK, CRUD OK, upload OK, Swagger OK.

 16. Links de Entrega
 GitHub, YouTube, coleção Postman.

 17. Documento para o Classroom
 Resumo, links, integrantes, ano, curso.

 18. Critérios de Avaliação
 Funcionalidades, segurança, API, Thymeleaf, MySQL, código, CI, documentação.
 
 19. Autores
 Vinícius Esteves de Souza — RA 3023104087
 Amanda Esteves Brito dos Santos — RA 30231052113
 Ryan Eleutherio Dias — RA 3023105252

# Centro Policial

## Integrantes
- Vinícius Esteves de Souza — RA 3023104087
- Amanda Esteves Brito dos Santos — RA 30231052113
- Ryan Eleutherio Dias — RA 3023105252

## Instruções rápidas
1. Configurar `src/main/resources/application.properties` com credenciais MySQL.
2. Criar DB: `CREATE DATABASE centro_policial_db;`
3. Rodar: `mvn spring-boot:run`
4. Usuários seeds:
   - admin@exemplo.com / admin123
   - user@exemplo.com / user123
5. Swagger: http://localhost:8080/swagger-ui/index.html
