# 社团招新管理平台

数据库：

```shell
docker run -d \
    --name db-for-joinus \
    -e POSTGRES_USER=joinus \
    -e POSTGRES_PASSWORD=123456 \
    -e POSTGRES_DB=joinus_dev \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v joinus-data:/var/lib/postgresql/data \
    -p 5432:5432 \
    postgres:14
```

部署：

```shell
./gradlew bootJar
```

```shell
docker build -t joinus:v1 .
```

```shell
# 运行数据库
docker run -d \
    --name db-for-joinus \
    -e POSTGRES_USER=joinus \
    -e POSTGRES_PASSWORD=123456 \
    -e POSTGRES_DB=joinus \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v joinus-data:/var/lib/postgresql/data \
    --network=joinus \
    --restart=always \
    postgres:14
```

```shell
docker run -d \
    --name joinus \
    -p 8080:8080 \
    -e PG_HOST=db-for-joinus \
    -e PG_DATABASE=joinus \
    -e PG_USER=joinus \
    --network=joinus \
    --restart=on-failure:10 \
    joinus:v1
```
