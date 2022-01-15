package services.impl;

import entites.*;
import enums.ExpenseType;
import repositories.ExpenseRepository;
import repositories.UserRepository;
import services.ExpenseService;

import java.util.List;
import java.util.Map;

public class ExpenseServiceImpl implements ExpenseService {
    UserRepository userRepository = new UserRepository();
    ExpenseRepository expenseRepository = new ExpenseRepository();

    @Override
    public void createExpense(String paidBy, double amount, List<Split> splits, ExpenseType type, MetaData metaData) {
        Expense expense = addExpense(paidBy, amount, splits, type, metaData);
        if(!expense.validate()){
            System.out.println("Invalid Expense");
            return;
        }
        expense.enrichSplit();
        splits.forEach(split -> {
            Map<String, Double> userBalanceSheet = userRepository.findById(paidBy).getBalanceSheet();
            String paidTo = split.getUserId();
            userBalanceSheet.put(paidTo, userBalanceSheet.getOrDefault(paidTo, 0.0) + split.getAmount());

            Map<String, Double> otherBalanceSheet = userRepository.findById(paidTo).getBalanceSheet();
            otherBalanceSheet.put(paidBy, otherBalanceSheet.getOrDefault(paidBy, 0.0) - split.getAmount());

        });
        expenseRepository.save(expense);
    }

    @Override
    public void showBalances() {
        userRepository.findAll().forEach(user -> {
            showBalance(user.getId());
        });
    }

    @Override
    public void showBalance(String userId) {
        Map<String, Double> balanceSheet = userRepository.findById(userId).getBalanceSheet();
        Boolean isEmpty = true;
        for(Map.Entry<String, Double> entry: balanceSheet.entrySet()){
            if(entry.getValue() != 0){
                isEmpty = false;
                printBalance(userId, entry.getKey(), entry.getValue());
            }
        }
        if(isEmpty){
            System.out.println("No Balances");
        }
    }

    private Expense addExpense(String paidBy, double amount, List<Split> splits, ExpenseType type, MetaData metaData){
        Expense expense;
        switch (type){
            case EQUAL:
                expense = new EqualExpense(paidBy, amount, metaData, splits);
                return expense;
            case EXACT:
                expense = new ExactExpense(paidBy, amount, metaData, splits);
                return expense;
            case PERCENT:
                expense = new PercentExpense(paidBy, amount, metaData, splits);
                return expense;
            default:
                return null;

        }
    }

    private void printBalance(String paidBy, String paidTo, double amount){
        if(amount<0){
            System.out.println(paidBy + " owes " +paidTo +": "+ Math.abs(amount));
        }
        else{
            System.out.println(paidTo + " owes " +paidBy +": "+ Math.abs(amount));
        }
    }
}
