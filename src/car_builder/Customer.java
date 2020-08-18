package car_builder;

public class Customer
{
    private int accountNumber;
    private String firstName;
    private String lastName;

    private String phone;

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;

    /**
     * No-arg constructor
     * This constructor initializes all fields with the value "Unknown".
     */
    public Customer() {
        accountNumber = (int)(Math.random()*1e10);
        firstName = "Unknown";
        lastName = "Unknown";
        phone = "Unknown";
        address1 = "Unknown";
        address2 = "Unknown";
        city = "Unknown";
        state = "Unknown";
        zip = "Unknown";
    }

    /**
     * This constructor accepts parameters for, and initializes, all fields.
     * @param firstName
     * @param lastName
     * @param phone
     * @param address1
     * @param address2
     * @param city
     * @param state
     * @param zip
     */
    public Customer(String firstName, String lastName,
                    String phone, String address1,
                    String address2, String city,
                    String state, String zip) {
        accountNumber = (int)(Math.random()*1e10);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * This constructor accepts parameters for, and initializes, all fields except address 2.
     * @param firstName
     * @param lastName
     * @param phone
     * @param address1
     * @param city
     * @param state
     * @param zip
     */
    public Customer(String firstName, String lastName,
                    String phone, String address1,
                    String city, String state, String zip) {
        accountNumber = (int)(Math.random()*1e10);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * This method returns the account number.
     * @return int account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * This method returns the first name.
     * @return String containing the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method returns the last name.
     * @return String containing the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method returns the phone number.
     * @return String containing the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method returns the first address line.
     * @return String containing the first address line.
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * This method returns the second address line.
     * @return String containing the second address line
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * This method returns the city.
     * @return String containing the city
     */
    public String getCity() {
        return city;
    }

    /**
     * This method returns the state.
     * @return String containing the state
     */
    public String getState() {
        return state;
    }

    /**
     * This method returns the zip code.
     * @return String containing the zip code
     */
    public String getZip() {
        return zip;
    }

    /**
     * This method sets the first name field.
     * @param firstName String containing the first name
     */
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method sets the last name field.
     * @param lastName String containing the last name
     */
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method sets the phone field.
     * @param phone String containing the phone number
     */
    protected void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method sets the first address line field.
     * @param address1 String containing the first address line
     */
    protected void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * This method sets the second address line field.
     * @param address2 String containing the second address line
     */
    protected void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * This method sets the city field.
     * @param city String containing the city
     */
    protected void setCity(String city) {
        this.city = city;
    }

    /**
     * This method sets the state field.
     * @param state String containing the state
     */
    protected void setState(String state) {
        this.state = state;
    }

    /**
     * This method sets the zip field.
     * @param zip String containing zip code
     */
    protected void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * toString method
     * @return String containing information about the object
     */
    @Override
    public String toString() {
        String str;
        if (address2 == null) {
            str = String.format("Customer #:\t%d" +
                            "%nName:\t\t%s %s"+
                            "%nPhone:\t\t%s" +
                            "%nAddress 1:\t%s" +
                            "%nAddress 2:"+
                            "%nCity:\t\t%s" +
                            "%nState:\t\t%s" +
                            "%nZip:\t\t%s", accountNumber, firstName, lastName, phone,
                            address1, city, state, zip);
        } else {
            str = String.format("Customer #:\t%d" +
                            "%nName:\t\t%s %s"+
                            "%nPhone:\t\t%s" +
                            "%nAddress 1:\t%s" +
                            "%nAddress 2:\t%s"+
                            "%nCity:\t\t%s" +
                            "%nState:\t\t%s" +
                            "%nZip:\t\t%s", accountNumber, firstName, lastName, phone,
                            address1, address2, city, state, zip);
        }
        return str;
    }
}
