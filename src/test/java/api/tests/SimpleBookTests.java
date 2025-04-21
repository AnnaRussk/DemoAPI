package api.tests;

import api.models.BookResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SimpleBookTests extends BookStoreTestBase {

    @Test
    @DisplayName("Проверка книги с использованием маппера")
    void getFirstBook() {
        BookResponse response = given()
                .when()
                .get("/Books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(BookResponse.class);

        assertFalse(response.books.isEmpty());
        assertEquals("Git Pocket Guide", response.books.get(0).title);
    }

    @Test
    @DisplayName("Получение списка всех книг")
    void getAllBooks() {
        given()
                .when()
                .get("/Books")
                .then()
                .statusCode(200)
                .body("books", not(empty()));
    }
}
