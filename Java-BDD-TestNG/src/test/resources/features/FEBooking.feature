@FrontEnd
Feature: FrontEnd - Login

  As a user, I want to login successfully on FrontEnd site

  Background:
    Given User on the Front-End Login Page
    When  User log in
      |user@phptravels.com|demouser|


#-------------------------------------------------------------------------------
  @FEBooking
  Scenario: FE010-Booking - Verify Invoice Booking details
    And Random choose Booking information
    When  Click Invoice
    Then  Verify Invoice Information
