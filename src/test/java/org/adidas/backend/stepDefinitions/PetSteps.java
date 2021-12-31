package org.adidas.backend.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.adidas.backend.modules.PetModule;
import org.adidas.backend.supportFunctions.CommonsModule;
import org.junit.Assert;

public class PetSteps {
    PetModule petModule = new PetModule();

    @Given("^the user asks for (available) pets$")
    public void getPetsByStatus(String status) {
        petModule.getPetsByStatus(status);
    }

    @Then("^the user has received (available|pending|sold) pets$")
    public void verifyPetsReceivedByStatus(String status) {
        Assert.assertTrue("", petModule.verifyPetsReceivedByStatus(status));
    }

    @Given("^the user creates a new pet$")
    public void createPet() {
        petModule.createPet();
    }

    @When("^the user changes the pet status to (available|pending|sold)$")
    public void changePetStatus(String status) {
        petModule.changePetStatus(CommonsModule.getSessionVariable("id"), status);
    }

    @When("^the user deletes the pet$")
    public void deletePet() {
        petModule.deletePet(CommonsModule.getSessionVariable("id"));
    }

    @Then("^a pet is created$")
    public void verifyPetIsCreated() {
        Assert.assertTrue("Pet's ids don't match.", petModule.verifyPetIsCreated(CommonsModule.getSessionVariable("id")));
    }

    @Then("^pet status is (available|pending|sold)$")
    public void verifyPetStatus(String status) {
        Assert.assertEquals("Pet's status don't match.", status, petModule.getPetStatus(CommonsModule.getSessionVariable("id")));
    }

    @Then("^pet is deleted$")
    public void verifyPetIsDeleted() {
        Assert.assertTrue("Pet's status don't match.", petModule.verifyPetIsDeleted(CommonsModule.getSessionVariable("id")));
    }
}
