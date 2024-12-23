# Spring Boot jooq sample with r2dbc

## Generating code from database

### Export required env variables
```shell
export JOOQ_CODEGEN_JDBC_URL="jdbc:postgresql://localhost:5432/postgres" && \ 
export JOOQ_CODEGEN_JDBC_USER="my_user" && \ 
export JOOQ_CODEGEN_JDBC_PASSWORD="my_password" && \
export JOOQ_CODEGEN_INPUT_SCHEMA="my_schema"
```
### Run code generation
```shell
./gradlew jooqCodegen
```

## Run the app

### Export required env variables
```shell
export SPRING_R2DBC_URL="r2dbc:postgresql://localhost:5432/postgres" && \
export SPRING_R2DBC_USERNAME="my_user" && \
export SPRING_R2DBC_PASSWORD="my_password"
```

### Run spring boot app
```shell
./gradlew bootRun
```
