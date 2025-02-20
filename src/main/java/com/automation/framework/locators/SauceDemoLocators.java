package com.automation.framework.locators;

import org.openqa.selenium.By;

public class SauceDemoLocators {


    public static By username = By.id("user-name");
    public static By password = By.id("password");
    public static By loginButton = By.id("login-button");
    public static By inventoryPageLabel = By.xpath("//div[@id='inventory_filter_container']/div[@class='product_label']");

    public static By inventoryPagesProductByName(String productName){return By.xpath("//*[text()='"+productName+"']");}
    public static By inventoryPageProductPriceByName(String productName){return By.xpath("//*[text()='"+productName+"']//ancestor::div[@class='inventory_item']//div[@class='pricebar']/div");}
    public static By inventoryPageAddToCartByProductName(String productName){
        return By.xpath("//*[text()='"+productName+"']//ancestor::div[@class='inventory_item']//button");
    }

    public static By cartIcon = By.id("shopping_cart_container");

    public static By productPageProductName = By.xpath("//div[@class='inventory_details']//div[@class='inventory_details_desc_container']//div[@class='inventory_details_name']");
    public static By productPageProductPrice = By.xpath("//div[@class='inventory_details']//div[@class='inventory_details_desc_container']//div[@class='inventory_details_price']");

    public static By getProductNameListInCart = By.xpath("//div[@class='cart_list']//div[@class='cart_item']//div[@class='inventory_item_name']");

}
