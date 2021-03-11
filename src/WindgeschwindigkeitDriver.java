import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WindgeschwindigkeitDriver {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        WindgeschwindigkeitDriver wgd = new WindgeschwindigkeitDriver();

        List<Windgeschwindigkeit> windSpeedList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        Path path = Paths.get("output");
        if (Files.notExists(path)) {
            var p = Files.createDirectories(path);
        }

        System.out.println("Wie viele Geschwindigkeiten wollen Sie erfassen: ");
        int anzahl = scanner.nextInt();
        scanner.nextLine(); //muss IMMEr nach einem nextInt() etc gemacht werden. sonst kann es zu komische nebenwirkungen kommen.

        for (int i = 0; i < anzahl; i++) {
            System.out.println("Bitte die Windgeschwindigkeit eingeben: ");
            double speed = scanner.nextDouble();
            scanner.nextLine();
            windSpeedList.add(new Windgeschwindigkeit(i, localDateTime, speed));
        }
        for (int j = 0; j < anzahl; j++) {

            String datei = windSpeedList.get(j).toString();
            System.out.println(windSpeedList.get(j));
            wgd.serializeWindgeschwindigkeiten(windSpeedList.get(j));
        }
    }


    public void serializeWindgeschwindigkeiten(Windgeschwindigkeit windspeedList) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("output\\windspeeds1.txt"))) {

            oos.writeObject(windspeedList);
            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private double readSpeed() {
        String d = scanner.nextLine();
        return Double.parseDouble(d);
    }

}
