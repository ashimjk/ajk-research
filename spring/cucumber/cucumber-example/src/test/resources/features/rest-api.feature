Feature: Testing a REST API
  Users should be able to submit GET and POST requests to a web service,
  represented by WireMock

  Scenario: Data Upload to a web service
    When users upload data on a project
    """
    {
      "testing-framework": "cucumber",
      "supported-language":
      [
        "Ruby",
        "Java",
        "Javascript",
        "PHP",
        "Python",
        "C++"
      ],
      "website": "cucumber.io"
    }
    """
    Then the server should handle it and return a success status

  Scenario: Data retrieval from a web service
    When users want to get information on the Cucumber project
    Then the requested data is returned