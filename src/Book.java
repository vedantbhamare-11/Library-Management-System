import java.util.ArrayList;

public class Book {
    private int id;
    private String title;
    private String author;
    public Book(int id, String title, String author, int quantity, int subjectId) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Book findById(ArrayList<Book> books, int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null; // ID not found
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
