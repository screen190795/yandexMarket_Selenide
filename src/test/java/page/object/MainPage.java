package page.object;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;;

public class MainPage {
    /*
     * Переход по вкладке основного меню
     */
    public ElectronicsPage goToElectronicsPage() {
        SelenideElement tab = $(By.linkText("Электроника"));
        actions().moveToElement(tab).click(tab).perform();
        return page(ElectronicsPage.class);
    }
}