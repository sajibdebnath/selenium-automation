package testdata;

import com.github.javafaker.Faker;

public class Customer {
    public static final String FNAME = new Faker().name().firstName();
    public static final String LNAME = new Faker().name().lastName();
    public static final String PHONE = new Faker().number().digits(10);
    public static final String EMAIL = new Faker().internet().emailAddress();
    public static final String PASSWORD = "Pass@12345";
    public static final String TYPE = "Customer";

    public static final String COUNTRY = "Bangladesh";
    public static final String NATIONALITY = "Bangladesh";
    public static final String ADDRESS = "House# 35, Road# 1, Mirpur-11";

    public static final String TITLE_1 = "MRS";
    public static final String TRAVELLER1_FNAME = new Faker().name().firstName();
    public static final String TRAVELLER1_LNAME = new Faker().name().lastName();

    public static final String TITLE_2 = "MISS";
    public static final String TRAVELLER2_FNAME = new Faker().name().firstName();
    public static final String TRAVELLER2_LNAME = new Faker().name().lastName();
}
