package fptaptech.com.WIWebApp.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String password;
    private Long categoryUser;

    public User() {
    }

}
