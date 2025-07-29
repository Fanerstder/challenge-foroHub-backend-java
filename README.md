# 🧠 ForoHub API - Backend REST

**ForoHub** es una API REST desarrollada en Java con Spring Boot que gestiona tópicos de conversación en un sistema estilo foro técnico. Diseñada pensando en seguridad, escalabilidad y modularidad.

## 🚀 Características principales

- CRUD completo para tópicos
- Autenticación con JWT
- Paginación y ordenamiento dinámico
- Gestión de roles (Admin / Usuario)
- Validaciones robustas y manejo de excepciones
- Configuración segura y modular con Spring Security
- Adaptación de zona horaria a Bogotá 🇨🇴

## 🛠️ Tecnologías utilizadas

| Tecnología              | Descripción                          |
|------------------------|--------------------------------------|
| Java 17                | Lenguaje principal                   |
| Spring Boot            | Framework backend                    |
| Spring Security        | Autenticación y autorización         |
| JPA + Hibernate        | Persistencia con entidades           |
| Flyway                 | Migraciones de base de datos         |
| MySQL                  | Base de datos relacional             |
| WebClient / RestTemplate | Consumo de APIs externas          |
| Swagger                | Documentación de endpoints           |

## 📚 Endpoints principales

Accede a la documentación Swagger:
http://localhost:8080/swagger-ui/index.html


Algunos endpoints destacados:

- `GET /topics` → Lista todos los tópicos
- `POST /topics` → Crea un nuevo tópico
- `PUT /topics/{id}` → Actualiza el tópico indicado
- `DELETE /topics/{id}` → Elimina el tópico
- `POST /auth/login` → Autenticación y entrega de JWT

## 🔐 Seguridad

La API utiliza Spring Security para proteger los recursos:

- Filtros personalizados para validación de tokens
- Roles diferenciados con control granular
- Variables de entorno para evitar exposición de secretos

## ⚙️ Configuración local

```bash
git clone https://github.com/tu_usuario/foro-hub-api.git
cd foro-hub-api
./mvnw spring-boot:run

