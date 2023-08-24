package Main;

import Frame.LoginFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Init {

    public Init() {
        File file = new File("src/data/remember.txt");
        String line = null;
        try {
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            reader.close();
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
