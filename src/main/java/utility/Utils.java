package utility;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Iterator;

public class Utils {

    public static boolean waitForPageLoad(WebDriver driver){
        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                if (js.executeScript("return document.readyState").toString().equals("complete"))
                    return true;
                else
                    return false;
            }
        };
        WebDriverWait wait = new WebDriverWait(driver,300);
        return  wait.until(condition);
    }

    public static boolean verifyImageIsDisplayed(WebDriver driver, By image){
        Object result = ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && "+
                        "typeof arguments[0].naturalWidth != \"undefined\" && "+
                        "arguments[0].naturalWidth > 0", driver.findElement(image));
        boolean loaded = (Boolean) result;
        return loaded;
    }

    public static void clickOnElement(WebDriver driver,By element){
        try{
            driver.findElement(element).click();
        }
        catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }

    public static boolean elementIsDisplayed(WebDriver driver,By element){
        try{
            return driver.findElement(element).isDisplayed();
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    public static boolean elementIsSelected(WebDriver driver,By element){
        try{
            return driver.findElement(element).isSelected();
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    public static WebElement findElement(WebDriver driver,By element){
        WebElement ele;
        try{
            ele = driver.findElement(element);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            throw (e);
        }
        return ele;
    }

    public static String fetchNode(JSONObject json, String key) {
        boolean exists = json.has(key);
        String value = "";
        if (!exists) {
            Iterator<String> itr = json.keys();
            while (itr.hasNext()){
                String new_key = itr.next();
                if (json.get(new_key) instanceof JSONObject){
                    exists = json.getJSONObject(new_key).has(key);
                    if (exists){
                        value = json.getJSONObject(new_key).get(key).toString();
                        break;
                    }
                }
            }
        }
        else {
            value = json.get(key).toString();
        }
        return value;
    }

}
