Feature: Is first find page contain key world in title
  Search for keyword on google, open the first link on search results page and verify that title contains searched word

  Scenario: Check first result page title
        Then Input text "automation"
        Then Open link by index "1"
        Then Check that page title contain text "automation"

