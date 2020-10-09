package page.object;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

import static org.junit.jupiter.api.Assertions.fail;

public class SmartPhonesPage {

    private SelenideElement showNextButton=$(By.xpath("//button[contains(text(),\"Показать ещё\")]"));
    private ElementsCollection productTitles = $$(By.xpath("//div[@data-zone-name=\"SearchResults\"]//article//a/span"));

    /*
     * Выбор фильтра "Производитель"
     */
    public SmartPhonesPage addMakerFilter(String maker){
        try {
            SelenideElement makerTab = $(By.xpath("//input[@type='checkbox'][@name='Производитель " + maker + "']"));
            actions().moveToElement(makerTab).click(makerTab).perform();
            return page(SmartPhonesPage.class);
        } catch (NoSuchElementException n){   fail("Элемент не найден");}
        return null;
    }
    /*
     * Открытие всех товаров в списке
     */
    public SmartPhonesPage loadPages() {
        try {
            while (showNextButton.isDisplayed()) {
                actions().moveToElement(showNextButton).click(showNextButton).perform();
            }
        } catch (NoSuchElementException n) {
            return page(SmartPhonesPage.class);
        }
        return this;
    }

    /*
     * Сбор названий всех товаров в результате поиска
     */
    public List<String> collectTitles(){
       productTitles = $$(By.xpath("//div[@data-zone-name=\"SearchResults\"]//article//a/span"));
        List<String> titles = this.productTitles.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
        Assertions.assertTrue(titles.stream().allMatch(x->x.contains("Apple")), "Несоответствие товара производителю");
        return  titles;
    }
}
