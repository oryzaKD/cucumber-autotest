Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid email and password
    Then I should be redirected to the homepage