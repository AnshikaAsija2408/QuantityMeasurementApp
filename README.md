# Quantity Measurement App — Microservices Edition

This project has been refactored from a single Spring Boot monolith into
**2 independent microservices**, exactly as requested:

```
Root/
├── oauth-service/                    (port 8080)
├── quantity-service/                 (port 8081)
├── quantity-measurement-frontend/    (React app — kept exactly as-is,
│                                       node_modules excluded, run `npm install`)
└── README.md
```

The **React frontend has not been redesigned, rewritten, or restructured**.
Every file, folder, component, style, and asset is untouched. The **only**
change made anywhere in the frontend is 2 lines in one file:
`quantity-measurement-frontend/src/api/quantityApi.js` — the `BASE_URL`
constant now points to `http://localhost:8081/api/quantity` (the new
quantity-service port) instead of `:8080`, since quantity endpoints moved
off the old monolith port. `authService.js` and `DashboardPage.jsx` were
**not** touched — they still point at `http://localhost:8080`, which is
still correct because oauth-service kept the original port and endpoints
(`/oauth2/**`, `/logout`, `/user`).

No Docker, no API Gateway, no Eureka, no Config Server were used.
The two services talk to each other with **OpenFeign** (see "How the
services communicate" below).

---

## 1. Project structure

### oauth-service (port 8080)
```
oauth-service/
├── pom.xml
├── .env                                (Google OAuth2 client id/secret)
└── src/main/java/org/example/oauthservice/
    ├── OAuthServiceApplication.java
    ├── config/
    │   ├── SecurityConfig.java
    │   └── OpenApiConfig.java
    ├── controller/
    │   ├── AuthController.java         (login redirect, register, login,
    │   │                                 /api/auth/validate, google callback,
    │   │                                 /api/profile, /hello)
    │   ├── OAuthController.java        (/user – Google profile info)
    │   └── UserController.java         (/api/users/me – NEW, user management)
    ├── dto/
    │   ├── RegisterRequest.java        (NEW)
    │   ├── LoginRequest.java           (NEW)
    │   ├── AuthResponse.java           (NEW)
    │   ├── UserResponse.java           (NEW)
    │   └── ValidationResponse.java     (NEW – returned to quantity-service via Feign)
    ├── entity/
    │   └── User.java                   (NEW – JPA entity, table app_user)
    ├── repository/
    │   └── UserRepository.java         (NEW)
    ├── service/
    │   └── UserService.java            (NEW – register/login/find-or-create-OAuth-user)
    ├── security/
    │   ├── JwtUtil.java                (moved, unchanged logic)
    │   ├── JwtAuthenticationFilter.java(moved, unchanged logic)
    │   └── OAuth2LoginSuccessHandler.java (moved; now also persists the
    │                                        Google user into app_user)
    └── exception/
        ├── AuthException.java          (NEW)
        └── GlobalExceptionHandler.java (moved)
```

### quantity-service (port 8081)
```
quantity-service/
└── src/main/java/org/example/quantityservice/
    ├── QuantityServiceApplication.java (@EnableFeignClients)
    ├── client/
    │   └── OAuthServiceClient.java     (NEW – OpenFeign client)
    ├── config/
    │   ├── SecurityConfig.java         (rewritten to use FeignAuthenticationFilter)
    │   └── OpenApiConfig.java
    ├── controller/
    │   └── QuantityMeasurementController.java (moved, unchanged endpoints)
    ├── dto/
    │   └── ValidationResponse.java     (NEW – mirrors oauth-service's response)
    ├── entity/
    │   └── QuantityMeasurementEntity.java (moved, unchanged)
    ├── exception/
    │   ├── QuantityMeasurementException.java (moved, unchanged)
    │   └── GlobalExceptionHandler.java (moved, unchanged)
    ├── model/
    │   ├── QuantityDTO.java             (moved, unchanged)
    │   ├── QuantityModel.java           (moved, unchanged)
    │   └── QuantityOperationRequest.java(moved, unchanged)
    ├── quantity/
    │   └── Quantity.java                (moved, unchanged)
    ├── repository/
    │   └── QuantityMeasurementRepository.java (moved, unchanged — the real,
    │                                            JPA-backed repository)
    ├── security/
    │   └── FeignAuthenticationFilter.java (NEW – replaces JwtAuthenticationFilter;
    │                                        delegates token validation to oauth-service)
    ├── service/
    │   ├── IQuantityMeasurementService.java (moved, unchanged)
    │   └── QuantityMeasurementServiceImpl.java (moved, unchanged business logic)
    └── unit/
        ├── IMeasurable.java, SupportArithmetic.java
        ├── Length.java, LengthUnit.java
        ├── Weight.java, WeightUnit.java
        ├── VolumeUnit.java, TemperatureUnit.java  (all moved, unchanged)
```

---

## 2. What was moved / deleted / added

**Moved as-is** (only the Java package changed, from `org.example.*` to
`org.example.oauthservice.*` or `org.example.quantityservice.*`):
`JwtUtil`, `JwtAuthenticationFilter`, `OAuth2LoginSuccessHandler`,
`GlobalExceptionHandler` (both), `QuantityMeasurementController`,
`QuantityMeasurementServiceImpl`/`IQuantityMeasurementService`,
`QuantityMeasurementEntity`, `QuantityMeasurementRepository`,
`QuantityMeasurementException`, `QuantityDTO`, `QuantityModel`,
`QuantityOperationRequest`, `Quantity`, and every class under `unit/`
(`Length`, `LengthUnit`, `Weight`, `WeightUnit`, `VolumeUnit`,
`TemperatureUnit`, `IMeasurable`, `SupportArithmetic`).

**Deleted** (dead/broken legacy code that was never actually wired into
the running Spring context — only referenced by a fully commented-out
integration test — and therefore would have violated the
"no incomplete implementations" requirement if carried forward):
`ConnectionPool`, `ApplicationConfig`, `QuantityMeasurementDatabaseRepository`,
`QuantityMeasurementCacheRepository`, `IQuantityMeasurementRepository`,
`DatabaseException`.

**Added (new files)**, to satisfy "Login/Register, User Management" for
the OAuth service and to wire the two services together:
- `User` entity + `UserRepository` + `UserService` — your original project
  only supported Google OAuth2 login with no local users table, so a
  proper register/login/user-management layer (BCrypt-hashed passwords,
  H2-backed) was added. Google sign-ins are now also persisted as `User`
  rows via `UserService.findOrCreateOAuthUser`.
- `RegisterRequest`, `LoginRequest`, `AuthResponse`, `UserResponse`,
  `ValidationResponse` DTOs.
- `AuthException` + `UserController` (`/api/users/me`).
- `OAuthServiceClient` (OpenFeign client) + `FeignAuthenticationFilter`
  in quantity-service, and the matching `GET /api/auth/validate` endpoint
  in oauth-service's `AuthController`.

**Frontend — not redesigned, not rewritten, not restructured.** The
entire `quantity-measurement-frontend` folder (components, pages,
services, styles, assets, `public/`, config files) was carried over
byte-for-byte, with exactly one 2-line edit:

| File | Change |
|---|---|
| `src/api/quantityApi.js` | `BASE_URL` changed from `http://localhost:8080/api/quantity` to `http://localhost:8081/api/quantity`, and the comment above it updated to match |

Nothing else in the frontend — `authService.js`, `historyService.js`,
`DashboardPage.jsx`, every component, every style — was modified. No
files were added, moved, or deleted in the frontend. (`node_modules` was
left out of the ZIP only to keep the download small; run `npm install`
to restore it.)

---

## 3. How to run

1. Extract the ZIP.
2. Import **both** `oauth-service` and `quantity-service` into IntelliJ
   as two separate Maven projects (or open the `Root` folder and let
   IntelliJ detect both `pom.xml` files as modules).
3. Start **oauth-service** first (it must be reachable for quantity-service
   to validate tokens):
   ```
   cd oauth-service
   mvn spring-boot:run
   ```
   Runs on **http://localhost:8080**.
4. Start **quantity-service**:
   ```
   cd quantity-service
   mvn spring-boot:run
   ```
   Runs on **http://localhost:8081**.
5. Swagger UI:
   - oauth-service: http://localhost:8080/swagger-ui.html
   - quantity-service: http://localhost:8081/swagger-ui.html
6. H2 consoles:
   - oauth-service: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:authdb`)
   - quantity-service: http://localhost:8081/h2-console (JDBC URL `jdbc:h2:mem:quantitydb`)

6. Run the frontend (unchanged, `node_modules` was excluded from the ZIP
   to keep it small):
   ```
   cd quantity-measurement-frontend
   npm install
   npm run dev
   ```
   Runs on **http://localhost:5173** (Vite default) and talks to
   oauth-service on `:8080` and quantity-service on `:8081`.

## 4. Ports

| Service            | Port |
|--------------------|------|
| oauth-service      | 8080 |
| quantity-service    | 8081 |

## 5. How the two services communicate (OpenFeign)

quantity-service does **not** duplicate the JWT secret or validation
logic. Every request to `/api/quantity/**` is intercepted by
`FeignAuthenticationFilter`, which calls:

```
GET http://localhost:8080/api/auth/validate
Authorization: Bearer <token>
```

via the `OAuthServiceClient` OpenFeign interface. oauth-service decodes
and verifies the JWT and returns `{ valid, email, message }`. If
`valid == true`, quantity-service authenticates the request as that
email with `ROLE_USER`; otherwise the request is rejected (401/403) by
Spring Security. This keeps authentication logic centralized in exactly
one microservice, which is the reason OpenFeign is used here.

`oauth.service.url=http://localhost:8080` in
`quantity-service/src/main/resources/application.properties` controls
the target — change it if you run oauth-service on a different host/port.

## 6. Notes

- Both services use separate in-memory H2 databases (`authdb` and
  `quantitydb`), so there is no shared-database coupling between them —
  a requirement for two properly independent microservices.
- Spring Cloud OpenFeign requires the Spring Cloud BOM;
  `quantity-service/pom.xml` imports `spring-cloud-dependencies:2025.0.0`,
  which is the release train compatible with Spring Boot 3.5.0 (the
  parent version both services use, matching your original project).
