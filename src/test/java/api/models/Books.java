package api.models;

import lombok.Data;

@Data
public class Books {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private String publisher;
    private int pages;
    private String description;
    private String website;
    private String publish_date;

    public String getTitle() {
        return title;
    }
}
