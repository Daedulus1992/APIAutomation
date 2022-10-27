package CucumberExample.Automation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition {
    @Given("^User is on NetBanking landing page$")
    public void user_is_on_netbanking_landing_page(){
        System.out.println("USer is on landing page");
    }
    @When("^User login with username and password$")
    public void user_login_with_username_password(){
        System.out.println("user login with username and password");
    }
    @Then("^Home page is populated$")
    public void home_page_is_displayed(){
        System.out.println("home page is displayed");
    }
    @And("^Cards are displayed$")
    public void cards_are_displayed(){
        System.out.println("cards are displayed");
    }
}
