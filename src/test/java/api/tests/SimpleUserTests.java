package api.tests;

import api.models.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class SimpleUserTests extends UserTestBase {

    @Test
    @DisplayName("Регистрация пользователя + генерация токена")
    public void createUserAndGenerateTokenTest() {
        Faker faker = new Faker();

        User user = new User();
        user.setUserName(faker.name().username());
        user.setPassword("Test@" + faker.number().digits(5));

        String contentType = "application/json";

        //Шаг 1 - Создание юзера
        given()
                .contentType(contentType)
                .body(user)
        .when()
                .post("/User")
        .then()
                .statusCode(201);

        //Шаг 2 - Генерация токена и сохранение токена
        String token =
        given()
            .contentType(contentType)
            .body(user)
        .when()
             .post("/GenerateToken")
        .then()
             .statusCode(200)
             .body("token", notNullValue())
             .extract()
             .path("token");


        //Шаг 3 - авторизация? с токеном
        given()
                .contentType(contentType)
                .header("Authorization", "Bearer " + token)
                .body(user)
        .when()
                .post("/Authorized")
        .then()
                .statusCode(200)
                .body(equalTo("true"));

        //Вопрос - тут варианта 2 - хардкодить логин-пароль и разделять тесты или оставлять в одном?
    }
}
