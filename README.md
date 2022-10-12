# Monolithic backend for TX Internship Travel application.

Application will be used by employees to request and office managers (OM) to process daily allowance and travel expenses.

## First time setup

1. Install [Software Development Kit Manager](https://sdkman.io/)
2. Install Java and Maven
   ```shell
   sdk install 11.0.12-sem
   sdk install maven 3.8.5
   ```
3. Install [Docker service](https://docs.docker.com/get-docker/)
4. Compile project (~1min) - run the following command from project root:
   ```shell
    mvn spotless:apply clean verify 
   ```

## Run local Docker setup with necessary services

This command (from project root) starts a default stack (including MariaDB):

```shell
./_environment/docker/docker-compose.yaml up
```

## JWT tokens for executing HTTP requests

In ./_rest-client/ we defined `http` files used to execute http calls to APIs exposed by services. JWTs used for these 
calls are defined in rest-client.env.json (placeholders `token_employee` and `token_office_manager`).
In order to generate a dummy Bearer token, go to [jwt.io](https://jwt.io/) and copy the generated token into the 
placeholder in `travel.http` i.e. `token_employee`
