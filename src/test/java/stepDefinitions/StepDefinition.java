package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild testData=new TestDataBuild();
    static  String place_id;


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String address, String lang) throws IOException{
        // Write code here that turns the phrase above into concrete actions

        res = given().spec(requestSpec()).body(testData.addPlacePayload(name,lang,address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource,String methodName) {
        // Write code here that turns the phrase above into concrete actions

        APIResources resourcePath=APIResources.valueOf(resource);

        resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

        if (methodName.equalsIgnoreCase("POST"))
        {
        response=res.when().post(resourcePath.getResource())
                .then().spec(resSpec).log().all().extract().response();
        }

        else if (methodName.equalsIgnoreCase("GET"))
        {
            response=res.when().get(resourcePath.getResource())
                    .then().spec(resSpec).log().all().extract().response();
        }

    }
    @Then("the API call got success with the status code {int}")
    public void the_api_call_got_success_with_the_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
       assertEquals(response.getStatusCode(),200);


    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(getJsonPath(response,keyValue),expectedValue);

    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) throws IOException {
        //
        place_id = getJsonPath(response,"place_id");
        res = given().spec(requestSpec()).queryParam("place_id",place_id);
        user_calls_with_post_http_request(resourceName,"GET");

        String actualName = getJsonPath(response,"name");
        assertEquals(actualName,expectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpec()).body(testData.deletePlacePayload(place_id));
    }
}
