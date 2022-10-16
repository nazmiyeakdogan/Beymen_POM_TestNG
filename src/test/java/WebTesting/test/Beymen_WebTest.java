package WebTesting.test;

import WebTesting.Utils.GWD;
import WebTesting.pom.Parent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

        List<WebElement> sizeList = driver.findElements(By.xpath("//div[@class='m-variation']//span"));
        if(sizeList.size() > 0){
            WebElement getSize = driver.findElement(By.cssSelector(".m-variation__item:not(.-disabled)"));
            Parent.clickToElement(getSize);
        }else {
            System.out.println("ürün stokları tükenmiştir");
        }

        WebElement addToBasket = driver.findElement(By.xpath("//button[@id='addBasket']"));
        Parent.clickToElement(addToBasket);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Sepete Git')]")));
        WebElement basket = driver.findElement(By.xpath("//*[contains(text(),'Sepete Git')]"));
        Parent.clickToElement(basket);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='m-productPrice__salePrice']")));
        WebElement price1 = driver.findElement(By.xpath("//span[@class='m-productPrice__salePrice']"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='m-orderSummary__item -grandTotal']//span[@class='m-orderSummary__value']")));
        WebElement price2 = driver.findElement(By.xpath("//li[@class='m-orderSummary__item -grandTotal']//span[@class='m-orderSummary__value']"));

        WebElement kargo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='m-orderSummary__item'])[2]//span[@class='m-orderSummary__value']")));


        int intKargo = Integer.parseInt(kargo.getText().replaceAll("[^0-9]", ""));
        if(intKargo == 0 ){
            Assert.assertTrue(price1.getText().contains(price2.getText()));
        }else {
            System.out.println("ürün için " + intKargo + " TL  kargo ücreti eklenmiştir");
        }

        WebElement piece = driver.findElement(By.id("quantitySelect0-key-0"));
        Select menu = new Select(piece);
        WebElement control = driver.findElement(By.xpath("(//option[@value])[2]"));
        if (menu.getOptions().size() > 1){
            menu.selectByIndex(1);
            System.out.println("Adet : 2 adet");
            Assert.assertTrue(piece.getText().contains(control.getText()));
        }else {
            System.out.println("yeteri kadar stok bulunmamaktadır");
        }


        quitDriver();


    }

}
