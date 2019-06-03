@FrontEnd
Feature: FrontEnd - Search

  As a user, I want to search to find out the trip that I expected
  User can search by Tour type, hotel, cars or flight

  Background:
    Given User is on homepage

    @FESearchTour
    Scenario: FE_Tour002 - Verify Search Tour successfully
      Then Go to  tours page
      And  Provide tours searching Information
        |Star Grade|Min Price|Max Price|Tour Type|
        |3         |0        |160      |Holidays |
      When Click Search button
      Then Verify Tours Information


    @FECar
    Scenario: FE003-Cars - Verify Search Car successfully
      Then Go to car page
        |Pick Up Location|
        |Malaysia        |
      And Provide cars searching Information
        |Star Grade|Car Type|Min Price|Max Price|AirPort Pick Up|
        |4         |Standard  |60      |160      |yes            |

      And  Click Search button
      Then Verify Cars Information


    @FEFlight
    Scenario: FE004-Flights - Verify Search Flights Car successfully
      Then Go to flight page
      And  Provide flight searching information
        |stop|airline|
        |no  |EVA Air|

      Then Verify flights information

    @FEHotel
    Scenario: FE005-Hotels - Verify Search Hotel successfully
      Then Go to hotel listing page
      And  Provide hotel searching information
        |star|minPrice|maxPrice|propertyTypes|amenities|
        |4   |20      |40      |Hotel & Villa  |Night Club & Restaurant|

      And  Click Search button
      Then Verify hotels information

