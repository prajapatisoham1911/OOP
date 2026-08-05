import java.util.Scanner;

record Vehicle(String number, String type) {}

public class TollBooth {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int totalToll = 0;
        int bike = 0, car = 0, truck = 0;

        while (true) {

            System.out.print("Enter vehicle number (or done): ");
            String number = sc.next();

            if (number.equalsIgnoreCase("done"))
                break;

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.next().toLowerCase();

            Vehicle v = new Vehicle(number, type);

            int toll = switch (v.type()) {
                case "bike" -> {
                    bike++;
                    yield 20;
                }
                case "car" -> {
                    car++;
                    yield 50;
                }
                case "truck" -> {
                    truck++;
                    yield 150;
                }
                default -> {
                    System.out.println("Invalid type");
                    yield 0;
                }
            };

            totalToll += toll;
        }

        System.out.println("\nTotal toll: " + totalToll);

        if (bike >= car && bike >= truck)
            System.out.println("Most frequent: bike");
        else if (car >= bike && car >= truck)
            System.out.println("Most frequent: car");
        else
            System.out.println("Most frequent: truck");

        sc.close();
    }
}
