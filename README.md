# Mono-Backend

Between Freedom and Space backend monolith. Supports running in Docker. Available Grafana metrics.
All configuration data and properties are in **HOCON** [file](./src/main/resources/application.conf).

The Project structure implies an easy division into microservices. 
Root folders represents major features such as `access`, `auth`, `profiles` etc.
There are some common modules such as `common` and `util`.

The service are easily expandable and high performance. 
The application is codded according to the most modern practices and using the best architecture patterns.

## Features:
* Kotlin's frameworks and infrastructure:
  1. **Ktor**
  2. **Exposed**
  3. **Build Gradle kts**
  4. **Koin**
  5. **Coroutines**
* Gradle 
  1. Supports Kotlin script
  2. Supports [buildSrc](./buildSrc)
  3. [Convenient](./buildSrc/src/main/kotlin/dependencies/Dependencies.kt) work with dependencies and versions
* Docker
  1. Ability to run the application in any environment
  2. Easy integration into Kubernetes
* Microservices support
    1. All application features designed as separate modules.
    2. General logic moved to separate libraries
* Clean architecture
  1. The application is written according to the best practices of clean architecture
  2. All logical layers are separated into separate folders
  3. Each layer has its own API and data models
* Security and encryption
  1. We are not storing your sensitive personal data
  2. All traffic is encrypted
  3. We use modern encrypting algorithms to interact with API
* Simplicity
  1. All files with code is less than 350 lines
  2. Application is written by modern frameworks 
* High performance
  1. Service is powered by lightweight Ktor
  2. Minimum data manipulation 
  3. Maximum asynchronous interaction

## Git Flow:
For our developers and managers we use [YouTrack](https://www.jetbrains.com/youtrack/) as project management tool.
So the **common** flow is:

1. Checkout `develop` branch
2. Create a new branch with pattern: `feature/<TASK-NAME>` or `bugfix/<TASK-NAME>`
3. Add feature code, write tests and check performance
4. Commit changes with next description: `<TASK-NAME>: Your short description`
5. Make `git push` and create pull request to `develop` branch. Add some description.
6. Wait until reviewers approve
7. Merge and deploy to testing stage.

**Release** flow:
1. Checkout `develop` branch
2. Create a new branch with pattern: `release/v<VERSION>`
3. Create pull request to `main` branch
4. Wait until reviewers approve
5. Deploy release to production stage and merge the pull request

**Hotfix** flow:
1. Create a new branch with pattern: `hotfix/<TASK-NAME>`
2. Add code and commit changes
3. Create pull request and wait approve
4. Merge


If you want to add some functionality, and **you are not in our team**:
1. Create a new branch from `develop` with pattern: `contribution/<SHORT-DESCRIPTION>`
2. Add all code, write tests and check performance
3. Commit changes with next description: `CONTRIBUTION: Your short description`
4. Make `git push` and create pull request to `develop` branch. Add complete description.
5. Wait until reviewers approve
6. Pull request will be automatically merged

## How to launch:
1. Set up Docker on your machine and run `run.sh`
2. Use `gradle` to run the application manually. You can just open [Application.kt](./src/main/kotlin/com/between_freedom_and_space/mono_backend/app/Application.kt) file and click on green run icon.
3. Build java jar with gradle `package` task and run `java -jar mono-backend.jar` in terminal.

#### Created and powered by Ferum-bot.
