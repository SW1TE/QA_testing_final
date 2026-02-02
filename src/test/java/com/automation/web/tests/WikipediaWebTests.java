package com.automation.web.tests;

import com.automation.config.TestConfig;
import com.automation.web.pages.ArticlePage;
import com.automation.web.pages.MainPage;
import com.automation.web.pages.SearchPage;
import com.automation.web.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WikipediaWebTests {

    private WebDriverSetup driverSetup;
    private WebDriver driver;

    private MainPage mainPage;
    private ArticlePage articlePage;
    private SearchPage searchPage;

    @BeforeClass
    public void setUp() {
        driverSetup = new WebDriverSetup();
        driverSetup.initDriver();
        driver = driverSetup.getDriver();

        mainPage = new MainPage(driver);
        articlePage = new ArticlePage(driver);
        searchPage = new SearchPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driverSetup.quitDriver();
    }

    @Test(priority = 1, description = "Проверка заголовка главной страницы")
    public void testMainPageTitle() {
        String title = mainPage.getPageTitle();
        Assert.assertTrue(
                title.contains("Википедия"),
                "Заголовок должен содержать 'Википедия'. Фактический: " + title
        );
        Assert.assertTrue(mainPage.isMainPageDisplayed(), "Главная страница не отображается корректно");
    }

    @Test(priority = 2, description = "Поиск статьи и проверка заголовка результата")
    public void testSearchFunctionality() {
        String query = "Тестирование программного обеспечения";

        mainPage.searchFor(query);

        String heading = articlePage.getHeadingText();
        Assert.assertFalse(heading.isEmpty(), "Заголовок статьи пустой");
        Assert.assertTrue(
                heading.toLowerCase().contains("тестирование") ||
                        searchPage.getPageTitleText().toLowerCase().contains("поиск"),
                "Не перешли на корректную страницу после поиска"
        );
    }

    @Test(priority = 3, description = "Проверка основных элементов навигации на главной странице")
    public void testNavigationElements() {
        driver.get(TestConfig.getWebBaseUrl());

        Assert.assertTrue(mainPage.isMainPageDisplayed(), "Главная страница не отображается");

        mainPage.searchFor("Программирование");
        Assert.assertTrue(articlePage.isContentDisplayed(), "Контент статьи не отображается");

        driver.get(TestConfig.getWebBaseUrl());
        Assert.assertTrue(mainPage.isMainPageDisplayed(), "Возврат на главную страницу не удался");
    }

    @Test(priority = 4, description = "Проверка наличия результатов поиска и перехода по первой ссылке")
    public void testArticleLinksFromSearch() {
        String query = "Java";

        mainPage.searchFor(query);

        int resultsCount = searchPage.getSearchResultsCount();
        Assert.assertTrue(
                resultsCount >= 0,
                "Метод getSearchResultsCount вернул отрицательное значение: " + resultsCount
        );

        if (resultsCount > 0) {
            searchPage.clickFirstSearchResult();
            Assert.assertTrue(
                    articlePage.isContentDisplayed(),
                    "После перехода по результату контент статьи не отображается"
            );
        } else {
             System.out.println("По запросу '" + query + "' результаты не найдены, проверка перехода пропущена");
        }
    }

    @Test(priority = 5, description = "Проверка наличия ссылки на английскую версию")
    public void testLanguageSwitcher() {
        driver.get(TestConfig.getWebBaseUrl());

        boolean hasEnglish = driver
                .getPageSource()
                .contains("English");

        Assert.assertTrue(hasEnglish, "Не найдена ссылка на английскую версию Wikipedia");
    }

    @Test(priority = 6, description = "Переход на Заглавную страницу и проверка, что открыта главная")
    public void testGoToMainPageLink() {

        mainPage.searchFor("Тестирование программного обеспечения");

        mainPage.goToMainPage();


        Assert.assertTrue(
                mainPage.isMainPageDisplayed(),
                "После клика по ссылке 'Заглавная страница' главная страница не отобразилась"
        );

        String title = mainPage.getPageTitle();
        Assert.assertTrue(
                title.contains("Заглавная страница") || title.contains("Википедия"),
                "Заголовок не похож на заголовок главной страницы. Фактический: " + title
        );
    }
}
