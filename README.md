# 🧠 ForoHub API - Backend REST

> 🎯 _Proyecto realizado como parte del Challenge de Alura Latam sobre Java & Spring Boot – Desarrollo de API REST para gestión de tópicos en foros técnicos._

**ForoHub** es una API REST desarrollada en Java con Spring Boot que gestiona tópicos de conversación en un sistema estilo foro técnico. Diseñada pensando en seguridad, escalabilidad y modularidad.

---

## 📑 Menú

- [🚀 Características principales](#-características-principales)
- [🛠️ Tecnologías utilizadas](#️-tecnologías-utilizadas)
- [📚 Documentación Swagger UI](#-documentación-swagger-ui)
- [🔐 Seguridad](#-seguridad)
- [⚙️ Configuración local](#️-configuración-local)
- [🧪 Pruebas](#-pruebas)
- [📁 Estructura del proyecto](#-estructura-del-proyecto)
- [🤝 Contribuciones](#-contribuciones)
- [📄 Licencia](#-licencia)
- [🙋‍♂️ Autor](#-autor)

---

## 🚀 Características principales

- CRUD completo para tópicos
- Autenticación con JWT
- Paginación y ordenamiento dinámico
- Gestión de roles (Admin / Usuario)
- Validaciones robustas y manejo de excepciones
- Configuración segura y modular con Spring Security
- Adaptación de zona horaria a Bogotá 🇨🇴

---

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
| Swagger                | Documentación interactiva            |

---

## 📚 Documentación Swagger UI

Este proyecto cuenta con documentación interactiva de los endpoints gracias a **Swagger UI**.

📄 URL local: http://localhost:8080/swagger-ui/index.html


🔹 Ejemplo de endpoints:

- `GET /topics` → Lista todos los tópicos
- `POST /topics` → Crea un nuevo tópico
- `PUT /topics/{id}` → Actualiza el tópico indicado
- `DELETE /topics/{id}` → Elimina el tópico
- `POST /auth/login` → Autenticación y entrega de JWT

---

## 🔐 Seguridad

- Autenticación vía JWT
- Roles diferenciados con acceso controlado
- Filtros personalizados en Spring Security
- Uso de variables de entorno para ocultar credenciales

---

## ⚙️ Configuración local

```bash
git clone https://github.com/tu_usuario/foro-hub-api.git
cd foro-hub-api
./mvnw spring-boot:run

📌 Configura las variables de entorno necesarias (DB_URL, DB_USERNAME, DB_PASSWORD, JWT_SECRET) en un archivo .env (excluido por .gitignore) antes de ejecutar.

🧪 Pruebas
- Preparado para pruebas con JUnit y Mockito
- Pruebas unitarias e integración en progreso

📁 Estructura del proyecto
src/
├── config           # Configuración de seguridad
├── controller       # Controladores REST
├── dto              # Objetos de transferencia de datos
├── entity           # Entidades JPA
├── exception        # Manejo de errores
├── repository       # Interfaces JPA
├── service          # Lógica de negocio
└── utils            # Utilitarios generales



🤝 Contribuciones
¡Toda mejora es bienvenida! Puedes abrir issues, enviar pull requests o compartir ideas en la sección Discussions.

📄 Licencia
Este proyecto está bajo la Licencia MIT. Puedes usarlo libremente respetando los términos.

🙋‍♂️ Autor
Desarrollado por Faner Santander.
👩‍💻 Sígueme en GitHub para más proyectos backend con Java + Spring Boot.
