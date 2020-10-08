import org.junit.jupiter.api.Test;
import page.object.MainPage;
import static com.codeborne.selenide.Selenide.open;


public class Tests  {

    @Test
    public void YandexMarketMaker() {
        open("https://market.yandex.ru/", MainPage.class)
                .goToElectronicsPage()
                .addCategory()
                .addMakerFilter("Apple")
                .loadPages()
                .collectTitles();
    }

}
