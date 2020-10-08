package page.object;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.*;;

public class MainPage {

    private final String marketMainURL = "https://market.yandex.ru/";

    /*
     * Переход по вкладке основного меню
     */
    public ElectronicsPage goToElectronicsPage() {
        SelenideElement tab = $(By.linkText("Электроника"));
        actions().moveToElement(tab).click(tab).perform();
        return page(ElectronicsPage.class);
    }
}