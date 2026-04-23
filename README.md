# Auth-Module

*Maven based module for authentication and authorization in the application*

![Last Commit](https://img.shields.io/github/last-commit/pingmyheart/Auth-Module)
![Repo Size](https://img.shields.io/github/repo-size/pingmyheart/Auth-Module)
![Issues](https://img.shields.io/github/issues/pingmyheart/Auth-Module)
![Pull Requests](https://img.shields.io/github/issues-pr/pingmyheart/Auth-Module)
![License](https://img.shields.io/github/license/pingmyheart/Auth-Module)
![Top Language](https://img.shields.io/github/languages/top/pingmyheart/Auth-Module)
![Language Count](https://img.shields.io/github/languages/count/pingmyheart/Auth-Module)

## Architecture

**Authentication Module**: Main module that has the responsibility of authenticating users and issuing access tokens and
refresh tokens. It also provides an endpoint to validate access tokens for authorization purposes.
**Authorization Module**: Provides authorization annotations to protect resources based on user roles and permissions.

## API Contract

### Authentication API

Authentication api is basically the endpoint to obtain access token and refresh token for a user based on their
credentials.

- **Endpoint**: `/api/auth/login`
- **Method**: POST
- **RequestHeader**: `Authorization: Basic <base64-encoded-credentials>`

- **Response**:

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Refresh API

Refresh api is the endpoint to obtain new access token and refresh token based on the provided refresh token.

- **Endpoint**: `/api/auth/refresh`
- **Method**: POST
- **RequestHeader**: `Authorization: Bearer <accessToken>`
- **Request Body**:

```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

- **Response**:

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
``` 

### Logout API

Logout api simply invalidates the provided refresh token, so it can no longer be used to obtain new access tokens.

- **Endpoint**: `/api/auth/logout`
- **Method**: POST
- **RequestHeader**: `Authorization: Bearer <accessToken>`
- **Request Body**:

```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Validate API

Validate api is the internal endpoint used my `authorization-module` to validate the access token and obtain user
information for authorization purposes.

- **Endpoint**: `/api/auth/validate`
- **Method**: POST
- **Request Body**:

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

- **Response**:

```json
{
  "valid": true,
  "username": "user1",
  "roles": [
    "ROLE_USER",
    "ROLE_ADMIN"
  ]
}
``` 