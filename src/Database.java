public class Database {
    // comment
    private final Auto[] carpark;

    public Database() {
        carpark = new Auto[50];
    }

    // Rückgabe aller Datensätze
    public void ausgabeAutoliste() {

        for (int i = 0; i < 50; i++) {
            if (carpark[i] != null) {
                System.out.println("------------");
                System.out.println("ID " + carpark[i].getId());
                System.out.println("Brand " + carpark[i].getBrand());
            }
        }
        System.out.println("------------");
        System.out.println("Ausgabe Beendet.");
        System.out.println("Zurück zum Hauptmenü? J/N");
        String auswahl = Main.sc.next().toUpperCase();
        if (auswahl.equals("J")) {
            Main.mainMenu();
        }
    }
public void datensatzLoeschen(){}

    // Hinzufügen eines Autos
    public void hinzufuegenAuto(Auto a) {
        for (int i = 0; i < 50; i++) {
            if (carpark[i] == null) {
                carpark[i] = a;
                break;
            }

        }
    }
    public boolean idFree(String id) {
        for (int i = 0; i < 50; i++) {
            if (carpark[i] != null) {
            if (carpark[i].getId().equals(id)) {
                return false;
            }}
        }
        return true;
    }

    public void sucheAuto() {
        System.out.println("suchbegirff sadasdfasd");
        String suchbegriff = Main.sc.next();
        for (int i = 0; i < 50; i++) {
            if (carpark[i] != null) {
                if (suchbegriff.equals(carpark[i].getBrand()) || suchbegriff.equals(carpark[i].getId())) {
                    System.out.println("------------");
                    System.out.println("ID " + carpark[i].getId());
                    System.out.println("Brand " + carpark[i].getBrand());
                }
            }
        }
        System.out.println("------------");
        System.out.println("Ausgabe Beendet.");
        System.out.println("Zurück zum Hauptmenü? J/N");
        String auswahl = Main.sc.next().toUpperCase();
        if (auswahl.equals("J")) {
            Main.mainMenu();
        }
    }

    public void dummyDaten(){
        carpark[0] = new Auto("1", "BMW");
        carpark[1] = new Auto("2", "Audi");
        carpark[2] = new Auto("3", "BMW");
        carpark[3] = new Auto("4", "VW");
        carpark[4] = new Auto("5", "Opel");
    }
}


