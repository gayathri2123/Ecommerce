Feature: place an order

  Background:
    Given user is on ecommerce page
  @Regression
  Scenario Outline: user place an order
    Given user enter username <username> and password <password>
    When user get the product list
    And add product <productName> to cart
    And move to cart page
    Then verify cart product <productName>
    And click on checkout button
    And type <country> name and select desired country
    And click on placeorder button
    Then print order id

    Examples:
      | username                 | password   | productName | country |
      | gayathricseeng@gmail.com | Gayathri@6 | ZARA COAT 3 | India   |