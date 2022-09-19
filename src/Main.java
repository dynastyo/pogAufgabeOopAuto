import java.util.Scanner;
// main
public class Main {
    static  Database data = new Database();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        data.dummyDaten();
        mainMenu();

    }

    public static void mainMenu(){
        System.out.println("1 - Neues Fahrzeug hinzufuegen");
        System.out.println("2 - alle Fahrzeuge ausgeben");
        System.out.println("3 - suche nach Datensatz");
        System.out.println("Ihre Auswahl?");
        String auswahl = sc.next();
        if(auswahl.equals("1")){
            fahrzeugHinzufuegenUntermenu();

        }
        if(auswahl.equals("2")){
            data.ausgabeAutoliste();
        }
        if(auswahl.equals("3")){
            data.sucheAuto();
        }
        else {
            System.out.println("Falsche Eingabe, neuer Versuch!");
            mainMenu();
        }

    }
    public static void fahrzeugHinzufuegenUntermenu(){
            System.out.println("id eingeben");
             String id = sc.next();
             if (!data.idFree(id)){
                 System.out.println("ID schon vergeben");
                 fahrzeugHinzufuegenUntermenu();
             }
        System.out.println("hersteller eingeben");
        String brand = sc.next();
        Auto a = new Auto(id, brand);
        data.hinzufuegenAuto(a);
        mainMenu();
    }
}

