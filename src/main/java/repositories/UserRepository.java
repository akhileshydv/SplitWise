package repositories;

import entites.Expense;
import entites.User;

import java.util.*;

public class UserRepository implements BaseDao<User, String> {
    private static HashMap<String, User> userDB = new HashMap<>();
    @Override
    public User save(User entity) {
        userDB.putIfAbsent(entity.getId(), entity);
        return entity;
    }

    @Override
    public User update(User entity) {
        validate(entity.getId());
        userDB.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public User findById(String id) {
        validate(id);
        return userDB.get(id);
    }

    @Override
    public void delete(String id) {
        validate(id);
        userDB.remove(id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for(Map.Entry entry: userDB.entrySet()){
            users.add((User) entry.getValue());
        }
        return users;
    }

    private void validate(String id){
        Optional.ofNullable(userDB.get(id)).orElseThrow(()-> new RuntimeException("User not found"));
    }
}
