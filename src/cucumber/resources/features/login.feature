Feature: Login
  login.

  Background:
    Given There are 3 courses
    And "mary@example.com" which in "China" and "Chengdu" is a contact already
    And "JohnSmith@mail.com" which in "China" and "Chengdu" is a contact already
    And "JaneDoe@mail.com" which in "China" and "Chengdu" is a contact already
    And "john@example.com" which in "China" and "Chengdu" is a contact already
    And "Bobb@example.com" which in "China" and "Chengdu" is a contact already
    When I visit "CSD-1" detail page from course list page
    When I enroll participants to "CSD-1" from course detail page
      | JohnSmith@mail.com	Tom	Smith	CS	Singapore    |
      | JaneDoe@mail.com	John	Fisher	CS	Singapore |

  Scenario: Login success
    Given Visit Login Page
    Given There is a user with "mary@example.com" and "abcd1234"
    Given Fill form with "mary@example.com" and "abcd1234"
    When I click login button
    Then Show course list of current user

  Scenario Outline: Login fail
    Given Visit Login Page
    Given Fill form with "<email>" and "<password>"
    When I click login button
    Then I should move to page with url "<url>"
    And Login failed message is shown

    Examples:
      | email               | password       | url                   |
      | mary@example.com    | hogehoge       | login.jsp?status=fail |
      | unknown@example.com | hogehoge       | login.jsp?status=fail |
      | uninit@example.com  | uninitpassword | login.jsp?status=fail |

  @now
  Scenario Outline: Courses List after Login
    Given Visit Login Page
    Given There is a user with "<email>" and "<password>"
    Given Fill form with "<email>" and "<password>"
    When I click login button
    Then Show courses list "<courses>"

    Examples:
      | email              | password | courses |
      | JohnSmith@mail.com | abcd1234 | CSD-1   |
#      | JaneDoe@mail.com   | abcd1001 | CSD-1   |
#      | john@example.com   | abcd1002 | CSD-2               |
#      | Bobb@example.com   | abcd1003 |                             |

  @developing
  Scenario: Show Cources List Test
    Given I am on create new course page
    When Add a course with below details
      | coursename    | CSD-1        |
      | duration      | 30           |
      | country       | China        |
      | city          | Chengdu      |
      | startdate     | 2017-10-23   |
      | address       | odd-e        |
      | coursedetails | CSD training |
      | instructor    | Terry        |
    And I click the Create button
    Then Matsuo Show cources list test "1 - Tokyo CSD,2 - CSD cource,3 - Osaka CSD"
