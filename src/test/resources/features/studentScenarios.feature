Feature: Testing different requests on Student application

  @SMOKE
  Scenario: Verify status code 200 for get all students request
    Given User sends a GET request to the list endpoint, they must get a status code 200

 @SMOKE
  Scenario Outline: Create a new student & verify if the student is added
    When I create a new student with the info firstName <firstName> lastName <lastName> email<email> programme<programme> and courses<courses>
    Then I verify that the student with <email>is created

   Examples:
    |firstName|lastName|email|programme|courses|
    |Jordanna |Pearton |jpearton0@rakuten.co.jp|Computer Science|Java|
    |Joey     |Greenrde|jgreenrde1@senate.gov  |Information Science|C++|