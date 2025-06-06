package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class BrowserManager {
    public BrowserManager(){
        Configuration.browser = "chrome";
        open("https://idemo.bspb.ru");
        ResultOutput.log("Загрузка страницы: https://idemo.bspb.ru");
    }

    /**
     * Ожидание, пока указанный элемент не станет видимым.
     *
     * @param element элемент который нужно ожидать.
     */
    protected void waitForElementVisible(SelenideElement element) {
        ResultOutput.log("Проверка видимости элемента: " + element);
        element.shouldBe(visible); // Используем встроенное ожидание Selenide
    }

    /**
     * Получает текущий URL страницы.
     *
     * @return Строка, представляющая текущий URL страницы.
     */
    protected String getCurrentUrl(){
        String currentUrl = url();
        ResultOutput.log("Текущий URL страницы: " + currentUrl);
        return currentUrl;
    }

    public static void closeDriver() {
        closeWebDriver();
    }
}
