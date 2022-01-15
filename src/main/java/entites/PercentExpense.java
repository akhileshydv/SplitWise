package entites;

import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(String paidBy, double amount, MetaData metaData, List<Split> splits) {
        super(paidBy, amount, metaData, splits);
    }

    @Override
    public boolean validate() {
        double sumSplitPercent = 0.0;
        double totalPercent = 100;
        for(Split split: splits){
            PercentSplit percentSplit = (PercentSplit) split;
            sumSplitPercent += percentSplit.getPercent();
        }
        return sumSplitPercent == totalPercent;
    }

    @Override
    public void enrichSplit() {
        splits.forEach(split -> {
            PercentSplit percentSplit = (PercentSplit) split;
            split.setAmount(percentSplit.getPercent()*amount/100);
        });
    }
}
