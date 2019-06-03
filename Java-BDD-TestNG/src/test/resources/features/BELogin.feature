@BackEnd
Feature: BackEnd - Login

-------------------------------------------------------------------------------
  @ValidBELogin
  Scenario: BE001-Login-Login to page successful
    Given Login BackEnd Url
    And   Provide "admin@phptravels.com" and "demoadmin"
    And   Click BackEnd Login button
    Then  BackEnd Login successfully

  @InValidBELogin
  Scenario Outline: BE002-Login-Login to page unsuccessful
    Given Login BackEnd Url
    And   Provide "<UserName>" and "<PassWord>"
    And   Click BackEnd Login button
    Then  Error message displayed "<ErrorMessage>"


    Examples:
      |UserName            |PassWord|ErrorMessage                                       |
      |abc                 |email   |The Email field must contain a valid email address.|
      |admin@phptravels.com|pas     |Invalid Login Credentials                          |



