GET http://localhost:8080/users


GET http://localhost:8080/user/1


GET http://localhost:8080/user/v1/1


GET http://localhost:8080/users/v2?username=Bret
GET http://localhost:8080/users/v2?id=1
GET http://localhost:8080/users/v2?email=Sincere@april.biz
GET http://localhost:8080/users/v2?id=1&username=Bret



POST http://localhost:8080/users
POST http://localhost:8080/v3/users
{
  "name": "Leanne Graham",
  "username": "Bret",
  "email": "Sincere@april.biz",
  "address": {
    "street": "Kulas Light",
    "suite": "Apt. 556",
    "city": "Gwenborough",
    "zipcode": "92998-3874",
    "geo": {
      "lat": "-37.3159",
      "lng": "81.1496"
    }
  },
  "phone": "1-770-736-8031 x56442",
  "website": "hildegard.org",
  "company": {
    "name": "Romaguera-Crona",
    "catchPhrase": "Multi-layered client-server neural-net",
    "bs": "harness real-time e-markets"
  }
}




PUT http://localhost:8080/user/v4/1
PUT http://localhost:8080/user/v5/1
{
  "name": "Leanne Graham",
  "username": "Bret",
  "email": "Sincere@april.biz",
  "address": {
    "street": "Kulas Light",
    "suite": "Apt. 556",
    "city": "Gwenborough",
    "zipcode": "92998-3874",
    "geo": {
      "lat": "-37.3159",
      "lng": "81.1496"
    }
  },
  "phone": "1-770-736-8031 x56442",
  "website": "hildegard.org",
  "company": {
    "name": "Romaguera-Crona",
    "catchPhrase": "Multi-layered client-server neural-net",
    "bs": "harness real-time e-markets"
  }
}




DELETE http://localhost:8080/user/v6/1