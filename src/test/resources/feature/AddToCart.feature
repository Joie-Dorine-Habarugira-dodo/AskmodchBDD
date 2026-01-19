Feature: Add product to cart
  As a customer
  I want to add a product to my cart
  So that I can purchase it later

  Scenario: Successfully add a product to the cart
    Given I am on the product listing page
    And I click on product item for opening product detail page
    When I click on the Add to Cart button for a product
    Then a confirmation message is displayed
