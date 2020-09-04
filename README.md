# RoomsAndLamps

### Technologies: 
> Spring Boot, Spring Data, Hibernate, JPA, REST, Spring MVC, MySQL, JUnit, Maven, Thymeleaf, JSON
### Java version: 14

### Start the app:
> com.github.zhalabkevich.roomservice.RoomServiceApplication 
## Main Settings
### App:
> src/main/resourses/application.properties
### SQL:
> src/main/resourses/script.sql

## Test Settings
### App:
> src/test/resourses/application.properties
### SQL scripts:
> src/test/resourses/create-country-before.sql 
> src/test/resourses/create-room-before.sql

### UI + Spring MVC
> - Main page: http://localhost:8080/api/
> - Room page: http://localhost:8080/api/id

### REST with some UI

> - Get all countires: GET http://localhost:8080/countries/ 
> - Get all rooms: GET http://localhost:8080/rooms/ 
> - Get room by id: GET http://localhost:8080/rooms/id

> - Add new country: POST http://localhost:8080/countries/country
> - Add new countries: POST http://localhost:8080/countries/countries
> - Add new room: POST  http://localhost:8080/rooms/room

> - Update room: PUT http://localhost:8080/rooms/id


