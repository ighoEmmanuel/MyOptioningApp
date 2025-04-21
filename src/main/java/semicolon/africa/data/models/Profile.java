package semicolon.africa.data.models;


import lombok.Data;


@Data
public class Profile {
    private String email;
    private Address address;
    private AccountStatus accountStatus;
}
