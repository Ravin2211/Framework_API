package api.test;

import api.endpoints.UserModEndpoint;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker fakedatagen; // to generate Random Data
    User userpayload; //We have to pass the generated data to the POJO Class

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
    }

    @Test(priority = 1)
    public void testPOstUser(){

        Response res =UserModEndpoint.createUser(userpayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);
    }
}
