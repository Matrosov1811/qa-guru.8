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

    @ValueSource(strings = {"qa guru"})
    @ParameterizedTest(name = "Поиск в ютубе канала {0}")
    void youTubeTest1 (String testData) {
        Selenide.open("https://www.youtube.com/");
        Selenide.$("input#search").setValue(testData);
        Selenide.$("#search-icon-legacy").click();
        Selenide.$$("div#info.ytd-channel-renderer").first().shouldHave(Condition.text(testData));
    }

    @CsvSource(value = {"qa guru, Подписаться" ,
    "Тучс, Подписаться"})
    @ParameterizedTest(name = "Поиск в ютубе канала {0} и проверка наличия кнопки {1}")
    void youTubeTest2 (String testData, String expectedResult) {
        Selenide.open("https://www.youtube.com/");
        Selenide.$("input#search").setValue(testData);
        Selenide.$("#search-icon-legacy").click();
        Selenide.$$("div#subscribe-button.ytd-channel-renderer").first().shouldHave(Condition.text(expectedResult));
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("qa guru", "Подписаться"),
                Arguments.of("Тучс", "Подписаться")
        );
    }

    @MethodSource("testData")
    @ParameterizedTest(name = "Поиск в ютубе канала {0} и проверка наличия кнопки {1}")
    void youTubeTest3 (String testData, String expectedResult) {
        Selenide.open("https://www.youtube.com/");
        Selenide.$("input#search").setValue(testData);
        Selenide.$("#search-icon-legacy").click();
        Selenide.$$("div#subscribe-button.ytd-channel-renderer").first().shouldHave(Condition.text(expectedResult));
    }

}
