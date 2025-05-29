package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Pojo.AddPlace;
import Pojo.Location;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	

	@Given("AddPlace payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException
	{
		request=given().spec(requestSpecification()).body(data.add_place_payload(name, language, address));
	}
	
	@When("user calls {string} with {string} http method")
	public void user_calls_with_http_method(String resource, String method)
	{
		APIResources resourceAPI= APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResources());
		
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		
		if(method.equalsIgnoreCase("POST"))
		response = request.when().post(resourceAPI.getResources());
		else if(method.equalsIgnoreCase("GET"))
			response = request.when().get(resourceAPI.getResources());
	}
	
	@Then("the API call got success with status code 200")
	public void the_API_call_got_success_with_status_code()
	{
		assertEquals(response.getStatusCode(), 200);
	}
	
	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue)
	{
		assertEquals(getJsonPath(response, keyvalue), expectedvalue);
	}
	

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		place_id= getJsonPath(response, "place_id");
		request=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_method(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		
	   
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException 
	{
		request=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}
	}






	
	
	
	
	


