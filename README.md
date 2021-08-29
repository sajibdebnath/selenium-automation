# selenium-automation

### Project configuration
- Clone project from github repository `git clone https://github.com/faizulcse/selenium-automation.git`
- Go to project root directory
- Open `config.properties` file under the directory `/src/main/resources`
```properties
BROWSER=chrome
HEADLESS=false
EVENT_LOG=false
IMPLICIT_WAIT=0
EXPLICIT_WAIT=15
SCROLL_BY_PIXEL=300
SCROLL_COUNT_MAX=10
SCROLL_INTERVAL_DELAY=10
SCREENSHOT_ON_FAILURE=true
BASE_URL=https://www.phptravels.net
```
- Change the property value as you required

### Run project using Maven
- Open terminal or cmd
- Go to project root directory command `cd ~/selenium-automation`
- Run all tests from suite use command `mvn clean test -q` (-q flag for hiding the console warning log)
- Run all tests in a class use command `mvn clean test -Dtest='LoginTest' -q`
- Run test method in a class use command `mvn clean test -Dtest='LoginTest#loginAndLogoutTest' -q`
