package services;

import entites.User;
import enums.ExpenseType;

public interface UserService {
    User addUser(String id, String name, String email, Long phoneNo);
}
