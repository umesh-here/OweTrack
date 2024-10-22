# OweTrack - Payment Splitting Application

Welcome to the Payment Splitting Application! This Spring Boot application is designed to simplify splitting payments among users, track balances, and record transactions seamlessly. 

## Table of Contents
- [OweTrack - Payment Splitting Application](#owetrack---payment-splitting-application)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Getting Started](#getting-started)
  - [API Documentation](#api-documentation)
    - [User APIs](#user-apis)
      - [Add a New User](#add-a-new-user)
      - [Get User Details](#get-user-details)
      - [Get Balances for a User](#get-balances-for-a-user)
      - [Get User Transactions](#get-user-transactions)
    - [Record APIs](#record-apis)
      - [Record an Equal Payment](#record-an-equal-payment)
      - [Record an Unequal Payment](#record-an-unequal-payment)
  - [Contributing](#contributing)

## Introduction
This application helps users split payments equitably and manage outstanding balances. It provides a robust set of APIs to handle user management, balance inquiries, and recording transactions.

## Getting Started
To get a local copy up and running, follow these simple steps:

1. **Clone the repository**: 
    ```bash
    git clone https://github.com/umesh-here/owetrack.git
    ```
2. **Navigate to the project directory**:
    ```bash
    cd your-repo
    ```
3. **Install the dependencies**:
    ```bash
    mvn install
    ```
4. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

## API Documentation
This section provides details about the available endpoints.

### User APIs
#### Add a New User
- **Endpoint**: `/api/user/add`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "name": "John Doe",
        "email": "john.doe@example.com"
    }
    ```
- **Response**:
    - `201 Created`: User successfully created.
    - `409 Conflict`: Email is already in use.

#### Get User Details
- **Endpoint**: `/api/user/detail/{id}`
- **Method**: `GET`
- **Response**:
    ```json
    {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com"
    }
    ```

#### Get Balances for a User
- **Endpoint**: `/api/user/due/{id}`
- **Method**: `GET`
- **Response**:
    ```json
    [
        "Alice owes John 50.0",
        "John owes Bob 20.0"
    ]
    ```

#### Get User Transactions
- **Endpoint**: `/api/user/transactions/{id}`
- **Method**: `GET`
- **Response**:
    ```json
    [
        {
            "payerId": 1,
            "amount": 100.0,
            "title": "Dinner",
            "participants": [
                {
                    "id": 2,
                    "amount": 50.0
                }
            ]
        },
        {
            "payerId": 2,
            "amount": 50.0,
            "title": "Cab",
            "participants": [
                {
                    "id": 1,
                    "amount": 25.0
                }
            ]
        }
    ]
    ```

### Record APIs
#### Record an Equal Payment
- **Endpoint**: `/api/record/equal-payment`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "payerId": 1,
        "amount": 100.0,
        "title": "Dinner",
        "participants": [2, 3]
    }
    ```
- **Response**: `200 OK`

#### Record an Unequal Payment
- **Endpoint**: `/api/record/unequal-payment`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "payerId": 1,
        "totalAmount": 100.0,
        "title": "Dinner",
        "participantAmounts": {
            "2": 30.0,
            "3": 70.0
        }
    }
    ```
- **Response**: `200 OK`

## Contributing
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**. Please fork the repository and create a pull request.


