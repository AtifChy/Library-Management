import Frame.LoginFrame;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/data/remember.txt");
        String line = null;
        try {
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!file.exists() || line == null) {
            new LoginFrame();
        } else {
            String[] data;
            data = line.split(",");
            new LoginFrame(data[0], data[1], data[2]);
        }
    }
}