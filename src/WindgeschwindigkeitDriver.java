import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WindgeschwindigkeitDriver {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Windgeschwindigkeit> windSpeedList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
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
            System.out.println(windSpeedList.get(j));
        }
    }

    private double readSpeed() {
        String d = scanner.nextLine();
        return Double.parseDouble(d);
    }


}
