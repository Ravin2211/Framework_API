package api.endpoints;


import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//To implement methods that perform CRUD operations (Create Read Update Delete)
public class UserModEndpoint {

    //should have a body which comes from payload
    public static Response createUser(User payload){

        Response res = given()
                            .contentType(ContentType.JSON)
                            .body(payload)

                         .when()
                            .post(Routes.post_url);    //getting the post url from Routes class

        return res;
    }

    //TO read the created user - will get the username from the data given in ()
    public static Response readUser(String username){

       Response res= given()
                        .pathParam("username",username)
                    .when()
                        .get(Routes.get_url);

       return res;
    }

    //will update the data after getting user info from pojo class and the modification data from ()
    public static Response updateUser(String username,User payload){
        //User payload pojo is used to get the body and String username parameter is to modify the data which was given already it can be any data
        Response res = given()
                .contentType(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)

                .when()
                .put(Routes.update_url);    //getting the post url from Routes class

        return res;
    }

    //TO read the created user - will get the username from the data given in ()
    public static Response deleteUser(String username){

        Response res= given()
                .pathParam("username",username)
                .when()
                .delete(Routes.delete_url);

        return res;
    }

}
