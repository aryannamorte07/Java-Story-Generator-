import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

  public static ArrayList<String> toStringList(String filename) {

    ArrayList<String> list = new ArrayList<String>();

    try {

      File file = new File(filename);

      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {

        list.add(scanner.nextLine());

      }

      scanner.close();

    } catch (IOException e) {

      System.out.println("Error reading file: " + filename);

    }

    return list;
  }

}
