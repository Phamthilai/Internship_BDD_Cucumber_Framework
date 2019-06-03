@FrontEnd
Feature: FrontEnd - Blog

  Background:
    Given User is on homepage
#-------------------------------------------------------------------------------
  @Blog
  Scenario: FE006-Blog - Verify Categories and Posts
    And Choose Blog menu
    Then Choose categories and verify the number of post
      |Travel and Foods|Adventure|Shopping and Fashion|Things to do|

  @Offer
  Scenario: FE007-Offers - Verify maximize image
    And Choose Offer menu
    When Random click on 'Read more' button
    When Mouse over on image
    Then Verify Fullscreen icon is  displayed on Image
    When Clicking Fullscreen icon
    Then Verify Image displays as full screen
    When Mouse over on image and clicking on normal icon
    Then Image displays as normal size