Feature: Registration functionality
  As a new customer,
  I want to create an account by registering with my personal details,
  So that I can login in the website.

@positive
  Scenario: Successful registration

    Given I am on the registration page
    When I register with valid details:

      | userName | dorine10          |
      | email    | tester@tester.com |
      | password | 12345             |
    Then I should be redirected to the dashboard and see welcome message

@negative
  Scenario: Registration with existing email
    Given I am on the registration page
    When I register with email that already exists:
      | userName | dorine10      |
      | email    | tester@example.com |
      | password | 12345   |
    Then I should see the error message Email already exists