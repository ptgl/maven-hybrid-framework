package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerConfig {
    Faker faker;
    String data;

    public FakerConfig(){
        faker = new Faker(Locale.ENGLISH);
    }

    public static FakerConfig getFaker(){
        return new FakerConfig();
    }

    public String getFirstName(){
        data = faker.name().firstName();
        printData(data);
        return data;
    }

    public String getLastName(){
        data = faker.name().lastName();
        printData(data);
        return data;
    }

    public String getMidleName(){
        data = faker.pokemon().name();
        printData(data);
        return data;
    }

    public String getEmail(){
        data = faker.internet().emailAddress();
        printData(data);
        return data;
    }

    public String getPassword(){
        data = faker.internet().password(8, 16,true,true);
        printData(data);
        return data;
    }

    public String getStreet(){
        data = faker.address().streetAddress();
        printData(data);
        return data;
    }

    public String getState(){
        data = faker.address().state();
        printData(data);
        return data;
    }

    public String getCity(){
        data = faker.address().city();
        printData(data);
        return data;
    }

    public String getZip(){
        data = faker.address().zipCode();
        printData(data);
        return data;
    }

    public String getPhone(String prefix){
        data = faker.numerify(prefix+"#######");
        printData(data);
        return data;
    }


    private void printData(String data){
        System.out.println(data);
    }
}
