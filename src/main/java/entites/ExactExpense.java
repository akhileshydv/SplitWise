package entites;

import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(String paidBy, double amount, MetaData metaData, List<Split> splits) {
        super(paidBy, amount, metaData, splits);
    }

    @Override
    public boolean validate() {
        double sum = 0.0;
        for(Split split: splits){
            sum+=split.getAmount();
        }
        return sum == amount;
    }

    @Override
    public void enrichSplit() {

    }
}
