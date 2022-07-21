# Functional Endpoint!

GET -> `http://localhost:8080/router/square/5`

Response:

```json
only 10-19 allowed
```
------

GET -> `http://localhost:8080/router/square/19`

Response:

```json
{
    "localDate": "2022-07-18",
    "output": 361
}
```

- Below as per predicate we're allowing only `.GET("square/{input}", RequestPredicates.path("*/1?"), requestHandler::squareHandler)` one more character to be allowed after 1st character.

GET -> `http://localhost:8080/router/square/191`


GET -> `http://localhost:8080/calculator/5/4`

Response:

```
OP should be + - * /
```

GET -> 

```
curl --location --request GET 'http://localhost:8080/calculator/5/4' \
--header 'OP: +'
```

GET -> 

```
curl --location --request GET 'http://localhost:8080/calculator/5/4' \
--header 'OP: -'
```

GET -> 

```
curl --location --request GET 'http://localhost:8080/calculator/5/4' \
--header 'OP: /'
```
```