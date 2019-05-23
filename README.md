# Pattern Recognition Feature Points
A simple RESTful software service that given a set of N feature points in the plane, determine every line that contains N or more of the points, and
return all points involved.

It consists of one controller called `FeaturePointsController` that expose the following endpoint:

- Add a point to the space:
```javascript
POST /point with body { "x": ..., "y": ... }
```
- Get all points in the space:
```javascript
GET /space
```
- Get all line segments passing through at least N points:
```javascript
GET /lines/{n}
```
- Remove all points from the space:
```javascript
DELETE /space
```

JSON model:
- LineDTO (points)
- PointDTO (x, y)

## Toolset
- Spring Boot (embedded Tomcat)
- Spring MVC
- Maven
- Git
- Junit
- Mockito
- Hamcrest
- springfox-swagger2

### Testing
- Service integration test with Mockito
- Spring MVC integration test with Spring WebMvcTest and MockMvc Object

## Prerequisites
- JRE 12
- GIT
- Maven

## Quick start
Use the following command:
- `git clone https://github.com/aborraccino/pattern-recognition-feature-points.git`
- `cd pattern-recognition-feature-points/`
- `mvn clean install`
- `java -jar target/feature-points-1.0.0-SNAPSHOT.jar`
- the embedded Tomcat starts at `http://localhost:8080`
- swagger GUI is available on `http://localhost:8080/swagger-ui.html`