package WebTesting.test;

import WebTesting.Utils.GWD;
import WebTesting.pom.Parent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Beymen_WebTest extends GWD {

    @Test
    public void Test() {


        GWD.getDriver().get("https://www.beymen.com/");

        WebElement cookies = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Tüm Çerezleri Kabul Et'])[2]")));
        cookies.click();

        WebElement homePage = driver.findElement(By.xpath("//a[text()='Sipariş Takibi']"));
        Assert.assertTrue(homePage.getText().contains("Sipariş Takibi"));

        String path = "src/test/java/WebTesting/pom/test.xls";

        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Ürün, Marka Arayın']"));
        Parent.sendKeysFunction(searchInput, Parent.getExcel(path, "test", 0, 0));
        Parent.sendKeysFunction(searchInput, Parent.getExcel(path, "test", 0, 1));

        Actions actions = new Actions(GWD.getDriver());
        actions.keyDown(Keys.ENTER);
        actions.keyUp(Keys.ENTER);
        Action action = actions.build();
        action.perform();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class,'itemWrapper')]"), 47));
        List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class,'itemWrapper')]"));

        int number = (int) (Math.random() * list.size());
        Parent.clickToElement(list.get(number));

        WebElement productTitle = driver.findElement(By.xpath("//h1[@class='o-productDetail__title']"));
        Parent.waitVisibilityOfElement(productTitle);

        String pathh = "src/test/java/WebTesting/pom/productName";
        Parent.getTxt(pathh, productTitle);



        quitDriver();


    }

}
