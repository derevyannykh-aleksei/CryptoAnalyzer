import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypted {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void encrypted() throws IOException {

        System.out.println("Введите полный путь к файлу, для его зашифровки:");
        String src = scanner.nextLine();

        System.out.println("Введите ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите полный путь к файлу, в который записать зашифрованый текст:");
        String dst = scanner.nextLine();

        try (var reader = Files.newBufferedReader(Paths.get(src));
             var writer = Files.newBufferedWriter(Paths.get(dst))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encryptString = caesarCipher.encrypt(string, key);
                writer.write(encryptString);
                writer.newLine();
            }
        }
        System.out.println("Содержимое файла зашифровано." + System.lineSeparator());
    }
}