package api.test;

import api.endpoints.UserModEndpoint;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
This Class basically gets the data/payload from the DataProviders class and Passes it to the implementation class ie UserModEndpoint   */
public class DataDrivenTest {

    public Logger logger;

    @Test(priority =1,dataProvider ="excelData",dataProviderClass = DataProviders.class)
    public void testPostUserDDP(String userID,String userName,String fname,String lname,String Useremail,String pwd,String Phone){

        logger = LogManager.getLogger(this.getClass());

        User userpayload = new User();

        userpayload.setId((int) Double.parseDouble(userID));
        userpayload.setUsername(userName);
        userpayload.setFirstName(fname);
        userpayload.setLastName(lname);
        userpayload.setEmail(Useremail);
        userpayload.setPassword(pwd);
        userpayload.setPhone(Phone);

        logger.info("************************Adding body from Data Provider*************************8");
        Response res = UserModEndpoint.createUser(userpayload); //calling the Usermodendpoint class where the impementation happens
        Assert.assertEquals(res.getStatusCode(),200);

    }

    @Test(priority = 2,dataProvider = "getUsername",dataProviderClass = DataProviders.class)
    public void testDeleteByUName(String UserName){

        Response response =UserModEndpoint.deleteUser(UserName);
        Assert.assertEquals(response.getStatusCode(),404);
    }
}
