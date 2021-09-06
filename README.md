# selenium-automation
### Project configuration
- Clone project from github repository `git clone https://github.com/faizulcse/selenium-automation.git`
- Go to project root directory `cd ~/selenium-automation`
- Open `config.properties` file from `/src/main/resources`
- Update necessary property values
```properties
   BROWSER=chrome
   HEADLESS=false
   WAIT=0
   FLUENT_WAIT=10
   POLLING_DELAY=100
   SCROLL_DELAY=10
   SCROLL_COUNT=10
   SCROLL_PIXEL=200
   SCREENSHOT=true
   BASE_URL=https://www.phptravels.net
   HUB_ADDRESS=hub:4444
```
### Running Tests locally with selenium standalone server
- Open terminal or cmd
- Go to project root directory `cd ~/selenium-automation`
- Run selenium standalone server `java -jar selenium-server-standalone-*.jar -port 4444`
- On config file `/src/main/resources/config.properties` set `HUB_ADDRESS=localhost:4444`
- Run tests suite with `mvn clean test -Dremote=true`
##### Example
- Run all tests of a class `mvn clean test -Dremote=true -Dtest='LoginTest'`
- Run test method of a class `mvn clean test -Dremote=true -Dtest='LoginTest#loginAndLogoutTest'`
- To hide the console warning add `-q` flag like `mvn clean test -q`

### Running Tests in Docker Container
- Open docker app
- Open terminal or cmd
- Go to project root directory `cd ~/selenium-automation`
- On config file`/src/main/resources/config.properties` set `HUB_ADDRESS=hub:4444`
- Set `${TagName} = "phptravels"`
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
```commandline
.
├── driver
│   ├── chromedriver
│   ├── chromedriver.exe
│   ├── geckodriver
│   └── geckodriver.exe
├── screenshots
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── phptravels
│   │   │   │       ├── BasePage.java
│   │   │   │       ├── BookingPage.java
│   │   │   │       ├── DashboardPage.java
│   │   │   │       ├── HomePage.java
│   │   │   │       ├── LoginPage.java
│   │   │   │       ├── Page.java
│   │   │   │       └── SignUpPage.java
│   │   │   └── utils
│   │   │       ├── BundleUtils.java
│   │   │       ├── DriverUtils.java
│   │   │       ├── EventListener.java
│   │   │       ├── LocatorUtils.java
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
│               ├── Customer.java
│               ├── Site.java
│               ├── Tour.java
│               └── User.java
├── Dockerfile
├── Jenkinsfile
├── README.md
├── docker-compose.yml
├── pom.xml
├── selenium-automation.iml
├── selenium-server-standalone-3.141.59.jar
├── testng.xml
└── wip.xml
```