package entites;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PercentSplit extends Split{
    double percent;
    public PercentSplit(String userId, double percent) {
        super(userId);
        this.percent = percent;
    }
}
