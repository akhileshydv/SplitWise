package entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class Expense extends BaseEntity<Long>{
    String paidBy;
    double amount;
    MetaData metaData;
    List<Split> splits;
    public abstract boolean validate();
    public abstract void enrichSplit();
}
