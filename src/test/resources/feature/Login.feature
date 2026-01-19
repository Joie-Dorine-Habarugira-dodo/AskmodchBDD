Feature: Login functionality
  As a registered customer,
  I want to log into my account using my email or username and password,
  So that I can access my personalized dashboard and services.

  @positive
  Scenario Outline: Successful login
    Given I am on the login page
    When I enter valid "<username>" and "<password>"
    Then I should be taken to the dashboard
    Examples:
    | username | password|
    | dorine_08| 123     |


    @negative
  Scenario: Failed login with wrong credentials
    Given I am on the login page
    When I enter invalid password
    Then I should see the error message
    And I should see the option Lost your password? option