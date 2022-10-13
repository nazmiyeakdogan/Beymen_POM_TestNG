package WebTesting.pom;

import WebTesting.Utils.GWD;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class Parent {

    public static void clickToElement(WebElement element){

        waitUntilClickable(element);
        element.click();
    }


    public static void waitUntilClickable(WebElement element){

        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public static void waitVisibilityOfElement(WebElement element){

        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void sendKeysFunction(WebElement element, String value){

        waitVisibilityOfElement(element);
        element.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        element.sendKeys(value);
    }

    public static String getExcel(String path,String sheetName, int rowNo, int cellNo){

        Workbook workbook = null;
        try {
            FileInputStream fs = new FileInputStream(path);
            workbook = WorkbookFactory.create(fs);

        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNo);

        Cell cell = row.getCell(cellNo);

        return cell.toString();
    }

    public static void getTxt(String pathh, WebElement element){
        FileWriter fw = null;
        try {
            fw = new FileWriter(pathh);
            fw.write(element.getText());
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
