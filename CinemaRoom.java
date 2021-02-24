import java.util.Scanner;

public class CinemaRoom {
    public static final Scanner scanner = new Scanner(System.in);
    public static int numberOfRows;
    public static int numberOfSeats;
    public static int rowNumber = -1;
    public static int seatNumber = -1;
    public static int priceOfFrontHalf = 10;
    public static int priceOfBackHalf = 8;
    public static int income = 0;
    public static int totalIncome;
    public static int ticketPrice;
    public static int action;
    public static int purchasedTickets = 0;
    public static boolean work = true;

    public static void main(String[] args) {
        // Write your code here
        readNumberOfRowsAndSeats();
        String[][] cinemaRoom = new String[numberOfRows + 1][numberOfSeats + 1];
        initCinemaRoom(cinemaRoom);
        while (work) {
            printChoiseAction();
            choiseAction();
            switch (action) {
                case 0:
                    work = false;
                    break;
                case 1:
                    printNumberOfSeats(cinemaRoom);
                    break;
                case 2:
                    readChoiseOfRowAndSeat(cinemaRoom);
                    cinemaRoom[rowNumber][seatNumber] = "B";
                    calculateTheProfit(rowNumber);
                    System.out.println("Ticket price: $" + ticketPrice);
                    break;
                case 3:
                    printStatistic();
                    break;
            }
        }

    }

    public static void readNumberOfRowsAndSeats() {
        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numberOfSeats = scanner.nextInt();
    }

    public static void readChoiseOfRowAndSeat(String[][] room) {
        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber < 1 || rowNumber > numberOfRows || seatNumber < 0 || seatNumber > numberOfSeats) {
                System.out.println("Wrong input!");
            } else if (room[rowNumber][seatNumber] == "B") {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }

    }


    public static void calculateTheProfit(int rowNumber) {
        int halfOfRoom = numberOfRows / 2;
        if (numberOfSeats * numberOfRows < 60) {
            income = income + priceOfFrontHalf;
            ticketPrice = priceOfFrontHalf;
        } else {
            if (rowNumber > halfOfRoom) {
                income = income + priceOfBackHalf;
                ticketPrice = priceOfBackHalf;
            } else {
                income = income + priceOfFrontHalf;
                ticketPrice = priceOfFrontHalf;
            }
        }
        purchasedTickets++;
    }

    public static void printChoiseAction() {
        System.out.println("");
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

    }
    public static void choiseAction() {
        action = scanner.nextInt();
        System.out.println("");
    }


    public static void initCinemaRoom(String[][] cinemaRoom) {
        for (int i = 0; i <= numberOfRows; i++) {
            for (int j = 0; j <= numberOfSeats; j++) {
                if (i == 0) {
                    if (j == 0) {
                        cinemaRoom[i][j] = " ";
                    } else {
                        cinemaRoom[i][j] = Integer.toString(j);
                    }
                } else {
                    if (j == 0) {
                        cinemaRoom[i][j] = Integer.toString(i);
                    } else {
                        cinemaRoom[i][j] = "S";
                    }
                }
            }
        }

    }

    public static void printNumberOfSeats(String[][] cinemaRoom) {
        System.out.println("Cinema:");
        for (int i = 0; i <= numberOfRows; i++) {
            for (int j = 0; j <= numberOfSeats; j++) {
                System.out.print(cinemaRoom[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void printStatistic() {
        float maxTickets = numberOfRows * numberOfSeats;
        float percentage = purchasedTickets / maxTickets * 100;
        countIncome();

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void countIncome() {
        int onePartIncome = numberOfRows / 2 * numberOfSeats * priceOfFrontHalf;
        int twoPartIncome = (numberOfRows - numberOfRows / 2) * numberOfSeats * priceOfBackHalf;
        totalIncome = onePartIncome + twoPartIncome;
    }

}
