$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("fist.feature");
formatter.feature({
  "line": 2,
  "name": "First test using Cucumber",
  "description": "",
  "id": "first-test-using-cucumber",
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
  "name": "Login test in mercury tours",
  "description": "",
  "id": "first-test-using-cucumber;login-test-in-mercury-tours",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "User has opened Mercury Tours WebSite",
  "keyword": "Given "
});
formatter.match({
  "location": "TestMe.execute()"
});
formatter.result({
  "duration": 101919978,
  "status": "passed"
});
});