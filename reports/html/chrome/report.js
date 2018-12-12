$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("cucumber.feature");
formatter.feature({
  "line": 2,
  "name": "First test of Axon",
  "description": "",
  "id": "first-test-of-axon",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@first"
    }
  ]
});
formatter.scenario({
  "line": 4,
  "name": "First scenario to execute",
  "description": "",
  "id": "first-test-of-axon;first-scenario-to-execute",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "User has lauched Application",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "User login to Axon",
  "keyword": "When "
});
formatter.match({
  "location": "LoginTest.lauchApplication()"
});
formatter.result({
  "duration": 4654537945,
  "status": "passed"
});
formatter.match({
  "location": "LoginTest.loginToAxon()"
});
formatter.result({
  "duration": 5377944128,
  "status": "passed"
});
});