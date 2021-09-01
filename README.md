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
    HUB_ADDRESS=172.17.0.1:4444
```
### Running Tests Locally with Maven
- Open terminal or cmd
- Go to project root directory command `cd ~/selenium-automation`
- Run tests suite with command `mvn clean test`
##### Example
- Run all tests of a class `mvn clean test -Dtest='LoginTest'`
- Run test method of a class `mvn clean test -Dtest='LoginTest#loginAndLogoutTest'`
- To hide the console warning add `-q` flag `mvn clean test -q`

### Running Tests in Docker Container
- Open docker app
- Open terminal or cmd
- Go to project root directory `cd ~/selenium-automation`
- Run docker-compose file `docker compose up`
- Build docker image `docker build --tag ${TagName} .`
- Run tests in docker container `run -it ${TagName} mvn clean test -Dremote=true -Dbrowser=chrome`
- Use `-Dbrowser=firefox` to run tests in docker with Firefox browser

### Project directory structure
```cmd
.
├── driver
│   ├── chromedriver
│   ├── chromedriver.exe
│   ├── geckodriver
│   └── geckodriver.exe
├── screenshot
│   ├── bookingTicketAsGuestUserTest.png
│   └── loginAndLogoutTest.png
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
├── Jenkins
├── README.md
├── docker-compose.yml
├── pom.xml
├── selenium-automation.iml
└── testng.xml
```
