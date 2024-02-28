Feature: Urban Ladder Testing

  Scenario: Search for Bookshelves
    Given the user is on the shopping website
    When the user Searches for Bookshelves
    And selects bookshelf type and Max price to 15000
    And excludes out of stock and sorts by High to Low
    Then the user should see the top three items with their details

  Scenario: Retrieve Living items under Seating & Chairs sub-menu
    When the user navigates to the Living section and selects Seating & Chairs
    Then the user should see all available items under Seating & Chairs

  Scenario: Customize and Submit a Gift Card
    When the user clicks on GiftCards Section
    When the user chooses Anniversary and Enters Gift Card Amount
    And user fills the form with invalid data
    Then the user captures error message
    When user fills the form with valid data
    Then user validates the entered data
