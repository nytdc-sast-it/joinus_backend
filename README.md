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
