Feature: Updating product in cart
  In order to increase or decrease the product in cart
  As a customer on AskM online shopping website
  I want to update the cart successfully

  Background:
    Given the cart is not empty
    And customer is on the cart page

  Scenario: increase product quantity in cart successfully
    When customer increases the product quantity in cart
    Then the product quantity in the cart should be increased

  Scenario: decrease product quantity in cart successfully
    When customer decreases the product quantity in cart
    Then the product quantity in the cart should be decreased

  Scenario: Remove product successfully
    When customer removes a product from the cart
    Then the product should be removed from the cart
