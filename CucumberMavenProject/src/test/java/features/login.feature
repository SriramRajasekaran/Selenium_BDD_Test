Feature: Successfully creating account and login in using the credential 

  Scenario: Create a new account in sign up page
    Given I am on the sign-up page
    When I enter valid details
    When I try to submit the form
    Then my account should be created successfully
    
	 Scenario: Try to Sign in with the created account
    Given I am trying to sign in into page
    When I enter valid credentials
    And I try to sign in into the account using valid data
    Then I should be signed in successfully    