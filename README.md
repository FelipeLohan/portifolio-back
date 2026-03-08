# Portfolio Backend API

REST API backend para portfólio pessoal, construída com Java 21 e Spring Boot.

## Tecnologias

- **Java 21**
- **Spring Boot 3.5.11**
- **PostgreSQL 16**
- **Spring Data JPA**
- **Spring Validation**
- **MapStruct 1.6.3**
- **Lombok**
- **Springdoc OpenAPI 2.8.16**
- **Docker / Docker Compose**

## Pré-requisitos

- Java 21+
- Docker e Docker Compose

## Como executar

**1. Suba o banco de dados:**

```bash
docker-compose up -d
```

**2. Execute a aplicação:**

```bash
./mvnw spring-boot:run
```

**3. Execute os testes:**

```bash
./mvnw test
```

## Acessos

| Serviço     | URL                                    |
|-------------|----------------------------------------|
| API         | http://localhost:8080                  |
| Swagger UI  | http://localhost:8080/swagger-ui.html  |
| PgAdmin     | http://localhost:5433                  |

## Banco de dados

| Propriedade | Valor                              |
|-------------|------------------------------------|
| URL         | `jdbc:postgresql://localhost:5432/mydb` |
| Usuário     | `admin`                            |
| Senha       | `admin123`                         |

## Endpoints — Customers

| Método   | Rota               | Descrição                  |
|----------|--------------------|----------------------------|
| `POST`   | `/customers`       | Cria um novo customer      |
| `GET`    | `/customers`       | Lista todos os customers   |
| `GET`    | `/customers/{id}`  | Busca um customer por ID   |
| `PUT`    | `/customers/{id}`  | Atualiza um customer       |
| `DELETE` | `/customers/{id}`  | Remove um customer         |

### Payload de criação/atualização

```json
{
  "name": "Felipe Lohan",
  "email": "felipe@email.com",
  "telephoneNumber": "11999999999",
  "message": "Olá, gostaria de entrar em contato."
}
```

### Respostas de erro

Todos os erros seguem o formato:

```json
{
  "status": 404,
  "message": "Customer not found"
}
```

Códigos retornados: `404` (não encontrado), `422` (erro de validação).

## Estrutura do projeto

```
src/main/java/com/FelipeLohan/portifolio/
├── PortifolioApplication.java
├── customer/
│   ├── Customer.java
│   ├── CustomerController.java
│   ├── CustomerService.java
│   ├── CustomerRepository.java
│   ├── dtos/
│   │   ├── RequestCustomerDTO.java
│   │   └── ResponseCustomerDTO.java
│   ├── interfaces/
│   │   ├── CustomerControllerI.java
│   │   └── CustomerServiceI.java
│   └── mapper/
│       └── CustomerMapper.java
└── exception/
    ├── GlobalExceptionHandler.java
    └── ErrorResponseDTO.java
```
