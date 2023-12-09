import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        EmployeeManager empManager = new EmployeeManager();
        TimeClock timeClock = new TimeClock();
        boolean loggedIn = false;
        boolean adminLoggedIn = false;
        Employee employee = null; 
        Administrator admin = new Administrator(empManager,"MarkZuckerberg", "antisocialWIZ123");
        
        try {
            
            String filePath = "users.txt";
            Scanner fileScanner = new Scanner(new File(filePath));
            fileScanner.nextLine();

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                
                String empName = data[0];
                String username = data[1];
                String password = data[2];
                String position = data[3];
                double hourlyPayRate = Double.parseDouble(data[4]);

                
                Employee newEmployee = new Employee(empName, username, password, position, hourlyPayRate);

                
                empManager.addEmployee(newEmployee);
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found. Please check the file path and try again.");
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numeric values from the file.");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Time Clock!");

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Log In");
            System.out.println("2. Log Out");
            System.out.println("3. Clock In");
            System.out.println("4. Clock Out");
            System.out.println("5. View Weekly Hours and Pay");
            System.out.println("6. Admin");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (!loggedIn && !adminLoggedIn) {
                        System.out.println("Enter username: ");
                        String enteredUsername = scanner.next();
                        System.out.println("Enter password: ");
                        String enteredPassword = scanner.next();

                        if (admin.login(enteredUsername, enteredPassword)) {
                            adminLoggedIn = true;
                            System.out.println("Admin logged in successfully.");
                        } else {
                            employee = empManager.getEmployeeByUsername(enteredUsername);

                            if (employee != null && employee.login(enteredUsername, enteredPassword)) {
                                loggedIn = true;
                                System.out.println("Logged in successfully as " + employee.getEmpName());
                            } else {
                                System.out.println("Login failed. Incorrect username or password.");
                            }
                        }
                    } else {
                        System.out.println("You are already logged in.");
                    }
                    break;

                case 2:
                    if (loggedIn) {
                        employee.logout();
                        loggedIn = false;
                        System.out.println("Logged out successfully.");
                    } else {
                        System.out.println("You are not logged in.");
                    }
                    break;

                case 3:
                    if (loggedIn) {
                        System.out.println("Enter employee name for clock in:");
                        scanner.nextLine(); 
                        String employeeName = scanner.nextLine();
                        System.out.println("Enter the time for clock in:");
                        double clockInTime = scanner.nextDouble();

                        Employee clockInEmployee = empManager.getEmployeeByName(employeeName);
                        if (clockInEmployee != null) {
                            timeClock.clockIn(clockInEmployee, clockInTime);
                            System.out.println("Clocked in successfully for " + clockInEmployee.getEmpName());
                        } else {
                            System.out.println("Employee not found.");
                        }
                    } else {
                        System.out.println("You need to log in before clocking in.");
                    }

                    break;

                case 4:
                    if (loggedIn) {
                        System.out.println("Enter the time for clock out:");
                        double clockOutTime = scanner.nextDouble();

                        
                        if (employee != null) {
                            timeClock.clockOut(employee, clockOutTime);
                            System.out.println("Clocked out successfully for " + employee.getEmpName());
                        } else {
                            System.out.println("Employee not found.");
                        }
                    } else {
                        System.out.println("You need to log in before clocking out.");
                    }

                    break;

                case 5:
                    if (loggedIn) {
                            double weeklyHours = timeClock.calculateHoursWorked(employee);
                            double weeklyPay = timeClock.calculateWeeklyPay(employee);
                    
                            System.out.println("Total hours worked this week: " + weeklyHours);
                            System.out.println("Calculated pay for the week: $" + weeklyPay);
                        } else {
                            System.out.println("You need to log in to view your hours and pay.");
                        };

                    break;

                case 6:
                    if (adminLoggedIn && admin != null) {
                        System.out.println("Admin options:");
                        System.out.println("1. Add Employee");
                        System.out.println("2. Delete Employee");
                        System.out.println("3. View Employee Hours");
                        System.out.println("4. View Employee Pay");
                        System.out.println("5. Back to Main Menu");

                        int adminChoice = scanner.nextInt();

                        switch (adminChoice) {
                            case 1:
                                System.out.println("Enter employee name: ");
                                scanner.nextLine(); 
                                String name = scanner.nextLine();
                                System.out.println("Enter hourly pay rate: ");
                                double payRate = scanner.nextDouble();
                                System.out.println("Enter position: ");
                                scanner.nextLine(); 
                                String position = scanner.nextLine();

                                admin.addEmployee(name, payRate, position);
                                break;

                            case 2:
                                System.out.println("Enter employee name to delete: ");
                                scanner.nextLine(); 
                                String employeeToDelete = scanner.nextLine();
                                admin.deleteEmployee(employeeToDelete);
                                break;

                            case 3:
                                System.out.println("Enter employee name to view hours: ");
                                scanner.nextLine(); 
                                String employeeToViewHours = scanner.nextLine();
                                admin.viewEmployeeHours(employeeToViewHours);
                                break;

                            case 4:
                                System.out.println("Enter employee name to view pay: ");
                                scanner.nextLine(); 
                                String employeeToViewPay = scanner.nextLine();
                                admin.viewEmployeePay(employeeToViewPay);
                                break;

                            case 5:
                                
                                break;

                            default:
                                System.out.println("Invalid admin choice. Please select a valid option.");
                        }
                    } else {
                        System.out.println("You need to log in as an admin to access this option.");
                    }
                break;

                case 7:
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}