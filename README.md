# selenium-automation
### Project configuration
- Clone project from github repository `git clone https://github.com/faizulcse/selenium-automation.git`
- Go to project root directory `cd ~/selenium-automation`
- Open `config.properties` file from `/src/main/resources`
- Update necessary property values
```properties
    BROWSER=chrome
    HEADLESS=false
    FLUENT_WAIT=5
    POLLING_DELAY=100
    IMPLICITLY_WAIT=0
    SCROLL_BY_PIXEL=200
    SCROLL_COUNT_MAX=10
    SCROLL_INTERVAL_DELAY=10
    SCREENSHOT_ON_FAILURE=true
    BASE_URL=https://www.phptravels.net
    HUB_ADDRESS=hub:4444
```
### Running Tests locally with selenium standalone server
- Open terminal or cmd
- Go to project root directory `cd ~/selenium-automation`
- Run selenium standalone server `java -jar selenium-server-standalone-*.jar -port 4444`
- On `/src/main/resources/config.properties` set `HUB_ADDRESS=localhost:4444`
- Run tests suite with `mvn clean test -Dremote=true`
##### Example
- Run all tests of a class `mvn clean test -Dremote=true -Dtest='LoginTest'`
- Run test method of a class `mvn clean test -Dremote=true -Dtest='LoginTest#loginAndLogoutTest'`
- To hide the console warning add `-q` flag `mvn clean test -q`

### Running Tests in Docker Container
- Open docker app
- Open terminal or cmd
- Go to project root directory `cd ~/selenium-automation`
- On `/src/main/resources/config.properties` set `HUB_ADDRESS=hub:4444`
- Set `TagName = "phptravels"`
- Docker up `docker-compose -p ${TagName} up --remove-orphans -d`
- Docker scale `docker-compose -p ${TagName} scale firefox=5 chrome=5`
- Docker build image `docker build --tag ${TagName}_image .`
- Docker container run with
```commandline
    docker run -it --network ${TagName}_default ${TagName}_image mvn clean test -Dremote=true
    'OR'
    docker run -it --network ${TagName}_default ${TagName}_image mvn clean test -Dremote=true -Dtest='LoginTest#loginAndLogoutTest'
```
- Docker down `docker-compose -p ${TagName} down --remove-orphans`
- Docker container remove `docker rm ${TagName}_container`
- Docker image remove `docker rmi ${TagName}_image -f`

### Project directory structure
```cmd
.
├── driver
│   ├── chromedriver
│   ├── chromedriver.exe
│   ├── geckodriver
│   └── geckodriver.exe
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── phptravels
│   │   │   │       ├── BasePage.java
│   │   │   │       ├── BookingDetailsPage.java
│   │   │   │       ├── DashboardPage.java
│   │   │   │       ├── HomePage.java
│   │   │   │       ├── LoginPage.java
│   │   │   │       ├── Page.java
│   │   │   │       └── SignUpPage.java
│   │   │   └── utils
│   │   │       ├── BrowserUtils.java
│   │   │       ├── EventListener.java
│   │   │       ├── LocatorUtils.java
│   │   │       ├── PathUtils.java
│   │   │       ├── Utils.java
│   │   │       └── WindowHandler.java
│   │   └── resources
│   │       └── config.properties
│   └── test
│       └── java
│           ├── test
│           │   ├── BaseTest.java
│           │   ├── BookingTest.java
│           │   ├── LoginTest.java
│           │   ├── SignUpTest.java
│           │   └── Test.java
│           └── testdata
│               ├── SignupData.java
│               ├── SiteData.java
│               ├── TourData.java
│               └── UserData.java
├── Dockerfile
├── Jenkinsfile
├── README.md
├── docker-compose.yml
├── pom.xml
├── selenium-automation.iml
├── selenium-server-standalone-3.141.59.jar
└── testng.xml
```
