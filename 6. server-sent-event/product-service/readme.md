#

- install mongodb

```sh
version: "3"
services:
  mongo:
    image: mongo
    ports:
      - 27017:27017
    volumes:
      - ./mongo:/data/db  
  mongo-express:
    image: mongo-express
    ports:
      - 8081:8081
Thanks.
```

- install Postgres

```
version: "3"
services:
  postgres:
    image: postgres
    container_name: postgres
    environment:
      - POSTGRES_USER=vinsguru
      - POSTGRES_PASSWORD=admin
      - POSTGRES_DB=userdb
    volumes:
      - ./docker-volume/db:/var/lib/postgresql/data
    ports:
      - 5432:5432
  pgadmin:
    image: dpage/pgadmin4
    container_name: pgadmin
    environment:
      - PGADMIN_DEFAULT_EMAIL=admin@vinsguru.com
      - PGADMIN_DEFAULT_PASSWORD=admin
    volumes:
      - ./docker-volume/pgadmin:/var/lib/pgadmin
    ports:
      - 9000:80
```