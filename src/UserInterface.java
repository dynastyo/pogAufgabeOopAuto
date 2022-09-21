import java.util.Scanner;

public class UserInterface {
    static Database data = new Database();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        data.dummyDaten();
        menuMain();
    }

    public static void menuMain() {
        System.out.println("1 - Add car to carpark");
        System.out.println("2 - List all cars");
        System.out.println("3 - Search for a car");
        System.out.println("4 - Delete a car");
        System.out.println("5 - Sort cars by brand");
        System.out.println("Your choice?");
        String auswahl = sc.next();
        switch (auswahl) {
            case "1" -> menuAddCar();
            case "2" -> menuPrintCarpark();
            case "3" -> menuSearchCar();
            case "4" -> menuDeleteCar();
            case "5" -> menuSortCarpark();
            default -> {
                System.out.println("Wrong input, try again!");
                menuMain();
            }
        }
    }

    public static void menuAddCar() {
        if (!data.carparkFull()) {
            System.out.println("Enter ID:");
            String id = sc.next();
            if (!data.idFree(id)) {
                System.out.println("ID already taken.");
                menuAddCar();
            }
            System.out.println("Enter brand:");
            String brand = sc.next();
            System.out.println("Enter model:");
            String model = sc.next();
            boolean bError = false;
            do{
                try {
                    System.out.println("Enter value:");
                    double value = sc.nextDouble();
                    bError = false;
                } catch (Exception e) {
                    System.out.println("Value needs to be a number");
                    sc.nextLine(); // stupid line to fix the scanner bug
                    bError = true;
                }
            } while (bError);


            Auto newCar = new Auto(id, brand);
            data.addCar(newCar);
            backToMenu("Car added.");
        } else {
            backToMenu("Carpark is Full!");
        }
    }

    public static void menuPrintCarpark() {
        Auto[] usedCars = data.returnUsedCars();
        for (Auto usedCar : usedCars) {
            System.out.println(usedCar.toString());
        }
        backToMenu("Those are all " +usedCars.length + " Cars.");
    }

    public static void menuSearchCar() {
        System.out.println("Suchbegriff Eingeben");
        String suchbegriff = UserInterface.sc.next().toLowerCase();
        data.sucheAuto(suchbegriff);
        backToMenu("Found your Car?");
    }

    public static void menuDeleteCar() {
        System.out.println("Enter ID of car to delete!");
        String idToDelete = UserInterface.sc.next().toLowerCase();
        if (data.deleteCar(idToDelete)) backToMenu("The car with ID \""+ idToDelete+"\" got deleted!");
        else backToMenu("ID not found, could not delete the car!");
    }

    public static void menuSortCarpark() {
        data.carparkSort();
        backToMenu("Cars sorted by brand!");
    }

    public static void backToMenu(String info) {
        System.out.println("-------------\n" + info + "\n- - - - - - -\nGoing back to Main Menu\n-------------");
        menuMain();
    }
}