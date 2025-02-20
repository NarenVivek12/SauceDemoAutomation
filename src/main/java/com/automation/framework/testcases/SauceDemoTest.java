package com.automation.framework.testcases;

import com.automation.framework.pages.SauceLogin;
import com.automation.framework.uiUtilities.BaseClass;
import com.automation.framework.uiUtilities.WebDriverUtils;
import com.automation.framework.utils.FrameWorkDataProvider;
import com.automation.framework.utils.ProjectInputFile;
import com.automation.framework.utils.SauceDemoConstants;
import com.google.gson.JsonElement;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Test(dataProviderClass = FrameWorkDataProvider.class,dataProvider = "FrameWorkDataProvider")
public class SauceDemoTest extends BaseClass {


    @Test(dataProviderClass = FrameWorkDataProvider.class,dataProvider = "FrameWorkDataProvider")
    public void loginInSauceDemo(JsonElement inputJsonElement){
        System.out.println(inputJsonElement.getAsJsonObject());
        SauceLogin.loginIntoSauceDemo(inputJsonElement.getAsJsonObject().get("inventoryPageLabel").getAsString());

        Reporter.log("Executed Framework Test");
    }

    @Test
    public void verifyCookieUserName(JsonElement inputJsonElement){
        SauceLogin.loginIntoSauceDemo(inputJsonElement.getAsJsonObject().get("inventoryPageLabel").getAsString());
        Set<Cookie> sauceDemoCookie = WebDriverUtils.getCookies();
        sauceDemoCookie.forEach(cookie->{
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
        });
    }
    @Test
    public void verifySessionStorageUserName(JsonElement inputJsonElement){
        SauceLogin.loginIntoSauceDemo(inputJsonElement.getAsJsonObject().get("inventoryPageLabel").getAsString());
        Map<String,String> sessionStorageMap = WebDriverUtils.getSessionStorage();
        String sessionUserName = sessionStorageMap.get(inputJsonElement.getAsJsonObject().get("sessionUsernameKey").getAsString());
        Assert.assertEquals(sessionUserName, ProjectInputFile.getProjectInput(SauceDemoConstants.USERNAME));
    }

    @Test
    public void verifyCostOfProductSameOnInventoryAndProductPage(JsonElement inputJsonElement){
        SauceLogin.loginIntoSauceDemo(inputJsonElement.getAsJsonObject().get("inventoryPageLabel").getAsString());
        String productName = inputJsonElement.getAsJsonObject().get("productName").getAsString();
        SauceLogin.verifyProductPriceOnInventoryAndPage(productName);
    }

    @Test
    public void verifyProductsAreAddedToCart(JsonElement inputJsonElement){
        SauceLogin.loginIntoSauceDemo(inputJsonElement.getAsJsonObject().get("inventoryPageLabel").getAsString());
        List<String> productsAddToCart = new ArrayList<>(inputJsonElement.getAsJsonObject().get("productsAddToCart").getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList());
        SauceLogin.addElementToCartAndVerify(productsAddToCart);
    }
}
