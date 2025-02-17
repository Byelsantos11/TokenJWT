# Projeto Token-JWT

Este é um projeto gerenciador desenvolvido com o objetivo de praticar minhas habilidades recém-aprendidas, incluindo a implementação de autenticação e autorização por meio de tokens JWT.

## Funcionalidades

- **Sistema de Administradores**:
  - Administrador supremo pode criar e excluir outros administradores.
  - Administradores normais possuem permissões limitadas.
- **Sistema de Login por Token (JWT)**
- **Sistema de Perfil**:
  - Usuário pode visualizar suas informações no menu (endereço, telefone, nome, e-mail etc.)
  - Implementado com Spring Security
- **Telas Front-end**:
  - O projeto possui telas para maior imersão, incluindo uma interface para administradores.
  - URLs foram deixadas públicas para que todos consigam acessar sem problemas de autenticação em páginas web.

## Tecnologias Utilizadas

- Spring Boot
- Spring Security
- Java
- JavaScript
- MySQL
- HTML
- CSS
- Git & GitHub

## Como Baixar e Executar o Projeto

### 1. Clonar o Repositório

```bash
 git clone https://github.com/Byelsantos11/TokenJWT.git
```

### 2. Configurar o Banco de Dados

Certifique-se de ter o MySQL instalado e configurado. Crie um banco de dados chamado `Sua escolha` e ajuste as configurações no `application.properties`.

### 3. Instalar Dependências e Executar o Projeto

Navegue até o diretório do projeto e execute:

```bash
mvn spring-boot:run
```

O sistema estará disponível em `http://localhost:8080/`.

---

**Obs:** Este projeto foi desenvolvido com uma visão de crescimento futuro e aprimoramento contínuo.

