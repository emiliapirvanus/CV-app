package cvapp.model;

import cvapp.exception.InvalidFormatException;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {

    private String phoneNumber;

    private String emailAddress;

    private Address address;

    public static boolean isValidNumber(String phoneNumber) {

        return phoneNumber.matches("\\d{10}");
    }

    public static boolean isValidEmailAddress(String emailAddress) {

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return emailAddress.matches(regex);
    }
}
