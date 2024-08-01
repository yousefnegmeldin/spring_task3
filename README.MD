# Hotel Reservation System

## SETUP

---
- create a mysql schema/database called "task3"
- run code
- import postman collection found in root of the repo


## API Endpoints

---

### User controller

#### GET USER BY EMAIL
#### GET http://localhost:8080/user/{email}

returns 200 with user data if user is found
returns 404 if user is not found


#### CREATE USER
#### POST http://localhost:8080/user

```json
{
  "email": "john@example.com",
  "name":  "john doe",
  "phone": "01023122412"
}
```

---
### Room controller

#### GET ALL AVAILABLE ROOMS
#### GET http://localhost:8080/room/available

returns 200 with all rooms as an array of objects