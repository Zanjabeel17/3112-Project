public class Employee extends User {
    private String empName;
    private String position;
    private double hourlyPayRate;
    private double totalHoursWorked;

    public Employee(String empName, String username, String password, String position, double hourlyPayRate) {
        super(username, password);
        this.empName = empName;
        this.position = position;
        this.hourlyPayRate = hourlyPayRate;
    }

     /**
     * Implementation of login for Employee.
     * @param enteredPassword the password entered by the user during login
     * @return true if the login is successful, otherwise it is false
     */
    public boolean login(String enteredUsername, String enteredPassword) {
        
        if (enteredUsername.equals(getUsername()) && enteredPassword.equals(getPassword())) {
            loggedIn = true;
            return true;
        }
        return false; 
    }


    /**
     * Gets the name of the employee.
     * @return the name of the employee
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * Sets the name of the employee.
     * @param empName the new name to set for the employee
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    /**
     * Gets the position of the employee.
     * @return the position of the employee
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position of the employee.
     * @param position the new position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets the hourly pay rate of the employee.
     * @return the hourly pay rate of the employee
     */
    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    /**
     * Sets the hourly pay rate of the employee.
     * @param hourlyPayRate the new hourly pay rate to set
     */
    public void setHourlyPayRate(double hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    /**
     * Gets the hourly pay rate of the employee.
     * @return the hourly pay rate of the employee
     */
    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    /**
     * Sets the hourly pay rate of the employee.
     * @param hourlyPayRate the new hourly pay rate to set
     */
    public void setTotalHoursWorked(double totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    /**
     * Returns a string representation of the employee.
     * @return a string containing the employee's username and position
     */
    @Override
    public String toString() {
        return "Employee [Username: " + getUsername() + ", Position: " + position + "]";
    }
}