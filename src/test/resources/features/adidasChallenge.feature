@pet
Feature: pet feature

  @TEST_001
  Scenario: get available pets
    Given the user asks for available pets
    Then the user gets 200 status code
    Then the user has received available pets

  @TEST_002
  Scenario: create a new pet, sell it and delete it
    Given the user creates a new pet
    Then the user gets 200 status code
    And a pet is created
    When the user changes the pet status to sold
    Then the user gets 200 status code
    And pet status is sold
    When the user deletes the pet
    Then the user gets 200 status code
    Then pet is deleted