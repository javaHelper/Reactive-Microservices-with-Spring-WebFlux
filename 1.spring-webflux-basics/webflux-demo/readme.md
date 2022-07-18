# Spring WebFlux

GET -> `http://localhost:8080/math/square/7`

GET -> `http://localhost:8080/math/table/7`

Response:

```json
[
    {
        "localDate": "2022-07-18",
        "output": 7
    },
    {
        "localDate": "2022-07-18",
        "output": 14
    },
    {
        "localDate": "2022-07-18",
        "output": 21
    },
    {
        "localDate": "2022-07-18",
        "output": 28
    },
    {
        "localDate": "2022-07-18",
        "output": 35
    },
    {
        "localDate": "2022-07-18",
        "output": 42
    },
    {
        "localDate": "2022-07-18",
        "output": 49
    },
    {
        "localDate": "2022-07-18",
        "output": 56
    },
    {
        "localDate": "2022-07-18",
        "output": 63
    },
    {
        "localDate": "2022-07-18",
        "output": 70
    }
]
```

GET -> `http://localhost:8080/reactive-math/square/7`

GET -> `http://localhost:8080/reactive-math/table/7`

GET -> `http://localhost:8080/reactive-math/table/7/stream`

POST -> 
```curl --location --request POST 'http://localhost:8080/reactive-math/multiply' \
--header 'some-key: some-value' \
--header 'Content-Type: application/json' \
--data-raw '{
    "first" : 5,
    "second": 5
}'```

----------
- Exception Checking

GET -> `http://localhost:8080/reactive-math/square/8/throw`

       `http://localhost:8080/reactive-math/square/12/throw`
       
       
GET -> `http://localhost:8080/reactive-math/square/8/mono-error`

       `http://localhost:8080/reactive-math/square/12/mono-error`
       
GET -> `http://localhost:8080/reactive-math/square/8/assignment`

       `http://localhost:8080/reactive-math/square/12/assignment`  




