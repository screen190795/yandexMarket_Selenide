package page.object;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import static com.codeborne.selenide.Selenide.*;
public class ElectronicsPage {




    /*
     * Выбор категории товара
     */
    public  SmartPhonesPage addCategory() {
        try {
            SelenideElement categoryTab = $(By.xpath("//body//a[text()=\"Смартфоны\"]"));
            actions().moveToElement(categoryTab).click(categoryTab).perform();
        }catch(NoSuchElementException n){
            Assertions.fail("Элемент не найден");
        }
        return page(SmartPhonesPage.class);
    }


}
