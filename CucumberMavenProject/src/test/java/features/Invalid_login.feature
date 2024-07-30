Feature: Trying to login into the webpage using invalid data

  Scenario: Sign in with invalid credential details
    Given I am on the sign-in page
    When I enter invalid credentials
    And I try to sign in into the account
    Then I should get a error message
       
  Scenario: Try to Sign up with an existing email
    Given I am on the sign-up page
    When I enter details with an existing email
    When I try to submit the form
    Then I should get a error as account is already present