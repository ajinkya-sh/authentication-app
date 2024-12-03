# Project Setup and API Usage Guide

 ## Step 1: Clone the Repository 
 `git clone https://github.com/your-repo-name/project-name.git` 

## Step 2: Navigate to the Project Root

`cd authentication-app` 

## Step 3: Start the Application Using Docker Compose

`docker-compose up -d` 

The application will be available at `http://localhost:8080`.
Please note : If the spring application comes down please re run docker compse up command again

Signup and SignIn APIs are added.
Authorization of token happens.
Revocation of token will happen after 1 hour.
Generate Token API will renew the token before it expires.

----------

## API Endpoints

### 1. **Sign Up**

**Request:**

curl --location 'localhost:8080/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email" : "kushal@gmail.com",
    "password" : "qwerty@123",
    "name" : "kushal"
}'

**Response:**


`{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGdtYWlsLmNvbSIsImV4cCI6MTczMzIzMjAyMiwiaWF0IjoxNzMzMjI4NDIyfQ.33hm5PHMpeJYwaDQXBhgoS5WaravUWyczYj0ArjW4B4",
    "httpCode": 201,
    "message": "Token created successfully"
}` 

----------

### 2. **Sign In**

**Request:**


curl --location 'localhost:8080/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email" : "johndoe@gmail.com",
    "password" : "john@123"
}' 

**Response:**


`{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGdtYWlsLmNvbSIsImV4cCI6MTczMzIzMjA4NywiaWF0IjoxNzMzMjI4NDg3fQ.CenDEt4F5qLjjXvCvqbHTT9Cc_Q6Do1sU_1iPVQH_gE",
    "httpCode": 200,
    "message": "Token created successfully"
}` 

----------

### 3. **Generate New Token**

**Request:**

curl --location 'localhost:8080/auth/generateToken' \
--header 'Content-Type: application/json' \
--data '{
    "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGdtYWlsLmNvbSIsImV4cCI6MTczMzIzMzU5OSwiaWF0IjoxNzMzMjI5OTk5fQ.vA3QN4gBkBxd6Ip4GND18mKVX8MZaWJNnnbD65QrO2A"
}'

**Response:**


`{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGdtYWlsLmNvbSIsImV4cCI6MTczMzIzMjE0MiwiaWF0IjoxNzMzMjI4NTQyfQ.eGEpzOluhgd675eUcMYIhBvVuJRRb--NDhXMgQX1XqY",
    "httpCode": 200,
    "message": null
}` 

**Note:** If an invalid token is passed, the API will return a `500 Internal Server Error`.

----------

### 4. **Access Secured Endpoint**

**Request with Valid Token: This the api which is secured and one has to put tokens from signin or signup or generateToken api**

The token's expiration limit is of 1 hour.

curl --location 'localhost:8080/api/secure' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGdtYWlsLmNvbSIsImV4cCI6MTczMzIzMzU5OSwiaWF0IjoxNzMzMjI5OTk5fQ.vA3QN4gBkBxd6Ip4GND18mKVX8MZaWJNnnbD65QrO2A'

**Response:**

`This is a secured endpoint` 

**Request Without Token:**

curl --location 'localhost:8080/api/secure' \
--header 'Content-Type: application/json'

**Response:**

`{
    "timestamp": "2024-12-03T12:24:17.143+00:00",
    "status": 401,
    "error": "Unauthorized",
    "path": "/api/secure"
}`
