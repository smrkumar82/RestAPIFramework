Feature: Validating place APIs
  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<address>" "<language>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with the status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      |name           |address                         |language       |
      |Frontline house|   29, side layout, cohen 09    |  French-IN    |
      |Sample test    |    USA, Exton                  | English -US   |

  @DeletePlace @Regression
  Scenario: Verify if delete place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call got success with the status code 200
    And "status" in response body is "OK"



