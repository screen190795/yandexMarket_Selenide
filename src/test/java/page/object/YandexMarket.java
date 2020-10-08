package page.object;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;

public class YandexMarket {
    private final WebDriver driver;

     private final String marketMainURL="https://market.yandex.ru/";


    //кнопка в списке товаров "Показать ещё"
    @FindBy(how=How.XPATH, using = "//button[contains(text(),\"Показать ещё\")]")
    public WebElement showNextButton;

    //Название товара в результатах поиска
    @FindAll(@FindBy(how=How.XPATH, using = "//div[@data-zone-name=\"SearchResults\"]//article//a/span"))
    public List<WebElement> productTitles;

    public YandexMarket(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToElement(WebElement linkElement){
        Actions builder = new Actions(driver);
        builder.moveToElement(linkElement).click(linkElement);
        Action mouseoverAndClick = builder.build();
        mouseoverAndClick.perform();
    }

    /*
     * Переход по вкладке основного меню
     */
    public void goByMainMenuTab(String tabName){
        try{
            Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(tabName))));
        WebElement tab = driver.findElement(By.linkText(tabName));
        this.clickToElement(tab);
        }catch(NoSuchElementException n ){
            Assertions.fail("Элемент не найден");
        }
    }


    /*
     * Выбор категории товара
     */
    public  void addCategory(String category) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//body//a[text()=\"" + category + "\"]"))));
            WebElement categoryTab = driver.findElement(By.xpath("//body//a[text()=\"" + category + "\"]"));
            this.clickToElement(categoryTab);
        }catch(NoSuchElementException n){
            Assertions.fail("Элемент не найден");
            }
        }



    /*
     * Выбор фильтра "Производитель"
     */
    public void addMakerFilter(String maker){
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, 20, 1000);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//input[@type='checkbox'][contains(@name,\"Производитель\")]"))));
            WebElement makerTab = driver.findElement(By.xpath("//input[@type='checkbox'][@name='Производитель " + maker + "']"));
            this.clickToElement(makerTab);
        } catch (NoSuchElementException n){   fail("Элемент не найден");}

    }

    public void goToMainURL(){
        driver.get(marketMainURL);
    }


    /*
     * Открытие всех товаров в списке
     */
    public void  loadPages()  {
        try {
        while (showNextButton.isDisplayed()) {
            this.clickToElement(this.showNextButton);
        }
            } catch (NoSuchElementException n){
            return;
        }
    }

    /*
     * Сбор названий всех товаров в результате поиска
     */
    public List<String> collectTitles(){
        new WebDriverWait(driver, 20).until(
                ExpectedConditions.presenceOfAllElementsLocatedBy
                        ((By.xpath("//div[@data-zone-name=\"SearchResults\"]//article//a/span"))));
        List<String> titles = this.productTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        titles.forEach(System.out::println);
        System.out.println(titles.size());
        System.out.println(productTitles.size());
        return  titles;
    }

}
