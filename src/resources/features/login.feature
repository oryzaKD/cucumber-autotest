Feature: System kasirAja

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid email and password
    Then I should be redirected to the dashboard page

  Scenario: Create new category
    Given I am on the category page
    When I click the create category button
    And I click button simpan category without fill the form
    And I fill the category form
    Then I should see new category in the category list

  Scenario: Update an existing category
    When I update one of the category value
    Then I should see updated category in the category list

  Scenario: Delete an existing category
    When I delete one of the category

  Scenario: Create new user
    Given I am on the user page
    When I click the create user button
    And I click button simpan user without fill the form
    And I fill the user form
    Then I should see new user in the user list

  Scenario: Update an existing user
    When I update one of the user value
    Then I should see updated user in the user list

  Scenario: Delete an existing user
    When I delete one of the user

  Scenario: Logout from System
    When I click button logout
    Then I should be redirected to the login page