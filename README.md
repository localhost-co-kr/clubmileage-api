## How to run?

#### Step 1. Run ELK & Kafka with Docker Compose
* **start-up**
`docker-compose build && docker-compose up -d`

* **stop**
`docker-compose down --volume`
  
#### Step 2. Run Application
* **start-up**
`./gradlew build && java -jar build/libs/clubmileage-api-0.1-SNAPSHOT.jar`

---

## Application Service Endpoint

* **swagger**
`http://localhost:8080/webjars/swagger-ui/index.html`

* **h2**
`http://localhost:8082/login.jsp`

접속정보

|JDBC URL|사용자명|비밀번호|
|:-------|:-----|:-----|
|jdbc:h2:mem:testdb|sa|triple|

* **kibana**
`http://localhost:5601`

---

## Screenshot

* **Monitoring (ELK with Kafka)**
<img src="https://user-images.githubusercontent.com/90884449/174804365-a078f17d-e983-4a94-8df4-1d07d04f0bfe.png" width="600" height="350"/>

* **Test Report**
<img src="https://user-images.githubusercontent.com/90884449/174693873-56528633-5ff8-4770-80ef-5acce7e1f84b.png" width="600" height="350"/>
<img src="https://user-images.githubusercontent.com/90884449/174693900-16f424b5-e716-4dd0-8fb8-e48df825e611.png" width="600" height="350"/>
<img src="https://user-images.githubusercontent.com/90884449/174693949-6f820d2f-e554-4e39-a6bc-a526701c158e.png" width="600" height="350"/>
<img src="https://user-images.githubusercontent.com/90884449/174693953-ed4297b5-c482-45f2-9c37-febe76325bec.png" width="600" height="350"/>
