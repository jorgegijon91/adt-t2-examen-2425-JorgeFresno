import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
    private List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>();
    }

    // Agrega un libro a la lista (no persiste en archivo)
    public void addBook(Book book) {
        // TODO
        books.add(book);
    }


    public List<Book> getBooks() {
        return books;
    }

    // Guardar el informe en un archivo de texto con el nombre del autor
    public void printReportFromAuthor(String author) {
        // La siguiente instrucción
        String fileName = author.replaceAll(" ", "_") + "_report.txt";
        // TODO
        List<Book> booksByAuthor = books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author)) // Filtra libros por autor
                .collect(Collectors.toList()); // Crea una nueva lista con los libros filtrados

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            //Foreach para imprimir los libros
            for (Book book : booksByAuthor) {
                writer.println(book);
            }
            System.out.println("Infome guardado en: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al guardar el informe: " + e.getMessage());
        }
    }

    // Guarda los libros en formato json
    public void saveBooksToJsonFile(String filePath) {
        // TODO
        //ObjetMapper de jackson
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //Escribe los libros en el archivo
            objectMapper.writeValue(new File(filePath), books);
            System.out.println("Libros fueron guardados en: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al guardar libros: " + e.getMessage());
        }
    }

    // Carga los libros desde un archivo json
    public void loadBooksFromJsonFile(String filePath) {
        // TODO
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //Lee los libros del archivo
            books = objectMapper.readValue(new File(filePath), new TypeReference<List<Book>>() {
            });
            System.out.println("Libros fueron cargados desde: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al cargar libros: " + e.getMessage());
        }
    }

    // Método para guardar los libros en un archivo binario
    public void saveBooksToBinaryFile(String filePath) {
        // TODO
        //ObjetOutputStream para escribir en binario
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(books);
            System.out.println("Libros guardados en binario: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al guardar en binario: " + e.getMessage());
        }
    }

    // Método para cargar los libros desde un archivo binario
    public void loadBooksFromBinaryFile(String filePath) {
        // TODO
        //ObjetInputStream para leer en binario
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            books = (List<Book>) ois.readObject();
            System.out.println("Libros cargados desde binario: " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar desde binario: " + e.getMessage());
        }
    }
}