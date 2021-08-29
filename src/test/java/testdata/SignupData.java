package testdata;

import com.github.javafaker.Faker;

public class SignupData {
    public static final String FIRST_NAME = new Faker().name().firstName();
    public static final String LAST_NAME = new Faker().name().lastName();
    public static final String PHONE_NUMBER = new Faker().number().digits(10);
    public static final String EMAIL = new Faker().internet().emailAddress();
    public static final String PASSWORD = "Pass@12345";
    public static final String ACCOUNT_TYPE = "Customer";

    public static final String COUNTRY = "Bangladesh";
    public static final String NATIONALITY = "Bangladesh";
    public static final String ADDRESS = "House# 35, Road# 1, Mirpur-11";

    public static final String TITLE_1 = "MRS";
    public static final String TRAVELLER_1_FNAME = new Faker().name().firstName();
    public static final String TRAVELLER_1_LNAME = new Faker().name().firstName();

    public static final String TITLE_2 = "MISS";
    public static final String TRAVELLER_2_FNAME = new Faker().name().firstName();
    public static final String TRAVELLER_2_LNAME = new Faker().name().firstName();
}
