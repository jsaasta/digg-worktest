# Code test for DIGG

## Overview

* Frontend application (Vue 3) in `/digg-frontend`.
* Backend REST API (Quarkus, tested with openjdk-21.0.4) in `/digg-rest-api`

Overall I didn't focus too much on error handling or validation given the scope and trying to keep it minimal.
Basic error handling and returning for example HTTP Status codes 200/201 or 500.

Since you only (unsure if using a database was "allowed") wanted internal storage in the service, I just used an internal list to store the users. However, I wanted to try Panache a little, so I added a h2-jdbc-driver aswell. By implementing a property or flag to swap between beans, h2 should work seamless. (Out of scope).

"Kept it simple".

## How to run

To run both backend and frontend:
1. `cd ./digg-codetest`
2. `docker compose up --build`

Backend is accessible through `localhost:8080`

Frontend is accessible through `localhost:5173`