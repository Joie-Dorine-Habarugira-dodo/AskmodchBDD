Feature: Place order
  As a customer
  I want to enter my billing details and select a payment method
  So that the system knows where the order is going and who is paying for it
  Background:
    Given I am logged in as a customer
    And I have at least one product in my cart
    And I am on the checkout page

    @positive
  Scenario: Successfully place an order with valid billing details
    When I enter valid billing details:
      | First name | dorine             |
      | Last name  | dorine             |
      | Email      | tester@example.com |
      | Street     | 150 Main Street    |
      | City       | New York           |
      | Zip code   | 00000              |
      | Phone      | 1234567890         |
    And I click on Place order button
    Then the order is placed successfully

    @negative
  Scenario: Fail to place an order with empty required fields
    When I leave required billing fields empty
    And I click on "Place order"
    Then error messages are displayed for each empty required field
    And the order is not placed
