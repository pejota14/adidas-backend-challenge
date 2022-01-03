package org.adidas.backend.stepDefinitions;

import io.cucumber.java.en.Then;
import org.adidas.backend.supportFunctions.CommonsModule;
import org.adidas.backend.supportFunctions.RequestModule;
import org.junit.Assert;

public class CommonSteps {
    RequestModule requestModule = new RequestModule();

    @Then("^the user gets (.*) status code$")
    public void verifyResponseStatus(String status) {
        Assert.assertEquals("The status code doesn't match.", Integer.parseInt(status), requestModule.getResponseStatus(CommonsModule.getSessionVariable("response")));
    }
}
