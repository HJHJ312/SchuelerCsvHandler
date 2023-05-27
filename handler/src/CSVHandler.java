import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dariush
 */
public class CSVHandler {

    /**
     * muss sich im aktuellen Ordner befinden!
     */
    private String file;
    private String delimiter;
    private String line = "";

    private String[] zeilenListe = null;
    private List<String> formatedColumes = null;

    /**
     * Default constructor
     */
    public CSVHandler() {
        this(";", "studentNameCSV.csv");
    }

    /**
     * Standard constructor
     *
     * @param delimiter, Trennzeichen
     * @param file,      Datei zum Einlesen
     */
    // Constructor 2
    public CSVHandler(String delimiter, String file) {
        super();
        this.delimiter = delimiter;
        this.file = file;
    }

    public List<String> formatColumns(String[] zeilenListe) {

        List<String> formatedColumms = new ArrayList<String>();

        String column1 = zeilenListe[0] + " " + zeilenListe[1];
        if (column1.equals("Vorname Name")) {
            column1 = "Name";
        }
        formatedColumms.add(column1);

        String column2 = zeilenListe[2];
        if (column2.equals("joker")) {
            column2 = "Joker";
        }
        formatedColumms.add(column2);

        String column3 = zeilenListe[3];
        if (column3.equals("blamiert")) {
            column3 = "Blamiert";
        }
        formatedColumms.add(column3);

        String column4 = zeilenListe[4];
        if (column4.equals("fragen")) {
            column4 = "Fragen";
        }
        formatedColumms.add(column4);

        return formatedColumms;
    }

    public void printAllStudentsFromCsvStructured() throws IOException {
        FileReader fileReader = new FileReader(this.file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String zeile = "";
        while ((zeile = bufferedReader.readLine()) != null) {
            this.zeilenListe = this.csvValueSpilt(zeile);
            String formatZeilen = this.structureCsvSplitValue(List.of(zeilenListe));
            System.out.println(formatZeilen);
        }

        bufferedReader.close();
    }

    public String[] csvValueSpilt(String zeile) {

        return zeile.split(";");
    }

    public String structureCsvSplitValue(List<String> formatColumns) {

        return String.format(
                "%-15s %-15s %-15s %-15s",
                formatColumns.get(0),
                formatColumns.get(1),
                formatColumns.get(2),
                formatColumns.get(3)
        );
    }

    /**
     * Gibt alle Schüler aus
     */
    public void printAll(List<Schueler> schuelerList) {
        for (int i = 0; i < schuelerList.size(); i++) {
            System.out.println("=========================================");
            System.out.printf("Schueler %d%n", i + 1);
            System.out.println("=========================================");
            System.out.printf(
                    "%-10s %-10s%n", "Name:", schuelerList.get(i).getName()
            );
            System.out.printf(
                    "%-10s %-10s%n", "Joker:", schuelerList.get(i).getJoker()
            );
            System.out.printf(
                    "%-10s %-10s%n", "Blamiert:", schuelerList.get(i).getBlamiert()
            );
            System.out.printf(
                    "%-10s %-10s%n", "Fragen:", schuelerList.get(i).getFragen()
            );
        }
    }

    /**
     * Schreibe alle Schüler in Csv Datein
     */
    public void schreibeAlleSchuelerInCSVDatei(List<Schueler> schuelerList) throws IOException {
        FileWriter fileWriter = new FileWriter("newStudentCsv.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Name;Joker;Blamiert;Fragen");
        bufferedWriter.newLine();

        for (Schueler schueler : schuelerList) {
            bufferedWriter.write(
                    schueler.getName() +
                            this.delimiter +
                            schueler.getJoker() +
                            this.delimiter +
                            schueler.getBlamiert() +
                            this.delimiter +
                            schueler.getFragen() +
                            this.delimiter
            );
            bufferedWriter.newLine();

        }
        bufferedWriter.close();
    }


    /**
     * Liest alle Schüler aus der csv aus und gibt sie zurück
     *
     * @return List mit Schülern
     */

    public List<Schueler> getAll() throws IOException {
        FileReader fileReader = new FileReader(this.file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<Schueler> schuelerList = new ArrayList<Schueler>();


        String zeile = "";
        while ((zeile = bufferedReader.readLine()) != null) {

            this.zeilenListe = this.csvValueSpilt(zeile);
            this.formatedColumes = this.formatColumns(this.zeilenListe);
            if (this.formatedColumes.get(0) != "Name") {
                schuelerList.add(
                        new Schueler(
                                this.formatedColumes.get(0),
                                Integer.parseInt(this.formatedColumes.get(1)),
                                Integer.parseInt(this.formatedColumes.get(2)),
                                Integer.parseInt(this.formatedColumes.get(3))
                        )
                );
            }
        }

        bufferedReader.close();

        return schuelerList;
    }
}
