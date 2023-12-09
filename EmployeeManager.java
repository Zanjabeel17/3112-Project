import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private List<Employee> employees;

    public EmployeeManager() {
        employees = new ArrayList<>();
    }

    /**
     * Adds a new employee to the list of employees.
     * @param employee the employee to be added
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

     /**
     * Deletes an employee from the list based on their name.
     * @param employeeName the name of the employee to be deleted
     */
    public void deleteEmployee(String employeeName) {
        Employee employeeToDelete = null;
        for (Employee employee : employees) {
            if (employee.getEmpName().equals(employeeName)) {
                employeeToDelete = employee;
                break;
            }
        }
        if (employeeToDelete != null) {
            employees.remove(employeeToDelete);
        }
    }

    /**
     * Retrieves an employee from the list based on their name.
     * @param employeeName the name of the employee to be retrieved
     * @return the employee object if found, otherwise null
     */
    public Employee getEmployeeByName(String employeeName) {
        for (Employee employee : employees) {
            if (employee.getEmpName().equals(employeeName)) {
                return employee;
            }
        }
        return null; 
    }

    /**
     * Retrieves an employee from the list based on their username.
     * @param username the username of the employee to be retrieved
     * @return the employee object if found, otherwise null
     */
    public Employee getEmployeeByUsername(String username) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                return employee;
            }
        }
        return null; 
    }

    /**
     * Retrieves a list of all employees.
     * @return the list of all employees
     */
    public List<Employee> getAllEmployees() {
        return employees;
    }

  
}