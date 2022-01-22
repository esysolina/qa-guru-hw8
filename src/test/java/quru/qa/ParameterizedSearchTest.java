package quru.qa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedSearchTest {

    @CsvSource(value = {
            "Цвейг, зарубежная классика",
            "Харари, научно-популярная литература"
    }
    )

    @ParameterizedTest(name = "Тестирование выдачи жанров в поиске с тестовыми данными: {0}")
    void genreTest (String testData, String expectedResult) {
        open("https://www.litres.ru/");
        $(".adult-content-agreement__remind-later").click();
        $(".Search-module__input").setValue(testData);
        $(".Search-module__button").click();
        $(".rmd-genre-href").shouldHave(Condition.text(expectedResult));
    }
}

