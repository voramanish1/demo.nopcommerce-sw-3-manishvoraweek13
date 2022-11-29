package computer;

import com.google.common.base.Verify;
import homepage.TopMenuTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuit extends TopMenuTest {
    String baseUrl = "https://demo.nopcommerce.com";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsArrangeInAlphaBaticalOrder() throws InterruptedException {
        //1.1 Click on Computers menu
        selectMenu("Computers");
        //1.2 Click on Desktop
        selectMenu("Desktops");

        //1.4 Verify the products will arrange in Descending order
        //Before filter get product list

        List<WebElement> beforeFilterProduct = driver.findElements(By.className("product-title"));
        List<String> beforeFilterProductList = new ArrayList<>();
        for (WebElement element : beforeFilterProduct) {
            beforeFilterProductList.add(element.getText());
        }

        //1.3 Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");
        //After filter get product list
        List<WebElement> afterFilterProduct = driver.findElements(By.className("product-title"));
        List<String> afterFilterProductList = new ArrayList<>();
        for (WebElement element : afterFilterProduct) {
            afterFilterProductList.add(element.getText());
        }

        //Verify products now
        Collections.sort(beforeFilterProductList);    //List will sort in ascending order
        Collections.reverse(beforeFilterProductList);   //List will sort in descending order
        Assert.assertEquals(beforeFilterProductList, afterFilterProductList);

        String expectedMessage = "Name: Z to A";
        //Actual Text
        String actualMessage = getTextFromElement(By.xpath("//select[@id='products-orderby']/option[3]"));
        //Validate actual and expected
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //2.1 Click on Computer Menu
        selectMenu("Computers");
        //2.2 Click on Desktop
        selectMenu("Desktops");
        //2.3 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Name: A to Z");
        //2.4 Click on "Add To Cart"
        actionMethod(By.xpath("//div[@class='picture']/descendant::img[@alt='Picture of Build your own computer']"),
                By.xpath("//div[@class='buttons']/child::button"));

        //2.5 Verify the Text "Build your own computer"
        verifyText("Build your own computer", By.linkText("Build your own computer"), getTextFromElement(By.linkText("Build your own computer")));
        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"),
                "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]");
        //2.7.Select "8GB [+$60.00]" using Select class
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_2']"), "8GB [+$60.00]");
        //2.8 Select HDD radio "400 GB [+$100.00]"
        Thread.sleep(5000);
        clickOnElement(By.id("product_attribute_3_7"));
        //2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_8"));
        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        Thread.sleep(2000);
        clickOnElement(By.id("product_attribute_5_10"));
        clickOnElement(By.id("product_attribute_5_12"));
        Thread.sleep(3000);
        //2.11 Verify the price "$1,475.00"
        verifyText("$1,430.00", By.xpath("//span[@id='price-value-1']"), getTextFromElement(By.xpath("//span[@id='price-value-1']")));
        //2.12 Click on "ADD TO CARD" Button
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        verifyText("The product has been added to your shopping cart", By.xpath("//p[@class='content']"),
                getTextFromElement(By.xpath("//p[@class='content']")));
        //2.13 After that close the bar clicking on the cross button
        clickOnElement(By.xpath("//p[@class='content']/following-sibling::span"));
        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button
        Thread.sleep(5000);
        actionMethod(By.xpath("//span[contains(text(),'Shopping cart')]"), By.xpath("//button[contains(text(),'Go to cart')]"));
        //2.15 Verify the message "Shopping cart"
        verifyText("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"),
                getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")));
        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        Thread.sleep(5000);
        WebElement quantity = driver.findElement(By.xpath("//td[@class='quantity']/child::input"));
        quantity.clear();
        quantity.sendKeys("2");
        actionMethod(By.xpath("//tbody/tr[1]/td[4]"), By.xpath("//button[@id='updatecart']"));
        //2.17 Verify the Total"$2,950.00"
        verifyText("$2,860.00", By.xpath("//tr[@class='order-total']/child::td//following::td/child::span"),
                getTextFromElement(By.xpath("//tr[@class='order-total']/child::td//following::td/child::span")));
        //2.18 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.19 Click on “CHECKOUT”
        actionMethod(By.xpath("//div[@class='checkout-buttons']"), By.xpath("//button[@id='checkout']"));

        //2.20 Verify the Text “Welcome, Please Sign In!”
        verifyText("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"),
                getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")));
        //2.21 Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//strong[contains(text(),'Checkout as a guest or register')]"));
        //2.22 Fill the all mandatory field
        actionMethod(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]"),
                By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        //2.22 Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Manish");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Vora");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "Prime@gmail.com");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United States");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']"), "Alabama");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "New York");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "1, Bbbb");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "12345");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "9876543210");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "New Jersey");

        //2.23 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        //2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));

        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        //2.26 Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        Thread.sleep(5000);
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        Thread.sleep(5000);
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        //2.28 Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Abc Xyz");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5425233430109903");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "04");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2023");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "174");

        //2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));

        //2.30 Verify “Payment Method” is “Credit Card”
        verifyText("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")));
        //2.32 Verify “Shipping Method” is “Next Day Air”
        verifyText("Next Day Air", By.xpath("//span[contains(text(),'Next Day Air')]"), getTextFromElement(By.xpath("//span[contains(text(),'Next Day Air')]")));
        //2.33 Verify Total is “$2,950.00”
        verifyText("$2,860.00", By.xpath("//span[@class='value-summary']/child::strong"), getTextFromElement(By.xpath("//span[@class=\"value-summary\"]/child::strong")));
        //2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        //2.35 Verify the Text “Thank You”
        verifyText("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")));
        //2.36 Verify the message “Your order has been successfully processed!”
        verifyText("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"),
                getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")));
        //2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        //2.37 Verify the text “Welcome to our store”
        verifyText("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"), getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")));
    }
}
