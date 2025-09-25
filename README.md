# Prestamos 

Para arrancar este proyecto hay que tener en cuenta la configuracion del archivo [application.yml](./applications/app-service/src/main/resources/application.yaml) (Click para ir) y colocar la correcta de su ambiente

### Para este proyecto se uso postgres como base de datos

Loguear

GET http://localhost:8080/api/v1/signin
```json
{
  "email": "admin@test.com",
  "password": "123"
}
````

Listar prestamos

GET http://localhost:8080/api/v1/loans/:userId (http://localhost:8080/api/v1/loans/1)
```json
{
  "email": "admin@test.com",
  "password": "123"
}
````
Crear prestamo

POST http://localhost:8080/api/v1/loans
```json
{
  "userId": "1",
  "amount": "1000",
  "paymentDate": "11/09/2025"
}
```

Actualizar prestamo

PUT http://localhost:8080/api/v1/loans
````json
{
  "loanId": "1",
  "newState": 2
}
````