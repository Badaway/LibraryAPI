package common;

import com.example.LibraryAPI.controller.AuthenticationController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@Component
public class Common {

	public static final String baseUri="/api";

	public static final String defaultPassword="123";
	public static final String defaultEmail="@gts.com";
	public static final UUID defaultAuthorId= UUID.fromString("48257d4d-a5a9-4d1e-9a39-32ba6bfa0b2f");

	public static final String userEmail ="admin@gts.co";
	public static final String userPassword ="123";

	public static String getToken(String email, String password) throws Exception {



		String requestBody= "{\"email\":\""+email+"\",\"password\":\""+password+"\"}";


		var token = RestAssured.given().contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post(baseUri+ AuthenticationController.baseControllerUri+AuthenticationController.loginUri)
				.then()
				.contentType(ContentType.JSON)
				.extract().
				path("token");


		return token.toString();

	}

	public static String getRandomString(){
		return RandomStringUtils.randomAlphanumeric(10);
	}
	public static Long getRandomNumber(){
		return Long.parseLong(RandomStringUtils.randomNumeric(10));
	}


}
