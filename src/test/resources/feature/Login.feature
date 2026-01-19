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
  Scenario Outline: Failed login with invalid credentials
    Given I am on the login page
    When I enter invalid "<username>" and "<password>"
    Then I should see the "<error>" message
    And I should see the option Lost your password? option
    Examples:
        | username  | password | error |
        | dorine_10 | 123      | The username dorine_10 is not registered on this site. If you are unsure of your username, try your email address instead. |
        | dorine_08 | 12345    | The password you entered for the username dorine_08 is incorrect.                                                          |
        |           | 123      | Username is required.                                                                                                      |
        | dorine_08 |          | The password field is empty.                                                                                               |