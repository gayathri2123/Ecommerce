Feature: error validation for login
  @errorValidation
  Scenario Outline: validating login page
    Given user is on ecommerce page
    When user enter username <username> and password <password>
    Then verify "Incorrect email or password." is displayed

    Examples:
      | username                 | password    |
      | gayathricseeng@gmail.com | Gayathri@62 |