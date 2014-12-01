
import org.junit.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestService {
	@Test
	public void testJsonResult() {
		// expect().statusCode(200).when().get("rubyweb");
		// expect().statusCode(200).when().get("http://ruby.fti.pagekite.me/rubyweb");
		int  numberOfFile = 2;
		int  startFile = 2;
		for (int i = 2;i <= numberOfFile;i++ ) {
			List<DataTest> datatests = Helper.readXmlFile("data_test_api_" + i + ".xml");
			for (DataTest dataTest : datatests) {
				System.out.println("Test data " + dataTest.getId() + ": " + dataTest.getQuestion());
				System.out.println("	Domain: " + dataTest.getDomain() + " | Intent: " + dataTest.getIntent());
				if (!dataTest.getNameMapper().getMv_cinName().isEmpty())
					System.out.println("	Movie_CinName: " + dataTest.getNameMapper().getMv_cinName());
				if (!dataTest.getNameMapper().getMv_movieTitle().isEmpty())
					System.out.println("	Movie_MovieTitle: " + dataTest.getNameMapper().getMv_movieTitle());
				if (!dataTest.getNameMapper().getTv_channel().isEmpty())
					System.out.println("	TV_Channel: " + dataTest.getNameMapper().getTv_channel());
				if (!dataTest.getNameMapper().getTv_program().isEmpty())
					System.out.println("	TV_Program: " + dataTest.getNameMapper().getTv_program());
				given().contentType("application/x-www-form-urlencoded;charset=UTF-8").
						param("question", dataTest.getQuestion()).
						when().
						post("http://118.69.135.27/rubyweb/getAnswer").
						then().
						assertThat().body("domain", equalTo(dataTest.getDomain())).
						assertThat().body("intent", containsString(dataTest.getIntent())).
						assertThat().body("queryParamater.cinName", anyOf(is(nullValue()), equalToIgnoringCase(dataTest.getNameMapper().getMv_cinName()))).
						assertThat().body("queryParamater.tvChannel", anyOf(is(nullValue()), equalToIgnoringCase(dataTest.getNameMapper().getTv_channel()))).
						assertThat().body("queryParamater.tvProTitle", anyOf(is(nullValue()), equalToIgnoringCase(dataTest.getNameMapper().getTv_program()))).
						assertThat().body("queryParamater.movieTitle", anyOf(is(nullValue()),containsString(dataTest.getNameMapper().getMv_movieTitle())));
			}
		}
	}

	
	
}