import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

//Implementar Serializable para darle permiso a Java para que lo almacene en binario
public class Book implements Serializable {
    @JsonProperty ("titulo")
    private String title;
    @JsonProperty ("autor")
    private String author;
    @JsonProperty ("anio")
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    //Constructor vacío
    public Book() {}

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', year=" + year + "}";
    }
}




