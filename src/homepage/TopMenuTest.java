package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }

    @Test
    public void verifyPageNavigation() {
        //use selectMenu method to click on menu
        selectMenu("Computers");
        //verify page is navigated to selected menu
        verifyText("Computers",
                By.xpath("//h1[contains(text(),'Computers')]"),
                "Not navigated to selected menu");
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
