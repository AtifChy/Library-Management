import Frame.LoginFrame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        new LoginFrame();
        /*
        LocalDateTime rawDateTime = LocalDateTime.now();
        System.out.println(rawDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
        String formattedDateTime = rawDateTime.format(formatter);
        System.out.println(formattedDateTime);
        */
    }
}