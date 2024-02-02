import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedDecrypted {

    private final CaesarCipher cesarCipher = new CaesarCipher();

    public void encryptedDecrypted(boolean flag) throws IOException {

        Util.writeMessage("Введите путь к файлу для его " + (flag ? "зашифровки" : "расшифровки"));
        String src = Util.readString();

        System.out.println("Введите ключ:");
        int key = Util.readInt();

        Path dst = Util.buildFileName(src, flag ? "_encrypted" : "_decrypted");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(src));
             BufferedWriter writer = Files.newBufferedWriter(dst)) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encryptedDecrypted = flag ? cesarCipher.encrypt(string, key) : cesarCipher.decrypt(string, key);
                writer.write(encryptedDecrypted + System.lineSeparator());
            }
        }
        Util.writeMessage("Содержимое файла " + dst.getFileName() + (flag ? " зашифровано" : " расшифровано") +
                          System.lineSeparator());
    }
}
