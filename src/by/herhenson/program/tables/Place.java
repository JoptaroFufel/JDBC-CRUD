package by.herhenson.program.tables;

public class Place implements Table {
    private String address;

    public Place() {
    }

    public Place(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ADDRESS: " + address;
    }
}
