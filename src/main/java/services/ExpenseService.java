package services;

import entites.Expense;
import entites.MetaData;
import entites.Split;
import enums.ExpenseType;

import java.util.List;

public interface ExpenseService{
    void createExpense(String paidBy, double amount, List<Split> splits, ExpenseType type, MetaData metaData);
    void showBalances();
    void showBalance(String userId);
}
