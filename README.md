# Sistema de Avaliação de filmes

Sistema web desenvolvido para gerenciar avaliações de filmes, permitindo que usuários registrem opiniões, atribuam notas e acompanhem tendências de popularidade. A plataforma substitui métodos informais de recomendação, organizando as informações de forma centralizada e facilitando a descoberta de novos títulos, além de oferecer recursos como comentários, sugestões personalizadas com base no perfil do usuário.

## Pré-requisitos

- Java 17+
- Maven 3.8+
- MySQL 8+

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/SENAI-Cotia/leo-e-amigo
   cd seu-repositorio
   ```

2. Configure o banco de dados em `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/leo-e-amigo
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse em `http://localhost:8080`

## Tecnologias

- Java + Spring Web
- Thymeleaf, HTML5 e CSS3
- MySQL