import java.util.Scanner;
// githubtest2
public class UserInterface {
    static  Database data = new Database();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        data.dummyDaten();
        menuMain();
    }

    public static void menuMain(){
        System.out.println("1 - Neues Fahrzeug hinzufuegen");
        System.out.println("2 - alle Fahrzeuge ausgeben");
        System.out.println("3 - suche nach Datensatz");
        System.out.println("4 - Lösche einen Datensatz");
        System.out.println("Ihre Auswahl?");
        String auswahl = sc.next();
        switch (auswahl) {
            case "1" -> menuAddCar();
            case "2" -> menuAusgabeAutoliste();
            case "3" -> menuSearchCar();
            case "4" -> menuDeleteCar();
            default -> {
                System.out.println("Falsche Eingabe, neuer Versuch!");
                menuMain();
            }
        }
    }
    public static void menuAddCar(){
            System.out.println("id eingeben");
             String id = sc.next();
             if (!data.idFree(id)){
                 System.out.println("ID schon vergeben");
                 menuAddCar();
             }
        System.out.println("hersteller eingeben");
        String brand = sc.next();
        Auto newCar = new Auto(id, brand);
        data.hinzufuegenAuto(newCar);
        menuMain();
    }
    public static void menuAusgabeAutoliste(){
        Auto[] usedCars = data.rueckgabeAutoliste();
        for (int i = 0; i < usedCars.length; i++) {
            System.out.println("------------");
            System.out.println("ID " + usedCars[i].getId());
            System.out.println("Brand " + usedCars[i].getBrand());
        }
    }
    public static void menuSearchCar(){
        System.out.println("Suchbegriff Eingeben");
        String suchbegriff = UserInterface.sc.next().toLowerCase();
        data.sucheAuto(suchbegriff);
        UserInterface.ausgabeBeendet();
    }

    public static void menuDeleteCar(){
        System.out.println("Welches Auto möchtest du löschen? ID Eingeben!");
        String idToDelete = UserInterface.sc.next().toLowerCase();
        data.deleteCar(idToDelete);
        UserInterface.ausgabeBeendet();
    }

    public static void ausgabeBeendet(){
        System.out.println("------------");
        System.out.println("Ausgabe Beendet.");
        System.out.println("------------");
        menuMain();

    }
}

