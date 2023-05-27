import java.io.IOException;
import java.util.List;

/**
 * @author dariush
 */
public class CSVHandlerTest {

    /**
     * Testklasse
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        CSVHandler csv = new CSVHandler();

        csv.printAllStudentsFromCsvStructured();

        List<Schueler> schuelerList = csv.getAll();

        csv.printAll(schuelerList);

        csv.schreibeAlleSchuelerInCSVDatei(schuelerList);

    }
}
