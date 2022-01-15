package repositories;

import entites.Expense;

import java.util.*;

public class ExpenseRepository implements BaseDao<Expense, Long>{
    private static HashMap<Long, Expense> expenseDB  = new HashMap<>();
    Long currentId = 0L;
    @Override
    public Expense save(Expense entity) {
        currentId++;
        expenseDB.putIfAbsent(currentId, entity);
        return entity;
    }

    @Override
    public Expense update(Expense entity) {
        validate(entity.getId());
        expenseDB.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Expense findById(Long id) {
        validate(id);
        return expenseDB.get(id);
    }

    @Override
    public void delete(Long id) {
        validate(id);
        expenseDB.remove(id);
    }

    @Override
    public List<Expense> findAll() {
        List<Expense> expenses = new ArrayList<>();
        for(Map.Entry entry: expenseDB.entrySet()){
            expenses.add((Expense) entry.getValue());
        }
        return expenses;
    }

    private void validate(Long id){
        Optional.ofNullable(expenseDB.get(id)).orElseThrow(()-> new RuntimeException("Expense not found"));
    }
}
