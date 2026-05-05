# Meet - Android Multi-Module Template

🌐 Idiomas:
- 🇬🇧 [English](README.md)
- 🇪🇸 Español (actual)

Este proyecto es una **plantilla base (template)** para aplicaciones Android moderna, diseñada con un enfoque en la escalabilidad, mantenibilidad y modularización. Utiliza una arquitectura **multi-módulo** robusta siguiendo los principios de **Clean Architecture**, permitiendo una separación clara de responsabilidades y facilitando el trabajo en equipo o el crecimiento del proyecto.

El objetivo de esta plantilla es proporcionar una estructura preconfigurada con las mejores prácticas de la industria, incluyendo inyección de dependencias, navegación moderna y estrictos controles de calidad de código.

> [!IMPORTANT]
> **Prueba de Concepto:** Este proyecto es una PoC que explora la **modularización extrema**. Cada capa individual (datasource, repository, usecases, etc.) está aislada en su propio módulo de Gradle para asegurar una pureza arquitectónica absoluta y un aislamiento total.
> **Compromiso (Trade-off):** Este enfoque conlleva un alto "overhead" de configuración en Gradle y tiempos de sincronización significativamente más largos. Está diseñado con fines educativos o para proyectos de gran escala donde el cumplimiento estricto de los límites entre capas es prioritario frente a la velocidad de compilación.

---

# Arquitectura
### Diagrama Simplificado de Módulos y Relaciones

> [!NOTE]
> Para mayor claridad, este diagrama ha sido simplificado: se ha eliminado la funcionalidad de **auth** y no se muestran las dependencias directas de **:core:di** y **:core:common** con el resto de los módulos para reducir la densidad visual.

```mermaid
graph TD
    subgraph App_Level [Aplicación]
        APP[:app]
    end

    subgraph Core_Level [Nivel Core]
        core_di[:core:di]
        core_nav[:core:navigation]
        core_ui[:core:ui]
        core_vmb[:core:viewmodels]
        core_com[:core:common]
    end

    subgraph Feature_AppRoot [Funcionalidad: app-root]
        root_ui[:app-root:presentation:ui]
        root_vm[:app-root:presentation:viewmodels]
        root_uc[:app-root:domain:usecases]
        root_repo[:app-root:domain:repository]
        root_models[:app-root:domain:models]
        root_data[:app-root:data:repository]
        root_ds[:app-root:data:datasource]
    end

    %% Dependencias de APP
    APP --> core_di
    APP --> core_ui
    APP --> root_ui
    APP --> root_vm

    %% Flujo de App-Root
    root_ui --> core_ui
    root_ui --> core_vmb
    root_ui --> core_nav
    root_ui --> root_vm

    root_vm --> core_vmb
    root_vm --> root_models
    root_vm --> root_uc

    root_uc --> root_repo
    root_uc --> root_models

    root_data --> root_models
    root_data --> root_repo
```

### Resumen de la Estructura

* **`:app`**: Es el orquestador principal. Depende de todas las funcionalidades (`auth`, `app-root`) y configura la inyección de dependencias global y la navegación.
* **Funcionalidades (`auth`, `app-root`)**: Cada una está dividida internamente siguiendo Clean Architecture:
  * **`:presentation`**: Contiene la UI (Compose) y los ViewModels. Depende de `:domain`.
  * **`:data`**: Implementa los repositorios y fuentes de datos (API, Base de Datos). Depende de :domain.
  * **`:domain`**: El núcleo de la lógica de negocio (UseCases y Modelos). No tiene dependencias externas de capas superiores.
* **`:core`**: Contiene el código reutilizable por cualquier módulo (componentes UI comunes, utilidades de navegación, lógica base de ViewModels e inyección de dependencias).
* **`:detekt-architecture-rules`**: Módulo especializado para asegurar que las reglas de arquitectura se cumplan mediante análisis estático.

# Características
- Proyecto Multi-módulo
- Clean Architecture
- Koin
- Data Store
- Compose Navigation
- Detekt
- Reglas personalizadas de Detekt
- Hook de Pre-Commit para Detekt

### ¡Atención! 💥

Este proyecto contiene hooks de pre-commit y pre-push para asegurar la calidad del código. Es necesario realizar estos pasos para configurar el control de calidad:

1. Cambiar el hooksPath:

```bash
$ git config core.hooksPath .githooks
```

2. Dar permisos a los hooks:

```bash
$ chmod +x .git/hooks/pre-commit .git/hooks/pre-push
```

Para corregir los errores que reporte el hook `pre-commit`, ejecuta esto en la terminal:

```bash
$ ./gradlew detekt
```

# Kotzilla
Kotzilla es un SDK para monitorear y optimizar el rendimiento. Para configurarlo:

1. Localiza el archivo `app/kotzilla.sample.json`.
2. Crea una copia llamada `app/kotzilla.json` (este archivo está ignorado por Git para proteger tus claves).
3. Sustituye los valores de ejemplo por las credenciales reales de tu cuenta.

Para más información, asegúrate de tener instalado el plugin de Koin y conectado a tu cuenta.
