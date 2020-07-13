package StepDefinition;

import Page.MainPage;
import Utils.Driver;
import Utils.StringConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;


public class Steps {
    private MainPage mainPage;
    private static Driver driver;
    private static ExtentReports report;
    private static ExtentTest test;

    @Before
    public static void instantiateReport() {
        report = new ExtentReports("TestReport.html", true);
    }

    @Given("^Open the Tripadvisor website$")
    public void open_the_Tripadvisor_website() {
        try {
            driver = new Driver();
            driver.setUpDriver();
            mainPage = new MainPage(driver.getDriver());
            mainPage.openWebsite();
            test = report.startTest("Searching top restaurants and places to visit.");
            test.log(LogStatus.PASS, "Tripadvisor website has been opened.");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Tripadvisor website could not be opened.");
        }
    }

    @When("^Entering the city where you go \"([^\"]*)\"$")
    public void entering_the_city_where_you_go(String city) {
        try {
            mainPage.enterDestination(city);
            test.log(LogStatus.PASS, "The city has been entered");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "The city could not be entered.");
        }

    }

    @And("^Searching restaurants in the city$")
    public void searching_restaurants_in_the_city() {
        try {
            mainPage.clickOnRestaurants();
            test.log(LogStatus.PASS, "The restaurants webpage has been opened.");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "The restaurants webpage isn't working.");
        }

    }

    @Then("^Creating a list with top five restaurants$")
    public void creating_a_list_with_top_five_restaurants() {
        try {
            List<String> listOfRest = mainPage.topRestaurants();
            test.log(LogStatus.INFO, "The top " + StringConstants.TOP_FIVE + " restaurants:");
            for (String list : listOfRest) {
                test.log(LogStatus.INFO, list);
            }
        } catch (Exception e) {
            test.log(LogStatus.ERROR, "Couldn't show the restaurants, there isn't any records");
        }
    }

    @When("^Searching top attractions in the city$")
    public void searching_top_attractions_in_the_city() {
        try {
            mainPage.findAttractions();
            test.log(LogStatus.PASS, "The attractions webpage has been opened");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "The attractions webpage isn't working");
        }
    }

    @Then("^Add to the list the first five places$")
    public void add_to_the_list_the_first_five_places() {
        try {
            List<String> attractions = mainPage.topAttractions();
            test.log(LogStatus.INFO, "The top " + StringConstants.TOP_PLACES + " attractions:");
            for (String attraction : attractions) {
                test.log(LogStatus.INFO, attraction);
            }
            test.log(LogStatus.PASS, "The search has been ended successfully!");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Couldn't show the attractions, there isn't any records");
        } finally {
            driver.closeDriver();
        }
    }

    @After
    public static void closeReport() {
        report.endTest(test);
        report.flush();
    }
}
