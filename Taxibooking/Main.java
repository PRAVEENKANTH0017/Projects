package taxibooking;

import java.util.*;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("Choose any one: \n1. Book Taxi \n2. Booked Details \n3. Exit");
            int n = sc.nextInt();

            switch (n) {
                case 1: {
                    System.out.println("Enter pickup location (A-F): ");
                    char pickup = sc.next().charAt(0);

                    System.out.println("Enter drop location (A-F): ");
                    char drop = sc.next().charAt(0);

                    System.out.println("Enter pickup time (in hours): ");
                    int picktime = sc.nextInt();

                    System.out.println(taxibooking.booking(pickup, drop, picktime));
                    break;
                }

                case 2: {
                    taxibooking.display();
                    break;
                }

                case 3: {
                    loop = false;
                    System.out.println("Thank you for using the Taxi Booking System.");
                    break;
                }

                default: {
                    System.out.println("Invalid option. Try again.");
                    break;
                }
            }
        }

        sc.close();
    }
}