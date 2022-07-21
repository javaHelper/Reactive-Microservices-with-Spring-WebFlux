# Spring Boot Reactive Microservices

<img width="661" alt="Screenshot 2022-07-21 at 10 36 37 AM" src="https://user-images.githubusercontent.com/54174687/180134052-487adb4b-794c-4c4e-ae6b-8534512da665.png">

# Product Service

- Find All Product: http://localhost:8091/product/all
- Find Product By Id: http://localhost:8091/product/62d798a6c9a0e06e363b0bef
- Delete Product Bt Id: http://localhost:8091/product/62d798a6c9a0e06e363b0bef
- Find Product Price By Range: http://localhost:8091/product/price-range?min=500&max=1100
- Create Product

```sh
curl --location --request POST 'http://localhost:8091/product' \
--header 'Content-Type: application/json' \
--data-raw '{
    "description" : "slr-camera",
    "price": 750
}'
```

- Update Product

```sh
curl --location --request PUT 'http://localhost:8091/product/62d798a6c9a0e06e363b0bef' \
--header 'Content-Type: application/json' \
--data-raw '{
    "description": "slr-camera",
    "price": 850
}'
```

-------------

# User Service

- Find Users: http://localhost:8092/user/all
- Find user By Id: http://localhost:8092/user/1
- Delete User By Id: http://localhost:8092/user/1 (This will delete User tx as well)
- Save User

```sh
curl --location --request POST 'http://localhost:8092/user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "Prateek1",
    "balance": 100
}'
```


- Update User

```sh
curl --location --request PUT 'http://localhost:8092/user/5' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "Prateek",
    "balance": 1111
}'
```

- Create Tx

```sh
curl --location --request POST 'http://localhost:8092/user/transaction' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId" : 1,
    "amount": 250
}'
```

- Get Tx By Id: http://localhost:8092/user/transaction?userId=1

--------



