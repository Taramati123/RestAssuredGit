package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException
	{
	
		StepDefinition m = new StepDefinition();
		if(StepDefinition.place_id==null)
		{
		m.add_place_payload_with("Maruti", "Hindi", "US");
		m.user_calls_with_http_method("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Maruti", "getPlaceAPI");
		}
	}

}
