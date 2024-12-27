package utilities;

import com.github.javafaker.Faker;

public class FakerConfig {
    Faker faker;
    String data;

    public FakerConfig(){
        faker = new Faker();
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

    private void printData(String data){
        System.out.println(data);
    }
}
