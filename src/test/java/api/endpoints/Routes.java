package api.endpoints;


/*
Swagger URL : https://petstore.swagger.io/

Create User(POST) : https://petstore.swagger.io/v2/user
Get User(GET)     : https://petstore.swagger.io/v2/user/{username}
Update User(PUT)  : https://petstore.swagger.io/v2/user/{username}
DeleteUser(DELETE): https://petstore.swagger.io/v2/user/{username}
 */

//To maintain URLs ONLY
public class Routes {

public static String baseurl = "https://petstore.swagger.io/v2";


//User Module
    public static String post_url = baseurl +"/user";
    public static String get_url = baseurl +"/user/{username}";
    public static String update_url = baseurl +"/user/{username}";
    public static String delete_url = baseurl +"/user/{username}";

//Store Module
    //create a url for store module present in the website
//Pet Module
    //create a url for pet module present in the website


}
