Feature: Tests for generating an ABD document
  Scenario: Generate the ABD pdf
    When I make a request to the generate pdf api with "test_1_ABD.json"
    And The pdf is syntactically correct
