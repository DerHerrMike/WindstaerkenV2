
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class WindDaten {

    protected LocalDateTime localDateTime = LocalDateTime.now();
    protected ArrayList<Windgeschwindigkeit> windSpeedValuesList = new ArrayList<>();


    // Konstruktor
    public WindDaten(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    // Methode
    public String getZeitpunkt() {
        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return localDateTime.format(format);
    }
}


