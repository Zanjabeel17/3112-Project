import java.util.ArrayList;
import java.util.List;

public class TimeClock {
    private List<Employee> employees;
    private List<Double> clockInTimes;
    private List<Double> clockOutTimes;

    public TimeClock() {
        employees = new ArrayList<>();
        clockInTimes = new ArrayList<>();
        clockOutTimes = new ArrayList<>();
    }

    /**
     * Records a clock-in time for an employee.
     * @param employee the employee who is clocking in
     * @param time the time at which the employee is clocking in
     */
    public void clockIn(Employee employee, double time) {
        employees.add(employee);
        clockInTimes.add(time);
    }

    /**
     * Records a clock-out time for an employee and calculates the hours worked.
     * @param employee the employee who is clocking out
     * @param time the time at which the employee is clocking out
     */
    public void clockOut(Employee employee, double time) {
        employees.add(employee);
        clockOutTimes.add(time);

        int index = employees.indexOf(employee);

        if (index != -1) {
            double clockInTime = clockInTimes.get(index);
            double clockOutTime = clockOutTimes.get(index);

           
            double hoursWorked = calculateHoursWorked(clockInTime, clockOutTime);
            employee.setTotalHoursWorked(employee.getTotalHoursWorked() + hoursWorked);

            
            clockInTimes.remove(index);
            clockOutTimes.remove(index);
        } else {
            System.out.println("Employee not found in the list.");
        }
    }

    /**
     * Calculates the hours worked based on clock-in and clock-out times.
     * @param clockInTime the clock-in time
     * @param clockOutTime the clock-out time
     * @return the hours worked
     */
    private double calculateHoursWorked(double clockInTime, double clockOutTime) {
        if (clockOutTime < clockInTime) {
            clockOutTime += 12.0; 
        }
        return clockOutTime - clockInTime;
    }

    /**
     * Calculates the total hours worked by an employee.
     * @param employee the employee for whom to calculate total hours worked
     * @return the total hours worked by the employee
     */
    public double calculateHoursWorked(Employee employee) {
        
        return employee.getTotalHoursWorked();
    }

    /**
     * Calculates the weekly pay for an employee.
     * @param employee the employee for whom to calculate weekly pay
     * @return the weekly pay for the employee
     */
    public double calculateWeeklyPay(Employee employee) {
        double hourlyPayRate = employee.getHourlyPayRate();
        double hoursWorkedThisWeek = calculateHoursWorked(employee);

        return hoursWorkedThisWeek * hourlyPayRate;
    }
}