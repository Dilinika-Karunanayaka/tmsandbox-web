# TradeMe Sandbox Web Test Automation

## Test Framework

### Tools and technologies
> - **Selenium WebDriver** - Web browser client
> - **Cucumber** - BDD test layer
> - Java - 11
> - Maven - Build and dependency management
> - JUnit - Assertions
> - Lombok - Helps to reduce boilerplate code
> - Log4j - Logging framework
> - Masterthought - Custom test report

### Capabilities
> - Parallel test execution (dynamic or fixed)
>- Run tests against multiple browsers based on selected browser profiles
> - Run tests against multiple environments based on selected environment profiles
> - Multiple test reports - default and third party

## Test Cases - Functional
> - Search with valid input
> - Search with invalid input
> - Verify search bar placeholder text
> - Search suggestions relevance
> - Search with suggested item
> - Search categories and check headers

## Test Cases - Performance
> - Measure search results performance
> - Measure search suggestion dropdown list's performance

## Test Execution
> -  mvn clean test
> -  mvn clean test -Penv.trademe,browser.firefox
>  - **Note** - Maven Profile 'env.tmsandbox' & 'browser.chrome' is enabled by default
>  - https://youtu.be/Iu77TKlkoGI

## Test Reports
> - Default Cucumber report - target/cucumber-reports/Cucumber.html
> - Cucumber timeline report - target/cucumber-reports/CucumberTimeline/index.html
> - Masterthought report - target/cucumber-html-reports/overview-features.html