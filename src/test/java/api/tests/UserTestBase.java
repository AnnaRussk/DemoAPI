package api.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserTestBase {

    static Properties config = new Properties();

    @BeforeAll
    public static void setUp() throws IOException {
        config.load(new FileInputStream("src/test/java/api/resources/config.properties"));

        //тут для примера с исключением
        String baseUri = config.getProperty("base.uri");
        if(baseUri == null) throw new RuntimeException("Что-то не так с base.uri файла пропертиз");
        RestAssured.baseURI = baseUri;

        //тут как было - вопрос как лучше?
        RestAssured.basePath = config.getProperty("base.path");
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .build();
    }
}
