Feature: Application login
  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User login with "lkshm97" and "password"
    Then Home page is populated
    And Cards are displayed