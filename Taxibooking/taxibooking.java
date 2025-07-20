package taxibooking;

import java.util.ArrayList;

public class taxibooking {
    private static ArrayList<taxi> taxiList = new ArrayList<>();
    private static ArrayList<taxi> bookedHistory = new ArrayList<>();
    private static final int TAXI_LIST_LIMIT = 4;
    private static int idGenerator = 1;

    public static String booking(char pickup, char drop, int pickTime) throws CloneNotSupportedException {
        if (taxiList.size() < TAXI_LIST_LIMIT) {
            taxiList.add(new taxi()); // assuming Taxi class has a default constructor
        }

        taxi taxiready = null;
        int min = Integer.MAX_VALUE;

        for (taxi t : taxiList) {
            if (t.getDropTime() <= pickTime && Math.abs(pickup - t.getCurrentLocation()) <= min) {
                if (Math.abs(pickup - t.getCurrentLocation()) == min) {
                    if (taxiready != null && t.getEarnings() < taxiready.getEarnings()) {
                        taxiready = t;
                    }
                } else {
                    taxiready = t;
                    min = Math.abs(pickup - t.getCurrentLocation());
                }
            }
        }

        if (taxiready != null) {
            taxiready.setCustomerId(idGenerator++);
            taxiready.setPickupTime(pickTime);
            taxiready.setPickupLocation(pickup);
            taxiready.setDropLocation(drop);
            taxiready.setCurrentLocation(drop);
            int tripDuration = Math.abs(drop - pickup);
            taxiready.setDropTime(pickTime + tripDuration);

            int tripEarning = ((tripDuration * 15 - 5) * 10) + 100;
            taxiready.setEarnings(taxiready.getEarnings() + tripEarning);

            taxiready.setTaxiId(taxiList.indexOf(taxiready) + 1);

            bookedHistory.add((taxi) taxiready.clone()); // add to booked history
        }

        return taxiready != null
                ? "Taxi number " + taxiready.getTaxiId() + " is booked!"
                : "Taxis not available";
    }

    public static void display() {
        System.out.println("_______________________________");
        for (taxi t : bookedHistory) {
            System.out.println(t.toString());
            System.out.println("_______________________________");
        }
    }
}