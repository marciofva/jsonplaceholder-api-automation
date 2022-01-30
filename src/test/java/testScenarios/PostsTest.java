package testScenarios;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import helper.Utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;


public class PostsTest extends BaseTest {

    public PostsTest(){
        RestAssured.baseURI = prop.getProperty("base_uri");
    }

    @Test
    public void shouldGetAllPosts() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/posts")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/ListOfPosts.json"))
                .body("userId.size", is(100));
    }

    @Test
    public void shouldGetPostsById() {
        Object id = 7;

        given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
        .when()
                .get("/posts/{id}")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/GetPost.json"))
                .body("id", equalTo(id));
    }

    @Test
    public void shouldNotFoundGetPostsById() {
        Object id = 999;

        given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
        .when()
                .get("/posts/{id}")
        .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }


    @Test
    public void shouldGetPostsByUserId() {
        Object userId = 5;

        given()
                .accept(ContentType.JSON)
                .queryParam("userId", userId)
        .when()
                .get("/posts")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/ListOfPosts.json"))
                .body("userId.size", greaterThan(0));
    }

    @Test
    public void shouldCreatePosts() {
        String json = Utils.convertYamlToJson("src/test/resources/payload/posts.yml");

        given()
                .contentType(ContentType.JSON)
                .body(json)
        .when()
                .post("/posts")
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body(matchesJsonSchemaInClasspath("schemas/GetPost.json"))
                .body("id", is(101));
    }
}
