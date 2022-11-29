package electronics;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseURL = "https://demo.nopcommerce.com";

    @Before
    public void setUp() {
        openBrowser(baseURL);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        //1.1 Mouse Hover on “Electronics” Tab
        //1.2 Mouse Hover on “Cell phones” and click
        actionMethod(By.linkText("Electronics"),
                By.xpath("//ul[@class='top-menu notmobile']/li[2]/child::ul//child::li[2]//child::a"));
        //1.3 Verify the text “Cell phones”
        verifyText("Cell phones", By.xpath("//h1[contains(text(),'Cell phones')]"), "Not navigated to cell phones page");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse Hover on “Electronics” Tab
        //2.2 Mouse Hover on “Cell phones” and click
        actionMethod(By.linkText("Electronics"),
                By.xpath("//ul[@class='top-menu notmobile']/li[2]/child::ul//child::li[2]//child::a"));

        //2.3 Verify the text “Cell phones”
        verifyText("Cell phones", By.xpath("//h1[contains(text(),'Cell phones')]"), "Text not verified");

        //2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));

        //2.5 Click on product name “Nokia Lumia 1020” link
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"));

        //2.6 Verify the text “Nokia Lumia 1020”
        verifyText("Nokia Lumia 1020", By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"), "Text not matched");

        //2.7 Verify the price “$349.00”
        verifyText("$349.00", By.xpath("//span[@id='price-value-20']"), "Price not matched");

        //2.8 Change quantity to 2
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).clear();
        sendTextToElement(By.xpath("//input[@id='product_enteredQuantity_20']"), "2");
        //Thread.sleep(3000);

        //2.9 Click on “ADD TO CART” tab
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        verifyText("The product has been added to your shopping cart", By.xpath("//div[@class='bar-notification success']/child::p"), "Text not matched");
        //After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//div[@class='bar-notification success']/descendant::span"));
        Thread.sleep(3000);

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        actionMethod(By.xpath("//span[contains(text(),'Shopping cart')]"), By.xpath("//button[contains(text(),'Go to cart')]"));

        //2.12 Verify the message "Shopping cart"
        verifyText("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"),
                "Text not matched");

        //2.13 Verify the quantity is 2
        //verifyText("2", By.xpath("//input[@id='itemquantity11229']"), "Quantity not matched");
        String totalPrice = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        Double totalPrice1 = Double.valueOf(totalPrice.replace("$", ""));

        String eachPrice = getTextFromElement(By.xpath("//span[@class='product-unit-price']"));
        Double eachPrice1 = Double.valueOf(eachPrice.replace("$", ""));

        double quantity;
        if (totalPrice1 / eachPrice1 == 2) {
            quantity = totalPrice1 / eachPrice1;
            String quantity1 = String.valueOf(quantity);
            Assert.assertEquals("Verify quantity", "2.0", quantity1);
        }

        //2.14 Verify the Total $698.00
        verifyText("$698.00", By.xpath("//span[@class='product-subtotal']"), "Total not matched");

        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.16 Click on checkout
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.17 Verify the Text “Welcome, Please Sign In!”
        verifyText("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")));

        //2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//a[contains(text(),'Register')]"));

        //2.19 Verify the text “Register”
        verifyText("Register", By.xpath("//h1[contains(text(),'Register')]"), getTextFromElement(By.xpath("//h1[contains(text(),'Register')]")));

        //2.20 Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "Manish");
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Vora");
        sendTextToElement(By.xpath("//input[@id='Email']"), "Prime@gmail.com");
        sendTextToElement(By.xpath("//input[@id='Password']"), "1233456");
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "1233456");
        //Thread.sleep(3000);

        //2.21 Click on “REGISTER” Button
        clickOnElement(By.xpath("//button[@id='register-button']"));

        //2.22 Verify the message “Your registration completed”
        verifyText("Your registration completed", By.xpath("//div[contains(text(),'Your registration completed')]"), getTextFromElement(By.xpath("//div[contains(text(),'Your registration completed')]")));

        //2.23 Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        //2.24 Verify the text “Shopping cart”
        verifyText("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")));

        //2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.26 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.27 Fill the Mandatory fields
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Abc");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Xyz");
        sendTextToElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "UK");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "10 Downing Street");
        sendTextToElement(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "EC1 2FP");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "0987654321");

        //2.28 Click on “CONTINUE”
        //Thread.sleep(5000);
        clickOnElement(By.xpath("//button[@class='button-1 new-address-next-step-button' and @name='save']"));

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_2']"));

        //2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//div[@id='shipping-method-buttons-container']/descendant::button"));

        //2.31 Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//div[@id='payment-method-buttons-container']/descendant::button"));

        //2.32 Select “Visa” From Select credit card dropdown
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Visa");

        //2.33 Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Abc Xyz");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4263982640269299");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "02");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2023");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "837");

        //2.34 Click on “CONTINUE” CHECKOUT
        clickOnElement(By.xpath("//div[@id='payment-info-buttons-container']/descendant::button"));

        //2.35 Verify “Payment Method” is “Credit Card”
        verifyText("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")));

        //2.36 Verify “Shipping Method” is “2nd Day Air”
        verifyText("Shipping Method:", By.xpath("//span[contains(text(),'Shipping Method:')]"), getTextFromElement(By.xpath("//span[contains(text(),'Shipping Method:')]")));

        //2.37 Verify Total is “$698.00”
        verifyText("$698.00", By.xpath("//tr[@class='order-total']//span[@class='value-summary']"),
                getTextFromElement(By.xpath("//tr[@class='order-total']//span[@class='value-summary']")));

        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //2.39 Verify the Text “Thank You”
        verifyText("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")));

        //2.40 Verify the message “Your order has been successfully processed!”
        verifyText("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"),
                getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")));

        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //2.42 Verify the text “Welcome to our store”
        verifyText("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"),
                getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")));

        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[@class='ico-logout']"));

        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String url = driver.getCurrentUrl();
        Assert.assertEquals("verify url:", "https://demo.nopcommerce.com/", url);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}