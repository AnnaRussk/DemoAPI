package api.tests;

import api.models.BooksResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SimpleBooksTests extends BooksStoreTestBase {

    @Test
    @DisplayName("Проверка книги с использованием маппера")
    void getFirstBook() {
        BooksResponse response = given()
                .when()
                .get("/Books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(BooksResponse.class);

        assertFalse(response.books.isEmpty());
        assertEquals("Git Pocket Guide", response.books.get(0).getTitle());
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
