package entites;

import java.util.List;

public class EqualExpense extends Expense{
    public EqualExpense(String paidBy, double amount, MetaData metaData, List<Split> splits) {
        super(paidBy, amount, metaData, splits);
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void enrichSplit() {
        double individualSplit = amount / splits.size();
        splits.forEach(split -> {
            if(split.userId.equals(paidBy)){
                split.setAmount(amount - individualSplit*(splits.size()-1));
            }
            else{
                split.setAmount(individualSplit);
            }
        });
    }
}
