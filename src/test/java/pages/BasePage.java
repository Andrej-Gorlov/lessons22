package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.BrowserManager;
import helpers.ResultOutput;

public class BasePage extends BrowserManager {
    protected BasePage() {
        super();
    }

    /**
     * Вводит текст в указанный элемент.
     *
     * @param element элемент, в который будет введен текст.
     * @param text текст, который будет введен в элемент.
     * @param elementName имя элемента для логирования.
     */
    protected void enterText(SelenideElement element, String text, String elementName) {
        elementVisible(element);
        element.clear();
        ResultOutput.log("Выполняется ввод " + elementName);
        element.setValue(text);
    }

    /**
     * Нажимает на указанный элемент.
     *
     * @param element элемент, на который будет выполнен клик.
     * @param elementName имя элемента для логирования.
     */
    protected void clickElement(SelenideElement element, String elementName) {
        elementVisible(element); // Ожидание видимости элемента
        ResultOutput.log("Выполняется клик по " + elementName);
        element.click();
        getCurrentUrl();
    }

    /**
     * Проверяет, отображается ли указанный элемент.
     *
     * @param element элемент, который будет проверен на отображение.
     * @throws AssertionError если элемент не отображается.
     */
    protected void displayedText(SelenideElement element) {
        elementVisible(element);
        ResultOutput.log("Проверка отображение блока");
        if (!element.isDisplayed()) {
            throw new AssertionError("Блок не отображается");
        }
    }

    /**
     * Наводит курсор на элемент карты с указанным символом.
     *
     * @param element элемент-контейнер, содержащий символы карт.
     * @param symbol текст, который соответствует значению атрибута data-content элемента карты.
     */
    protected void hoverElement(SelenideElement element, String symbol) {
        elementVisible(element);
        ResultOutput.log("Наведение курсора на символ карты");
        SelenideElement card = element.$(String.format("a[data-content='%s']", symbol));
        elementVisible(card);
        card.hover();
    }

    /**
     * Получает содержимое поповера для символа карты.
     *
     * @param element элемент-контейнер, содержащий символы карт.
     * @param symbol текст, который соответствует значению атрибута data-content элемента карты.
     * @return содержимое атрибута data-content для указанного символа карты.
     */
    protected String getTextContent(SelenideElement element, String symbol) {
        elementVisible(element);
        ResultOutput.log("Получаем содержимое поповера для символа карты");
        SelenideElement card = element.$(String.format("a[data-content='%s']", symbol));
        elementVisible(card);
        return card.getAttribute("data-content");
    }

    private void elementVisible(SelenideElement element){
        waitForElementVisible(element);
    }
}
