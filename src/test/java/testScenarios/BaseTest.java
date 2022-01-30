package testScenarios;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import helper.Utils;
import org.junit.BeforeClass;
import java.util.Properties;


public class BaseTest {

    public static Properties prop;

    @BeforeClass
    public static void setUp() {
        prop = Utils.loadPropertiesFile("config.properties");
        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
