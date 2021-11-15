package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        StepDefinition sd = new StepDefinition();
        if (StepDefinition.place_id==null) {
            sd.add_place_payload_with("SMRK", "Nellai", "English");
            sd.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            sd.verify_place_id_created_maps_to_using("SMRK", "getPlaceAPI");
        }

    }
}
