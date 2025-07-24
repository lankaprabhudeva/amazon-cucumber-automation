Feature: Verify the Signup feature in Amazon Prime

  Scenario Outline: Signup attempt with different mobile and password combinations
    Given the user hovers over the "Your Account" icon
    When the user hovers over "Sign In" and clicks it
    Then the user enters a mobile number in the "<mobileField>" field
    And clicks the "Continue" button
    Then the user is redirected to the password entry page
    And enters the password in the "<passwordField>" field
    And clicks the "Sign In" button
    Then the user should see "<expectedResult>"

  Examples:
    | mobileField                      | passwordField  | expectedResult                    |
    | 9346250755                     | nagamani484@L#   | Welcome to Prime Video              |  # ✅ Positive case
    | 123                             | ValidPass123   | Invalid mobile number error shown |
    | 9876543210                       | wrongpass      | Incorrect password error shown    |
    |                                 | ValidPass123   | Mobile number required error      |
    | 9876543210                       |                | Password required error           |
