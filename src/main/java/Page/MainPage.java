package Page;

import Utils.StringConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    @FindBy(css = "#lithium-root > main > div.U2O9sR7- > div > div > div._12nbOYJX > div._3-KjE8YR > div > form > input._3qLQ-U8m")
    private WebElement whereYouGo;
    @FindBy(css = "#lithium-root > main > div.U2O9sR7- > div > div > div._12nbOYJX > div._3-KjE8YR > div.i3bZ_gBa._2RTs3_Ee._3TPJs5_m > form > div > a:nth-child(1)")
    private WebElement select;
    @FindBy(css = "#lithium-root > main > div._1brQmsfe > div > div > div:nth-child(5) > a > span > span._1Qo7YQ01")
    private WebElement restaurants;
    @FindBy(css = "#global-nav-attractions")
    private WebElement attractions;
    private final String ATTRACTIONS_LIST_LOCATOR = "_255i5rcQ";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openWebsite() {

        driver.get(StringConstants.TRIP_WEBSITE);
    }

    public void enterDestination(String city) {
        whereYouGo.sendKeys(city);
        select.click();
    }

    public void clickOnRestaurants() {
        restaurants.click();
    }

    public List<String> topRestaurants() {
        List<WebElement> elements = driver.findElements(By.className("_15_ydu6b"));
        List<String> listofelements = new ArrayList<String>();
        for (int i = 1; i <= StringConstants.TOP_FIVE; i++) {
            listofelements.add(elements.get(i).getText());
        }
        return listofelements;
    }

    public void findAttractions() {
        attractions.click();
    }

    public List<String> topAttractions() {
        List<WebElement> places = driver.findElements(By.className(ATTRACTIONS_LIST_LOCATOR));
        List<String> listofplaces = new ArrayList<String>();
        for (int i = 0; i < StringConstants.TOP_PLACES; i++) {
            listofplaces.add(places.get(i).getText());
        }
        return listofplaces;
    }
}

