package com.automation.framework.pages;

import com.automation.framework.locators.SauceDemoLocators;
import com.automation.framework.uiUtilities.WebDriverUtils;
import com.automation.framework.utils.ProjectInputFile;
import com.automation.framework.utils.SauceDemoConstants;
import com.google.gson.JsonElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SauceLogin {

    public static void loginIntoSauceDemo(String afterLoginProjectLabel) {
        WebDriverUtils.typeText(SauceDemoLocators.username, ProjectInputFile.getProjectInput(SauceDemoConstants.USERNAME));
        WebDriverUtils.typeText(SauceDemoLocators.password, ProjectInputFile.getProjectInput(SauceDemoConstants.PASSWORD));
        WebDriverUtils.click(SauceDemoLocators.loginButton);
        Assert.assertEquals(WebDriverUtils.getText(SauceDemoLocators.inventoryPageLabel), afterLoginProjectLabel);
    }

    public static void verifyProductPriceOnInventoryAndPage(String productName) {
        String productPriceOnInventoryPage = WebDriverUtils.getText(SauceDemoLocators.inventoryPageProductPriceByName(productName));
        WebDriverUtils.click(SauceDemoLocators.inventoryPagesProductByName(productName));
        String productNameOnProductPage = WebDriverUtils.getText(SauceDemoLocators.productPageProductName);
        String productPriceOnProductPage = WebDriverUtils.getText(SauceDemoLocators.productPageProductPrice);
        Assert.assertEquals(productNameOnProductPage, productName);
        Assert.assertEquals(productPriceOnInventoryPage, productPriceOnProductPage);
    }

    public static void addElementToCartAndVerify(List<String> productsAddToCart) {
        productsAddToCart.forEach(product -> {
            WebDriverUtils.click(SauceDemoLocators.inventoryPageAddToCartByProductName(product));
        });
        WebDriverUtils.click(SauceDemoLocators.cartIcon);
        List<String> productsInCart = new ArrayList<>(WebDriverUtils.getWebElementsText(SauceDemoLocators.getProductNameListInCart));
        productsAddToCart.sort(String::compareTo);
        productsInCart.sort(String::compareTo);
        Assert.assertEquals(productsAddToCart, productsInCart);


    }
}
