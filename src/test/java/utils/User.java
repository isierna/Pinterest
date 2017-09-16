package utils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public abstract class User {
    public static String userEmail = generateRandomString() + "@gmail.com";


    public static String generateEmail() {
        String firstPartOfTheEmail = "trys2015";
        Integer randomIndex = new Random().nextInt(firstPartOfTheEmail.length());
        if (randomIndex == 0) {
            randomIndex = randomIndex + 1;
        }
        String subString = firstPartOfTheEmail.substring(randomIndex);
        String finalRegString = firstPartOfTheEmail.replace(subString,"") + "." + subString;
        return finalRegString;
    }

    public static String generateRandomString() {
        RandomStringUtils randomObject = new RandomStringUtils();
        return randomObject.randomAlphanumeric(7);
    }

}
