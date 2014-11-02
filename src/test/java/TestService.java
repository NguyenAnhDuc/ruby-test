
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;

public class TestService {
	@Test
	public void testJsonResult() {
		// expect().statusCode(200).when().get("rubyweb");
		// expect().statusCode(200).when().get("http://ruby.fti.pagekite.me/rubyweb");
		List<DataTest> datatests = Helper.readXmlFile("data_test_api.xml");
		for (DataTest dataTest : datatests){
			System.out.println("Test data " + dataTest.getId() + ": " + dataTest.getQuestion());
			given().contentType("application/x-www-form-urlencoded;charset=UTF-8").
			param("question", dataTest.getQuestion()).
		when().
			post("http://ruby.fti.pagekite.me/rubyweb/getAnswer").
		then().
			assertThat().body("domain", equalTo(dataTest.getDomain())).
			assertThat().body("intent", equalTo(dataTest.getIntent())).
			assertThat().body("queryParamater.cinName", anyOf( is(nullValue()) , equalTo(dataTest.getNameMapper().getMv_cinName()))).
			assertThat().body("queryParamater.tvChannel", anyOf( is(nullValue()) , equalTo(dataTest.getNameMapper().getTv_channel()))).
			assertThat().body("queryParamater.tvProTitle", anyOf( is(nullValue()) , equalTo(dataTest.getNameMapper().getTv_program()))).
			assertThat().body("queryParamater.movieTitle", anyOf( is(nullValue()) , equalTo(dataTest.getNameMapper().getMv_movieTitle())));
	
		}
		
		
		
	}

	
	
}