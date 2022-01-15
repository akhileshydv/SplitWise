import entites.EqualSplit;
import entites.ExactSplit;
import entites.PercentSplit;
import entites.Split;
import enums.ExpenseType;
import services.ExpenseService;
import services.UserService;
import services.impl.ExpenseServiceImpl;
import services.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String [] args){
        UserService userService = new UserServiceImpl();
        ExpenseService expenseService = new ExpenseServiceImpl();

        userService.addUser("u1", "nikhil", "nikhil@splitwise.com", 12345L);
        userService.addUser("u2", "rupal", "rupal@splitwise.com", 12345L);
        userService.addUser("u3", "rithesh", "rithesh@splitwise.com", 12345L);
        userService.addUser("u4", "mohit", "rithesh@splitwise.com", 12345L);
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            String [] commands = input.split(" ");
            switch (commands[0]){
                case "EXPENSE":
                    String paidBy = commands[1];
                    Double amount = Double.valueOf(commands[2]);
                    int numUsers = Integer.parseInt(commands[3]);
                    ExpenseType expenseType = ExpenseType.valueOf(commands[numUsers + 4]);
                    List<Split> splits = new ArrayList<>();
                    switch (expenseType){
                        case EQUAL :
                            for(int i=0; i<numUsers; i++){
                                splits.add(new EqualSplit(commands[4+i]));
                            }
                            expenseService.createExpense(paidBy, amount, splits, expenseType, null);
                            break;
                        case EXACT:
                            for(int i=0; i<numUsers;i++){
                                splits.add(new ExactSplit(commands[4+i], Double.parseDouble(commands[numUsers + 5 +i])));
                            }
                            expenseService.createExpense(paidBy, amount, splits, expenseType, null);
                            break;
                        case PERCENT:
                            for(int i=0; i<numUsers;i++){
                                splits.add(new PercentSplit(commands[4+i], Double.parseDouble(commands[numUsers + 5 +i])));
                            }
                            expenseService.createExpense(paidBy, amount, splits, expenseType, null);
                            break;
                        default:
                            return;
                    }
                    break;
                case "SHOW":
                    if(commands.length == 1){
                        expenseService.showBalances();
                    }
                    else{
                        expenseService.showBalance(commands[1]);
                    }
                    break;
                default:
                    return;
            }

        }
    }
}
