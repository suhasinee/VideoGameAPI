package TestCases;

import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.HashMap;

public class TC_VideoGameAPI 
{
  @Test(priority=1)
	public void test_GetAllVideoGames()
	{
		given()
		.when().get("http://localhost:8080/app/videogames")
		.then().statusCode(200);
	}
    
  @Test(priority=2)
    public void test_PostAddVideoGame()
    {
    /*	HashMap data=new HashMap();
    	data.put("id","10");
    	data.put("name","Suhas");
    	data.put("releaseDate",	"2020-05-27T11:09:52.362Z");
    	data.put("reviewScore","5");
    	data.put("category","Adventure");
    	data.put("rating","Universal");*/
    	
    	Response res=
    	given().log().all().header("Content-Type","application/json").body("{\r\n" + 
    			"  \"id\": 107,\r\n" + 
    			"  \"name\": \"Suhasinee\",\r\n" + 
    			"  \"releaseDate\": \"2020-05-28T03:04:23.885Z\",\r\n" + 
    			"  \"reviewScore\": 10,\r\n" + 
    			"  \"category\": \"Adventure\",\r\n" + 
    			"  \"rating\": \"Universal\"\r\n" + 
    			"}")
    	.when().post("http://localhost:8080/app/videogames")

    	.then().statusCode(200).log().body().extract().response();
    	
    	String jsonString=res.asString();
    	//String res1=EntityUtils.toString();
    	Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
    }
	
   @Test(priority=3)
    public void tc_getVideoGame()
    {
    	 given().log().all()
    	.when().get("http://localhost:8080/app/videogames/100")
    	.then()
    	    .statusCode(200)
    	    .header("content-type", "application/xml");
    	   // .body("id", equals("100"));
    	  //  .body("videoGame.id", 
    //	.body("videoGame.id", equals("100"))
    	//.body("videoGame.name", equalTo("Suhas"));
    	
    
    }
    
    @Test(priority=4)
    public void test_updateVideoGame()
    {
       given().log().all().contentType("application/json").body("{\r\n" + 
       		"  \"id\": 105,\r\n" + 
       		"  \"name\": \"atul\",\r\n" + 
       		"  \"releaseDate\": \"2020-05-28T03:04:23.885Z\",\r\n" + 
       		"  \"reviewScore\": 20,\r\n" + 
       		"  \"category\": \"None\",\r\n" + 
       		"  \"rating\": \"Good\"\r\n" + 
       		"}")
       .when().put("http://localhost:8080/app/videogames/105")
       .then().statusCode(200)
       .log().all();
      // .body(path, responseAwareMatcher);
    }
    
    @Test(priority=5)
    public void test_deleteVideoGame() throws InterruptedException
    {
    Response res=given()
    .when().delete("http://localhost:8080/app/videogames/105")
    .then().statusCode(200)
    .log().all()
    .extract().response();
    
    Thread.sleep(3000);
    String jsonString=res.asString();
    Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
    }
}
