# CineStar — Sistema de Avaliação de Filmes

Sistema web desenvolvido para gerenciar avaliações de filmes, permitindo que usuários registrem opiniões, atribuam notas e acompanhem tendências de popularidade. A plataforma substitui métodos informais de recomendação, organizando as informações de forma centralizada e facilitando a descoberta de novos títulos.

---

## Tecnologias Utilizadas

### Backend
![Java](https://img.shields.io/badge/Java-17+-orange?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-green?style=flat&logo=springboot)
![Spring Web](https://img.shields.io/badge/Spring_Web-green?style=flat&logo=spring)

### Frontend
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-green?style=flat&logo=thymeleaf)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white)

### Banco de Dados
![MySQL](https://img.shields.io/badge/MySQL-8+-4479A1?style=flat&logo=mysql&logoColor=white)

---

## Funcionalidades

- Autenticação com email e senha
- Registro de avaliações e notas por usuário
- Sistema de comentários em filmes
- Sugestões personalizadas com base no perfil do usuário
- Acompanhamento de tendências de popularidade
- Descoberta e busca de novos títulos

---

## Perfis de Acesso

| Perfil | Permissões |
|---|---|
| ![Admin](https://img.shields.io/badge/Perfil-ADMIN-1A3C5E?style=flat) | Gerenciar filmes e usuários, moderar comentários |
| ![Usuário](https://img.shields.io/badge/Perfil-USUÁRIO-2E75B6?style=flat) | Avaliar filmes, comentar e receber sugestões |

---

## Como Rodar Localmente

### Pré-requisitos

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=flat&logo=apachemaven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8+-4479A1?style=flat&logo=mysql&logoColor=white)

### Instalação

1. Clone o repositório:
```bash
git clone https://github.com/SENAI-Cotia/leo-e-amigo
cd leo-e-amigo
```

2. Configure o banco em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cinestar
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Execute:
```bash
mvn spring-boot:run
```

4. Acesse em `http://localhost:8080`

---

## Acesso Admin

| Campo | Valor |
|---|---|
| Email | `admin@admin.com` |
| Senha | `admin@123` |

---

<div align="center">

Desenvolvido por

[![Leo](https://img.shields.io/badge/LeoSabbatini-181717?style=flat&logo=github&logoColor=white)](https://github.com/LeoSabbatini)
[![João](https://img.shields.io/badge/Manobrown29-181717?style=flat&logo=github&logoColor=white)](https://github.com/manobrown29)

</div>
