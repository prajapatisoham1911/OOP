class ParkingLot {

    private int twoWheelers;
    private int fourWheelers;

    private final int twoCap;
    private final int fourCap;

    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }

    // Park vehicle
    public void park(String type) {

        if (type.equalsIgnoreCase("two")) {

            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two-wheeler parked.");
            } else {
                System.out.println("Full");
            }

        } else if (type.equalsIgnoreCase("four")) {

            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four-wheeler parked.");
            } else {
                System.out.println("Full");
            }

        } else {
            System.out.println("Invalid vehicle type.");
        }
    }

    // Leave parking
    public void leave(String type) {

        if (type.equalsIgnoreCase("two")) {

            if (twoWheelers > 0)
                twoWheelers--;

        } else if (type.equalsIgnoreCase("four")) {

            if (fourWheelers > 0)
                fourWheelers--;
        }
    }

    public static long getRevenue() {
        return revenue;
    }

    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot(2, 2);

        lot.park("two");
        lot.park("two");
        lot.park("two");

        lot.park("four");
        lot.park("four");
        lot.park("four");

        lot.leave("two");
        lot.leave("four");

        lot.park("two");
        lot.park("four");

        System.out.println("\nFinal Occupancy:");
        System.out.println("Two-wheelers: " + lot.twoWheelers);
        System.out.println("Four-wheelers: " + lot.fourWheelers);

        System.out.println("Revenue = " + ParkingLot.getRevenue());
    }
}
