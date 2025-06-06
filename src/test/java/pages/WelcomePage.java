package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.ResultOutput;

import static com.codeborne.selenide.Selenide.$;

public class WelcomePage extends BasePage{
    private final SelenideElement financialFreedomBlock = $("#accounts-can-spend .text");
    private final SelenideElement financialFreedomAmount = $("#accounts-can-spend .can-spend-amount .value");
    private final SelenideElement cardContainer = $(".account-cards");

    public WelcomePage() {
        super();
    }

    /**
     * Проверяет, отображается ли блок "Финансовая свобода" на странице.
     *
     * @return текущий объект WelcomePage для использования Fluent Interface.
     */
    public WelcomePage checkFinancialFreedomVisible() {
        displayedText(financialFreedomBlock);
        return this;
    }

    /**
     * Извлекает текст суммы из элемента веб-страницы "Финансовая свобода"
     * и форматирует его, добавляя символ валюты.
     *
     * @return отформатированная строка суммы.
     */
    public String retrieveAndFormatFinancialFreedomAmount() {
        ResultOutput.log("Получаем текст суммы из элемента и форматируем его");
        String amountText = financialFreedomAmount.getText().replace("\u00A0", " ");
        return formatAmount(amountText);
    }

    /**
     * Наводит курсор на символ карты, используя указанный текст символа.
     *
     * @param cardSymbol текст, который соответствует значению атрибута data-content элемента карты.
     * @return текущий объект WelcomePage для использования Fluent Interface.
     */
    public WelcomePage hoverOverCardSymbol(String cardSymbol) {
        hoverElement(cardContainer, cardSymbol);
        return this;
    }

    /**
     * Получает содержимое поповера для символа карты, используя указанный текст символа.
     *
     * @param cardSymbol текст, который соответствует значению атрибута data-content элемента карты.
     * @return строка, содержащая текст поповера для указанного символа карты.
     */
    public String getCardPopoverContent(String cardSymbol) {
        return getTextContent(cardContainer, cardSymbol);
    }

    /**
     * Форматирует строку суммы, заменяя неразрывные пробелы и добавляя символ валюты.
     *
     * @param amount строка, представляющая сумму.
     * @return отформатированная строка суммы в формате "число ₽".
     */
    private String formatAmount(String amount) {
        ResultOutput.log("Форматируем сумму");
        return amount.replace("&nbsp;", " ").trim() + " ₽";
    }
}
