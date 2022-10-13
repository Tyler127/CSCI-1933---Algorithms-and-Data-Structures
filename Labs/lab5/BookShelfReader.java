import java.util.Scanner;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;

public class BookShelfReader {
    public static Bookshelf readBooksFromFile(String fileName){
        Bookshelf bookShelfEins = new Bookshelf(20);
        //String cutName = fileName.substring(0, fileName.length() - 4);
        //System.out.println(cutName);
        Scanner s = null;
        try{
            s = new Scanner(new File(fileName));
            while (s.hasNextLine()){
                String line = s.nextLine();//doesn't need to split on \n, already only reads 1 line
                System.out.println(line);
                String[] splitted = line.split(", ");
                String title = splitted[0];
                String author = splitted[1];
                System.out.println(splitted[2]);
                int rating = 0;//Integer.parseInt(splitted[2]);
                Book currBook = new Book(title, author, rating);
                System.out.println(currBook.toString());
                bookShelfEins.add(currBook);
            }
            return bookShelfEins;
            
        } catch (Exception e){
            System.out.println("Exception Found");
            return null;
        }

    }

    public static void main(String[] args){
        Bookshelf x = readBooksFromFile("bookinput.txt");
        //System.out.println(x.toString());
    }

}
