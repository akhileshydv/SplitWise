package services.impl;

import entites.Expense;
import entites.User;
import enums.ExpenseType;
import repositories.UserRepository;
import services.UserService;

public class UserServiceImpl implements UserService {

    UserRepository userRepository = new UserRepository();
    @Override
    public User addUser(String id, String name, String email, Long phoneNo) {
        User user = new User(id, name, email, phoneNo);
        return userRepository.save(user);
    }
}
