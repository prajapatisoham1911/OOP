class CinemaShow {

    private String title;
    private int seatsAvailable;
    private final int capacity;

    private static int totalBooked = 0;

    // Constructor
    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    // Constructor chaining
    public CinemaShow(String title) {
        this(title, 100);
    }

    // Book seats
    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    // Cancel booking
    public void cancel(int n) {
        seatsAvailable += n;

        if (seatsAvailable > capacity) {
            seatsAvailable = capacity;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {

        CinemaShow show = new CinemaShow("Avengers", 50);

        System.out.println(show.book(20));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println(show.book(15));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println(show.book(30));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        show.cancel(10);
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println(show.book(25));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Total Booked = " + CinemaShow.getTotalBooked());
    }
}
