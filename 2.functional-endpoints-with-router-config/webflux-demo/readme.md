# Functional Endpoint!

GET -> `http://localhost:8080/router/square/5`

GET -> `http://localhost:8080/router/table/5`

Response:

```json
[
    {
        "localDate": "2022-07-18",
        "output": 5
    },
    {
        "localDate": "2022-07-18",
        "output": 10
    },
    {
        "localDate": "2022-07-18",
        "output": 15
    },
    {
        "localDate": "2022-07-18",
        "output": 20
    },
    {
        "localDate": "2022-07-18",
        "output": 25
    },
    {
        "localDate": "2022-07-18",
        "output": 30
    },
    {
        "localDate": "2022-07-18",
        "output": 35
    },
    {
        "localDate": "2022-07-18",
        "output": 40
    },
    {
        "localDate": "2022-07-18",
        "output": 45
    },
    {
        "localDate": "2022-07-18",
        "output": 50
    }
]
```

GET -> `http://localhost:8080/router/table/5/stream`

POST ->

```sh
curl --location --request POST 'http://localhost:8080/router/multiply' \
--header 'some-key: some-value' \
--header 'Content-Type: application/json' \
--data-raw '{
    "first" : 5,
    "second": 5
}'
```

GET -> `http://localhost:8080/router/square/7/validation`

Response:

```json
{
    "errorCode": 100,
    "input": 7,
    "message": "allowed range is 10 - 20"
}
```
