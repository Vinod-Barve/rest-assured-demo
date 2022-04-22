import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredDemo {

    @Test
    public void GetBooksDetails() {
        String baseURI = "https://demoqa.com/BookStore/v1/Books";
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI)
                .then().extract().response();

        System.out.println("***************************** Status Line **************************************");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("******************************* Pretty Print ***********************************");
        System.out.println("Response=>" + response.prettyPrint());
        System.out.println("**************************** Response Body **************************************");
        System.out.println("Response body=>" + response.body().asString());
        System.out.println("**************************** Headers ***************************************");
        System.out.println("Response headers =>" + response.headers());
    }

    @Test
    public void GetBooksDetailsQueryParam() {
        String baseURI = "https://demoqa.com/BookStore/v1/Book";
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParams("ISBN", "9781449325862")
                .when()
                .get(baseURI)
                .then().extract().response();

        System.out.println("**************************** Response **************************************");
        System.out.println("Response body=>" + response.asString());

    }

    @Test
    public void GetPetByIdPathParam() {
        String baseURI = "https://petstore.swagger.io/v2/pet";
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("petId","9222968140497184000")
                .when()
                .get(baseURI+"/{petId}")
                .then().extract().response();

        System.out.println("**************************** Response Body **************************************");
        System.out.println("Response body=>" + response.body().asPrettyString());
        System.out.println("**************************** Response Body **************************************");
        System.out.println("Response body=>" + response.headers());
    }

    @Test
    public void PostUser() {
        String baseURI = "https://demoqa.com/Account/v1/User";

        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", "TQ123bssdl");
        requestParams.put("password", "Abc@1234");


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(requestParams.toJSONString())
                .post(baseURI)
                .then().extract().response();

        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
        System.out.println("Response body=>" + response.body());
        System.out.println("Response headers =>" + response.headers());
    }

    @Test
    public void PostAddBook() {
        String baseURI = "https://demoqa.com/BookStore/v1/Books";
        String userId = "cafb28d0-c159-4152-988e-37ce3afba6c1";
        String bookOne = "9781449325862";

        String body = "{\n" +
                "  \"userId\": \""+userId+"\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \""+bookOne+"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic("abc1234", "Abc@2022")
                .header("Content-Type", "application/json")
                .when()
                .body(body)
                .post(baseURI)
                .then().extract().response();


        //Fetching the response code from the request and validating the same
        System.out.println("**************************** Response Status Code **************************************");
        System.out.println("The response code - " +response.getStatusCode());
        System.out.println("**************************** Response Body **************************************");
        System.out.println("The response code - " +response.body().prettyPrint());

    }

    @Test
    public void PutUpdateBook() {
        String baseURI = "https://demoqa.com/BookStore/v1/Books";
        String userId = "cafb28d0-c159-4152-988e-37ce3afba6c1";
        String bookOne = "9781449325862";
        String bookTwo = "9781449331818";
        String requestURI = baseURI+"/"+bookOne;
        String body = "{\n" +
                "  \"userId\": \""+userId+"\",\n" +
                "  \"isbn\": \""+bookTwo+"\"\n" +
                "}";

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic("abc1234", "Abc@2022")
                .header("Content-Type", "application/json")
                .when()
                .body(body)
                .put(requestURI)
                .then().extract().response();

        System.out.println("**************************** Response Status Line **************************************");
        System.out.println("The response code - " +response.statusLine());
        System.out.println("**************************** Response Body **************************************");
        System.out.println("The response code - " +response.body().prettyPrint());
    }

    @Test
    public void DeleteUsersAllBooks() {
        String baseURI = "https://demoqa.com/BookStore/v1/Books";
        String userId = "cafb28d0-c159-4152-988e-37ce3afba6c1";

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic("abc1234", "Abc@2022")
//                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .queryParams("UserId", userId)
                .when()
                .delete(baseURI )
                .then().extract().response();

        System.out.println("**************************** Response Status Line **************************************");
        System.out.println("The response code - " +response.statusLine());
        System.out.println("**************************** Response Body **************************************");
        System.out.println("The response code - " +response.body().prettyPrint());
        Assert.assertEquals(response.getStatusCode(),"204", "Request wan not successful");


        }

    }