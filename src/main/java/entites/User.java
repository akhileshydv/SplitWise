package entites;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class User extends BaseEntity<String>{
    String name;
    String email;
    Long mobileNo;
    HashMap<String, Double> balanceSheet;
    public User(String id, String name, String email, Long mobileNo){
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        balanceSheet = new HashMap<>();
    }
}
