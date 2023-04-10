package by.herhenson.program.tables;

public class Hirer implements Table {
    private int id;
    private String fullname;
    private String passport_data;
    private String contact_data;

    public Hirer() {
    }

    public Hirer(int id, String fullname, String passport_data, String contact_data) {
        this.id = id;
        this.fullname = fullname;
        this.passport_data = passport_data;
        this.contact_data = contact_data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassport_data() {
        return passport_data;
    }

    public void setPassport_data(String passport_data) {
        this.passport_data = passport_data;
    }

    public String getContact_data() {
        return contact_data;
    }

    public void setContact_data(String contact_data) {
        this.contact_data = contact_data;
    }

    @Override
    public String toString() {
        return "HIRER: " +
                " " + fullname +
                " " + passport_data +
                " " + contact_data;
    }
}
