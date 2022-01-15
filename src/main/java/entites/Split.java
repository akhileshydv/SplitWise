package entites;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Split {
    String userId;
    double amount;
    public Split(String userId){
        this.userId = userId;
    }

}
