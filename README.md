# Meet - Android Multi-Module Template

🌐 Languages:
- 🇬🇧 English (default)
- 🇪🇸 [Español](README.es.md)

This project is a modern Android **base template**, designed with a focus on scalability, maintainability, and modularization. It uses a robust **multi-module** architecture following **Clean Architecture** principles, allowing for a clear separation of concerns and facilitating teamwork or project growth.

The goal of this template is to provide a pre-configured structure with industry best practices, including dependency injection, modern navigation, and strict code quality controls.

---

# Architecture
### Module and Relationship Diagram

```mermaid
graph TD
    subgraph App ["Application Level"]
        APP[:app]
    end

    subgraph Features ["Feature Modules"]
        direction TB
        subgraph Auth ["auth (Authentication)"]
            auth_p[:auth:presentation] --> auth_d[:auth:domain]
            auth_da[:auth:data] --> auth_d
        end

        subgraph AppRoot ["app-root (Main Feature)"]
            root_p[:app-root:presentation] --> root_d[:app-root:domain]
            root_da[:app-root:data] --> root_d
        end
    end

    subgraph Core ["Core Level (Shared)"]
        core_nav[:core:navigation]
        core_di[:core:di]
        core_ui[:core:ui]
        core_vm[:core:viewmodels]
        core_com[:core:common]
    end

    %% Main Dependency Relationships
    APP --> Auth
    APP --> AppRoot
    APP --> core_di
    APP --> core_nav

    %% Feature to Core Dependencies
    Auth --> Core
    AppRoot --> Core

    %% Tools
    detekt[:detekt-architecture-rules] -.-> APP
    detekt -.-> Auth
    detekt -.-> AppRoot
```

### Structure Summary

* **`:app`**: The main orchestrator. It depends on all features (`auth`, `app-root`) and configures global dependency injection and navigation.
* **Features (`auth`, `app-root`)**: Each is internally divided following Clean Architecture:
    * **`:presentation`**: Contains the UI (Compose) and ViewModels. Depends on `:domain`.
    * **`:data`**: Implements repositories and data sources (API, Database). Depends on `:domain`.
    * **`:domain`**: The business logic core (UseCases and Models). No external dependencies from higher layers.
* **`:core`**: Contains reusable code for any module (common UI components, navigation utilities, ViewModel base logic, and dependency injection).
* **`:detekt-architecture-rules`**: Specialized module to ensure architecture rules are met through static analysis.

# Features
- Multi-module project
- Clean Architecture
- Koin
- Data Store
- Compose Navigation
- Detekt
- Detekt custom rules
- Detekt Pre-Commit Hook

### Warning!! 💥

This project contains a pre-commit and pre-push hook to ensure code quality. The following steps are necessary to setup the quality control:

1. Change hooksPath:

```bash
$ git config core.hooksPath .githooks
```

2. Add permissions to the hook:

```bash
$ chmod +x .git/hooks/pre-commit .git/hooks/pre-push
```

To fix the errors reported by the `pre-commit` hook, run this command in your terminal:

```bash
$ ./gradlew detekt
```

# Kotzilla
Kotzilla is an SDK for monitoring and optimizing performance. You must install the Koin plugin and connect it to your account. The project includes a `kotzilla.sample.json` file where you must add your account credentials.
