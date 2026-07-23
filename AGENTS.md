# AGENTS: How to work with this codebase

This is a small Spring Boot (Java 21) hospital-management sample. The goal of this file is to give an AI coding agent the exact, actionable knowledge needed to be productive immediately.

Quick facts
- Language: Java 21, framework: Spring Boot 4.x (Jakarta namespace)
- Build: Maven (wrapper available: `./mvnw`)
- DB: MySQL (configured in `src/main/resources/application.properties`)

Quick commands
- Build (skip tests): `./mvnw -DskipTests package`
- Run locally (requires reachable MySQL instance): `MYSQL_HOST=localhost ./mvnw spring-boot:run`
- Build + run jar: `./mvnw package` then `java -jar target/hospitalManagement-0.0.1-SNAPSHOT.jar`
- Run tests: `./mvnw test` (tests use @SpringBootTest and expect a DB configured by application.properties)

Where to look (key files)
- `pom.xml` — dependencies (Spring Boot starters, Lombok, MySQL & PostgreSQL drivers), compiler annotation-processor configured for Lombok
- `src/main/resources/application.properties` — datasource, SQL init, and explicit credentials (password present in repo)
- `src/main/resources/data.sql` — SQL seed data (note: its schema/names do not match the JPA mapping; see pitfalls)
- `src/main/java/com/project/hospitalManagement/entity/Patient.java` — JPA entity (table: `patient_tbl`, unique constraint on `email`, index on `dateOfBirth`)
- `src/main/java/com/project/hospitalManagement/repository/PatientRepository.java` — Spring Data JPA interface
- `src/main/java/com/project/hospitalManagement/service/PatientService.java` — service layer; contains a small transactional example
- `src/main/java/com/project/hospitalManagement/type/BloodGroupType.java` — enum for blood groups

Project-specific patterns & gotchas
- Jakarta APIs: project uses `jakarta.persistence` (Spring Boot 4). Change imports to `jakarta.*` when modifying entities or configuration.
- Lombok: entities and service use Lombok (@Getter/@Setter/@RequiredArgsConstructor). The pom configures annotation processing — ensure your IDE has Lombok enabled.
- JPA mapping vs SQL seed mismatch: `Patient` is mapped to table `patient_tbl` (field names in camelCase). `data.sql` inserts into `patient` and uses snake_case columns (e.g. `date_of_birth`) and string blood-group values like `'A'`/`'AB'`. This will likely fail or produce unexpected results at runtime — check/align `data.sql` before relying on it.
- Enum mapping: `Patient.bloodGroup` has no `@Enumerated` annotation, so JPA default (ORDINAL) is used. `data.sql` uses text codes; these are incompatible without migration or explicit `@Enumerated(EnumType.STRING)`.
- Transaction example: `PatientService.getPatientById(Long)` calls `findById` twice and mutates the returned entity inside a `@Transactional` method. This is a deliberate demonstration of the persistence-context (first-level cache / entity identity) behaviour. When editing services, be mindful that repeated repository calls within the same transaction return the same managed instance.
- Database drivers: pom contains both PostgreSQL and MySQL drivers (both in runtime scope). The active DB is controlled entirely by `application.properties` (MySQL by default).
- Tests are integration-style: `src/test/java/.../PatientTest.java` uses `@SpringBootTest` and will start a Spring context and attempt DB access. There are no assertions — tests print entities. Expect tests to require DB connectivity.

> Security note
- `application.properties` contains a plaintext DB password. Treat it as a secret; if you edit or run CI, prefer using environment variables or a vault. The file uses `MYSQL_HOST` environment fallback for the host.

Quick troubleshooting checklist for agents
- If startup fails: check `spring.datasource.url` and ensure MySQL is reachable. Use `MYSQL_HOST` env var to point to the DB.
- If seed data fails: compare `data.sql` table/column names against `Patient` mapping (`patient_tbl`) and `bloodGroup` enum mapping.
- If tests fail due to missing Lombok-generated members: ensure annotation processing enabled in your IDE/build (pom already configures it for Maven).
- If changing enum handling: add `@Enumerated(EnumType.STRING)` to `Patient.bloodGroup` or rewrite `data.sql` to insert correct ordinal values.

Where to extend or look next
- Add controllers under `.../controller` (none exist currently) — current code demonstrates entity/repository/service layers only.
- Consider aligning `data.sql` or switching to Flyway/Liquibase for migrations (no migration tool present)

Example snippets from this repo
- Table mapping: `@Table(name = "patient_tbl", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email"))` — see `Patient.java`
- Transactional pattern: `@Transactional` on `PatientService.getPatientById` with two `findById` calls — see `PatientService.java`

If you need more
- Ask for a focused task (e.g., "align data.sql with JPA mapping", "add controller to expose patients", or "change enum mapping to STRING") and I will make the minimal, tested changes.

