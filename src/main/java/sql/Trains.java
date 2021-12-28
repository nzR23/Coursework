package sql;

public class Trains {
    private String Name;
    private int price;
    private int numOfSeats;
    private int numOfBaggage;

    public Trains(String name, int Price, int NumSeats, int numBaggage) {
        Name = name;
        price = Price;
        numOfSeats = NumSeats;
        numOfBaggage = numBaggage;
    }

    public String getPriceString(){
        return Integer.toString(price);
    }
    public String getSeatsString(){
        return Integer.toString(numOfSeats);
    }
    public String getBaggageString(){
        return Integer.toString(numOfBaggage);
    }

    public String getName() {
        return Name;
    }
}
