public class Administrator extends User {
    private EmployeeManager empManager;

    public Administrator(EmployeeManager empManager, String username, String password) {
        super(username, password);
        this.empManager = empManager;
    }

    /**
     * @param enteredUsername the username entered by the user during login
     * @param enteredPassword the password entered by the user during login
     * @return true if the login is successful, otherwise it is false
     */
    @Override
    public boolean login(String enteredUsername, String enteredPassword) {
        
        if (enteredUsername.equals(getUsername()) && enteredPassword.equals(getPassword())) {
            loggedIn = true;
            return true;
        }
        return false; 
    }

    /**
     * Adds a new employee to the EmployeeManager
     * @param name the name of the new employee
     * @param hourlyPayRate the hourly pay rate of the new employee
     * @param position the position of the new employee
     */
    public void addEmployee(String name, double hourlyPayRate, String position) {
        Employee newEmployee = new Employee(name, getUsername(), getPassword(), position, hourlyPayRate);
        empManager.addEmployee(newEmployee);
    }

    /**
     * Deletes an employee from the EmployeeManager
     * @param employeeName the name of the employee to be deleted
     */
    public void deleteEmployee(String employeeName) {
        empManager.deleteEmployee(employeeName);
    }


    /**
     * Displays the hours worked by a specific employee
     * @param employeeName the name of the employee
     */
    public void viewEmployeeHours(String employeeName) {
        Employee employee = empManager.getEmployeeByName(employeeName);
        if (employee != null) {
            double hoursWorked = employee.getTotalHoursWorked();
            System.out.println(employeeName + " has worked " + hoursWorked + " hours.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    /**
     * Displays the total pay earned by a specific employee for the week
     * @param employeeName the name of the employee
     */
    public void viewEmployeePay(String employeeName) {
        Employee employee = empManager.getEmployeeByName(employeeName);
        if (employee != null) {
            double weeklyPay = employee.getHourlyPayRate() * employee.getTotalHoursWorked();
            System.out.println(employeeName + " has earned $ " + weeklyPay + "so far this week.");
        } else {
            System.out.println("Employee not found.");
        }
    }

}