package api.test;

import api.endpoints.UserModEndpoint;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker fakedatagen; // to generate Random Data
    User userpayload; //We have to pass the generated data to the POJO Class

    public Logger logger;

    @BeforeClass
    public void setUpData(){

        fakedatagen = new Faker();
        userpayload = new User();

        userpayload.setId(fakedatagen.idNumber().hashCode());
        userpayload.setUsername(fakedatagen.name().username());
        userpayload.setFirstName(fakedatagen.name().firstName());
        userpayload.setLastName(fakedatagen.name().lastName());
        userpayload.setEmail(fakedatagen.internet().emailAddress());
        userpayload.setPassword(fakedatagen.internet().password());
        userpayload.setPhone(fakedatagen.phoneNumber().cellPhone());
        userpayload.setUserStatus(0);

        //Logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser(){

        logger.info("********************Creating User*************************************");

        Response res =UserModEndpoint.createUser(userpayload); //calling the Usermodendpoint class where the impementation happens
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void getUserByName(){

        logger.info("********************Reading User*************************************");
        Response response = UserModEndpoint.readUser(this.userpayload.getUsername());
        Assert.assertEquals(response.statusCode(),200);
        response.then().log().all();
    }

    @Test(priority = 3)
    public void testUpdateUser(){

        logger.info("********************Updating User*************************************");
        //Datas that have to be modified alone
        userpayload.setEmail(fakedatagen.internet().emailAddress());
        userpayload.setPassword(fakedatagen.internet().password());
        userpayload.setPhone(fakedatagen.phoneNumber().cellPhone());

        Response res =UserModEndpoint.updateUser(this.userpayload.getUsername(),userpayload); //calling the Usermodendpoint class where the impementation happens
        res.then().log().body().statusCode(200);   //Same as assertion statement

        //Assert.assertEquals(res.getStatusCode(),200);

        //checking response after modification
        Response resAfterUpdate = UserModEndpoint.readUser(this.userpayload.getUsername());
        resAfterUpdate.then().log().all();
        Assert.assertEquals(resAfterUpdate.getStatusCode(),200);


    }

    @Test(priority = 4)
    public void testDeleteUser(){

        logger.info("********************Deleting User*************************************");

        Response res= UserModEndpoint.deleteUser(this.userpayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);


    }
}
