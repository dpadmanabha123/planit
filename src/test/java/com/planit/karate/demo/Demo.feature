
@tag
Feature: Creating a demo test for the planit training

  @tag1
  Scenario: Would trigger a get call here
    Given url 'https://jsonplaceholder.typicode.com/todos/1'
    When method GET
    And status 200
    And match response.title == 'delectus aut autem'
    
  @tag2
  Scenario: Would trigger a post call here
    Given url 'https://jsonplaceholder.typicode.com/posts'
    And request {"title": "Hello World", "body" : "someData","userId": 1}
    When method POST
    Then status 201
    And match response.title == 'Hello World'
    
  @tag3
  Scenario: Would trigger a put call here
    Given url 'https://jsonplaceholder.typicode.com/posts/1'
    And request {"title": "Hello World 1", "body" : "someData","userId": 1}
    When method PUT
    Then status 200
    And match response.title == 'Hello World 1'
    
  @tag4
  Scenario: Would trigger a delete call here
    Given url 'https://jsonplaceholder.typicode.com/posts/1'
    When method DELETE
    And status 200
    
   
