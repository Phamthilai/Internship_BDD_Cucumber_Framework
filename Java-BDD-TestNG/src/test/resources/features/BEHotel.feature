@BackEnd
Feature: BackEnd - Login

  Background: Navigate and login as Admin to page
    Given Login BackEnd Url
    And   Provide "admin@phptravels.com" and "demoadmin"
    And   Click BackEnd Login button

#-------------------------------------------------------------------------------
  @BE003
  Scenario: BE003-Hotels-Navigate to sub item of Hotels
    And Click Hotel on left menu bar
    Then Verify navigation page successfully when clicking per each sub menu
      |Submenu 1|Submenu 2|Submenu 3|Submenu 4|Submenu 5|
      |Hotels   |Rooms    |Extras   |Reviews  |Hotels Settings|
    Then Verify Total SubMenu

  @BE004
  Scenario: BE004-Hotels-Create a new hotel successful
    And Click Hotel on left menu bar
    Then Choose Hotel submenu
    And Add new hotel
      | Status |Hotel Name      |Hotel Description                         | Stars|Hotel Type| Featured|Start Date|End Date  |HintLocation|Location             | Deposit  |Deposit Amount| Vat Tax   | Vat Tax Amount| Related Hotel            | Address       | Latitude |Longitude  |
      |Disabled|Ualifornia Hotel|I am testing the create new hotel function| 5    |  Villa   | No      |14/05/2019|30/06/2019|Tha         | Udon Thani, Thailand|Percentage| 30           | Percentage| 10            |Swissotel Le Plaza Basel  |123 East Street|40.7143528|-74.0059731|
    Then Verify a new hotel can be added successfully

  @BE005
  Scenario: BE005-Hotels-Modify Hotel Information
    And Click Hotel on left menu bar
    Then Choose Hotel submenu
    And Add new hotel
      | Status |Hotel Name      |Hotel Description| Stars|Hotel Type| Featured|Start Date|End Date  |HintLocation|Location             | Deposit  |Deposit Amount| Vat Tax   | Vat Tax Amount| Related Hotel            | Address       | Latitude |Longitude  |
      |Disabled|Ualifornia Hotel|I am testing     | 5    |  Villa   | No      |14/05/2019|30/06/2019|Tha         | Udon Thani, Thailand|Percentage| 30           | Percentage| 10            |Swissotel Le Plaza Basel  |123 East Street|40.7143528|-74.0059731|
    Then Modify hotel information
      | Status |Hotel Name      |Hotel Description  | Stars|Hotel Type| Featured|Start Date|End Date  |HintLocation|Location             | Deposit  |Deposit Amount| Vat Tax   | Vat Tax Amount| Related Hotel            | Address       | Latitude |Longitude  |
      |Enabled |Updated Hotel    |Updating hotel info| 4    |  Villa   | No      |15/05/2019|30/06/2019|Can         | Cananea, Mexico|Percentage| 30           | Percentage| 10            |Swissotel Le Plaza Basel  |123 East Street|40.7143528|-74.0059731|
    Then Verify hotel information edited successfully

  @BE006
  Scenario: BE006-Hotels-Upload gallery
    And Click Hotel on left menu bar
    Then Choose Hotel submenu
    And Add new hotel
      | Status |Hotel Name            |Hotel Description   | Stars|Hotel Type| Featured|Start Date|End Date  |HintLocation|Location             | Deposit  |Deposit Amount| Vat Tax   | Vat Tax Amount| Related Hotel            | Address       | Latitude |Longitude  |
      |Disabled|Upload Gallery testing|This is a test hotel | 5    |  Villa   | No      |14/05/2019|30/06/2019| Tha	      | Udon Thani, Thailand|Percentage| 30           | Percentage| 10            |Swissotel Le Plaza Basel  |123 East Street|40.7143528|-74.0059731|
    Then Upload one image
    Then Verify photo can be uploaded successfully

  @BE007
  Scenario: BE007-Hotels-Delete Hotels by icon
    And Click Hotel on left menu bar
    Then Choose Hotel submenu
    And Add new hotel
      | Status |Hotel Name          |Hotel Description   | Stars|Hotel Type| Featured|Start Date|End Date  |HintLocation|Location             | Deposit  |Deposit Amount| Vat Tax   | Vat Tax Amount| Related Hotel            | Address       | Latitude |Longitude  |
      |Disabled|Delete hotel by icon|This is a test hotel | 5    |  Villa   | No      |14/05/2019|30/06/2019| Tha	      | Udon Thani, Thailand|Percentage| 30           | Percentage| 10            |Swissotel Le Plaza Basel  |123 East Street|40.7143528|-74.0059731|
    Then Delete hotel via delete icon
    Then Verify hotel can be deleted successfully via delete icon

  @BE009
  Scenario: BE009-Hotels-Search hotel by Name
    And Click Hotel on left menu bar
    Then Choose Hotel submenu
    And Search hotel by name and verify search result
      | Type|HotelName|
      | valid|hotel|
      | invalid|@123hotel  |

  @BE010
  Scenario Outline: BE010-Hotels-Sort hotel
    And Click Hotel on left menu bar
    Then Choose Hotel submenu
    Then Verify sort by hotel "<columnName>" works correctly

    Examples:
      |columnName|
      |Name      |
      |Location  |
