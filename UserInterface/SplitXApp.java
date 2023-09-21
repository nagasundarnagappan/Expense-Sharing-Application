package UserInterface;

import Methods.Authorization;
import Methods.Admin;
import Methods.User;

import java.util.Scanner;

public class SplitXApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Admin ad = new Admin();
        User us = new User();

        boolean cont = true;
        while (cont) {
            System.out.println("Welcome to SplitX!");
            System.out.println("1. Sign in");
            System.out.println("2. Sign up");
            System.out.println("3. Quit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Sign in");
                    System.out.println("Enter your username : ");
                    sc.nextLine();
                    String userName = sc.nextLine();
                    System.out.println("Enter your password : ");
                    // sc.nextLine();
                    String userPassword = sc.nextLine();

                    if (userName.equals("Admin") && userPassword.equals("Admin")) {
                        while (cont) {
                            System.out.println("Welcome Admin!");
                            System.out.println("1. Display all users");
                            System.out.println("2. Display single user");
                            System.out.println("3. Delete user");
                            System.out.println("4. Display all expenses");
                            System.out.println("5. Display single expense");
                            System.out.println("6. Delete expense");
                            System.out.println("7. Exit");
                            System.out.println("Enter your choice: ");

                            int adminChoice = sc.nextInt();

                            switch (adminChoice) {
                                case 1:
                                    Admin.viewUser();
                                    break;
                                case 2:
                                    System.out.println("Enter user id : ");
                                    int userId = sc.nextInt();
                                    Admin.viewUser(userId);
                                    break;
                                case 3:
                                    System.out.println("Enter user id : ");
                                    userId = sc.nextInt();
                                    Admin.deleteUser(userId);
                                    break;
                                case 4:
                                    Admin.displayAllExpenses();
                                    break;
                                case 5:
                                    System.out.println("Enter expense id : ");
                                    int expenseId = sc.nextInt();
                                    ad.displaySingleExpense(expenseId);
                                    break;
                                case 6:
                                    System.out.println("Enter expense id : ");
                                    expenseId = sc.nextInt();
                                    ad.deleteExpense(expenseId);
                                    break;
                                case 7:
                                    System.out.println("Thank you for using SplitX!");
                                    cont = false;
                                    break;
                                default:
                            }
                        }

                    } else {
                        while (!Authorization.signIn(userName, userPassword)) {
                            System.out.println("Invalid username or password");
                            System.out.println("Enter your username : ");
                            userName = sc.nextLine();
                            System.out.println("Enter your password : ");
                            userPassword = sc.nextLine();
                        }

                        int userId = Authorization.getUserId(userName);

                        while (cont) {
                            System.out.println("Welcome " + userName + "!");
                            System.out.println("1. Display all expenses");
                            System.out.println("2. Display single expense details");
                            System.out.println("3. Display expenses to pay");
                            System.out.println("4. Display expenses to receive");
                            System.out.println("5. Create expense");
                            System.out.println("6. Delete expense");
                            System.out.println("7. Pay expense");
                            System.out.println("8. Mark share received");
                            System.out.println("9. Exit");
                            System.out.println("Enter your choice: ");

                            int userChoice = Integer.parseInt(sc.nextLine());

                            switch (userChoice) {
                                case 1:
                                    User.displayAllExpensesOfSpecificUser(userId);
                                    break;
                                case 2:
                                    System.out.println("Enter expense id : ");
                                    int expenseId = Integer.parseInt(sc.nextLine());
                                    us.displaySingleExpense(expenseId);
                                    break;
                                case 3:
                                    User.displayAllExpenseToPay(userId);
                                    break;
                                case 4:
                                    User.displayExpensesToReceive(userId);
                                    break;
                                case 5:
                                    System.out.println("Enter the expense name : ");
                                    String expenseName = sc.nextLine();

                                    System.out.println("Enter the expense description : ");
                                    String expenseDescription = sc.nextLine();

                                    System.out.println("Enter the expense amount : ");
                                    double expenseAmount = sc.nextDouble();
                                    sc.nextLine();

                                    System.out.println("Enter the expense date in format of (dd-MM-yyyy) : ");
                                    String expenseDate = sc.nextLine();

                                    System.out.println("Enter the number of users to share the expense with : ");
                                    int numberOfUsers = sc.nextInt();
                                    sc.nextLine();

                                    String sharedUsersNames[] = new String[numberOfUsers];

                                    for (int i = 0; i < numberOfUsers; i++) {
                                        System.out.println("Enter the user name of user " + (i + 1) + " : ");
                                        String name = sc.nextLine();

                                        sharedUsersNames[i] = name;
                                    }

                                    User.addExpense(userId, expenseName, expenseDescription, expenseAmount,
                                            expenseDate, sharedUsersNames);
                                    break;
                                case 6:
                                    System.out.println("Enter expense id : ");
                                    expenseId = Integer.parseInt(sc.nextLine());
                                    us.deleteExpense(expenseId);
                                    break;
                                case 7:
                                    System.out.println("Enter expense id : ");
                                    expenseId = Integer.parseInt(sc.nextLine());
                                    User.payExpense(userId, expenseId);
                                    break;
                                case 8:
                                    System.out.println("Enter expense id : ");
                                    expenseId = Integer.parseInt(sc.nextLine());
                                    System.out.println("Enter user name : ");
                                    String userNameToMark = sc.nextLine();
                                    User.markReceived(expenseId, userNameToMark);
                                    break;
                                case 9:
                                    System.out.println("Thank you for using SplitX!");
                                    cont = false;
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Sign up");
                    System.out.println("Enter desired username : ");
                    sc.nextLine();
                    String newUserName = sc.nextLine();

                    while (!Authorization.checkIfUsernameIsUnique(newUserName)) {
                        System.out.println("Username already exists");
                        System.out.println("Enter desired username : ");
                        newUserName = sc.nextLine();
                    }

                    System.out.println(
                            "Password must be at least 8 characters long and must contain at least one uppercase letter, one lowercase letter, one number and one special character");
                    System.out.println("Enter desired password : ");
                    String newUserPassword = sc.nextLine();

                    while (!Authorization.checkPasswordStrength(newUserPassword)) {
                        System.out.println("Password is not strong enough");
                        System.out.println("Enter desired password : ");
                        newUserPassword = sc.nextLine();
                    }

                    Authorization.signUp(newUserName, newUserPassword);

                    System.out.println("Sign up successful");
                    break;
                case 3:
                    System.out.println("Thank you for using SplitX!");
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}
