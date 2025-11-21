package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpointsProp {

    //method to get urls from property file(addtional Method)
    public static ResourceBundle getURL(){

        ResourceBundle routes = ResourceBundle.getBundle("routes"); /*will load the properties file__
        "routes" is Property file name*/
        return routes;
    }

    public static Response createUser(User payload){

        String p_url = getURL().getString("post_url");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(payload)

                .when()
                .post(p_url);    //getting the post url from Routes class

        return res;
    }

    //TO read the created user - will get the username from the data given in ()
    public static Response readUser(String username){

        String g_url = getURL().getString("get_url");

        Response res= given()
                .pathParam("username",username)
                .when()
                .get(g_url);

        return res;
    }

    //will update the data after getting user info from pojo class and the modification data from ()
    public static Response updateUser(String username,User payload){

        String up_url = getURL().getString("update_url");
        //User payload pojo is used to get the body and String username parameter is to modify the data which was given already it can be any data
        Response res = given()
                .contentType(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)

                .when()
                .put(up_url);    //getting the post url from Routes class

        return res;
    }

    //TO read the created user - will get the username from the data given in ()
    public static Response deleteUser(String username){

        String d_url = getURL().getString("delete_url");

        Response res= given()
                .pathParam("username",username)
                .when()
                .delete(d_url);

        return res;
    }

}
