package quru.qa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedSearchTests {

    @ValueSource(strings = {"Акунин", "Фейнман"})
    @ParameterizedTest(name = "Тестирование выдачи авторов в поиске с тестовыми данными: {0}")
    void authorTest(String testAuthorsData) {
        open("https://www.litres.ru/");
        $(".Search-module__input").setValue(testAuthorsData);
        $(".Search-module__input").shouldBe(visible);
        $(".Search-module__button").click();
        $(".search__found").shouldHave(Condition.text(testAuthorsData));
    }

    @CsvSource(value = {
            "Цвейг, зарубежная классика",
            "Харари, научно-популярная литература"
    }
    )

    @ParameterizedTest(name = "Тестирование выдачи жанров в поиске с тестовыми данными: {0}")
    void genreTest(String testData, String expectedResult) {
        open("https://www.litres.ru/");
        $(".Search-module__input").setValue(testData);
        $(".Search-module__button").click();
        $(".rmd-genre-href").shouldHave(Condition.text(expectedResult));
    }

    static Stream<Arguments> nameTestDataProvider() {
        return Stream.of(
                Arguments.of("Красная таблетка", "Андрей Курпатов"),
                Arguments.of("Сто лет одиночества", "Габриэль Гарсиа Маркес")
        );
    }

    @MethodSource("nameTestDataProvider")
    @ParameterizedTest(name = "Тестирование выдачи авторов по названию в поиске с тестовыми данными: {0}")
    void nameTest(String testsData, String expectedResults) {
        open("https://www.litres.ru/");
        $(".Search-module__input").setValue(testsData);
        $(".Search-module__button").click();
        $(".art-item__author_label").shouldHave(Condition.text(expectedResults));
    }
}
