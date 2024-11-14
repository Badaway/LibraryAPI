import com.example.LibraryAPI.repository.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryApiApplicationTests {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() throws Exception {
		RestAssured.port = port;

	}

	@Test
	void contextLoads() {
	}

}
