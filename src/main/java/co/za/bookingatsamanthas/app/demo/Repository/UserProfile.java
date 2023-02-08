package co.za.bookingatsamanthas.app.demo.Repository;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;



@Getter @Setter
public class UserProfile {
    @Id
    private ObjectId id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String idNumber;

    private String photoUrl;

    public UserProfile(String name, String surname, String email, String phoneNumber, String idNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

}
