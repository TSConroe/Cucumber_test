Feature: Is first find page contain key world in title  Search for keyword on google, open the first link on search results page and verify that title contains searched word

  Scenario: Verify that there is expected domain on search results

    Given Open "google.com"
    Then Input text "automation"
    Then Verify that there is "automation.com" domain is present on sears results pages, page 1 - 5



