package pav.matrosov;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class MyParameterizedTest {

    @ValueSource(strings = {"qa guru", "LABELCOM"})
    @ParameterizedTest(name = "Поиск в ютубе канала {0}")
    void youTubeValueSourceTest (String testData) {
        Selenide.open("https://www.youtube.com/");
        Selenide.$("input#search").setValue(testData);
        Selenide.$("#search-icon-legacy").click();
        Selenide.$("#info.ytd-channel-renderer").shouldHave(Condition.text(testData));
    }

    @CsvSource(value = {"qa guru, Подписаться" ,
    "LABELCOM, Подписаться"})
    @ParameterizedTest(name = "Поиск в ютубе канала {0} и проверка наличия кнопки {1}")
    void youTubeCsvSourceTest (String testData, String expectedResult) {
        Selenide.open("https://www.youtube.com/");
        Selenide.$("input#search").setValue(testData);
        Selenide.$("#search-icon-legacy").click();
        Selenide.$("#info.ytd-channel-renderer").shouldHave(Condition.text(expectedResult));
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("qa guru", "Подписаться"),
                Arguments.of("LABELCOM", "Подписаться")
        );
    }

    @MethodSource("testData")
    @ParameterizedTest(name = "Поиск в ютубе канала {0} и проверка наличия кнопки {1}")
    void youTubeMethodSourceTest (String testData, String expectedResult) {
        Selenide.open("https://www.youtube.com/");
        Selenide.$("input#search").setValue(testData);
        Selenide.$("#search-icon-legacy").click();
        Selenide.$("#info.ytd-channel-renderer").shouldHave(Condition.text(expectedResult));
    }

}
