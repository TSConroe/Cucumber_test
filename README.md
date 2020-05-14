Task:
1. Test #1. Open Google. Search for “automation”. Open the first link
on search results page. Verify that title contains searched word
2. Test #2. Open Google. Search for “automation”. Verify that there is
expected domain (“testautomationday.com”) on search results
pages (page: 1-5).

Requirements
Java JDK 8.

Steps to run:
mvn clean test - run tests
mvn allure:serve -generate allure report, folterto save report: "target/allure-results", it can be changed in allure.properties
mvn allure:report - start web server with results
