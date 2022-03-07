package pro.sky.cource2_hw8.service;
import java.util.Map;

public interface IEmployeeManager {

    void addEmployee(EmployeeID employeeID, Employee employee);

    void removeEmployee(EmployeeID employeeID);

    Employee findEmployee(EmployeeID employeeID);

    Map<EmployeeID, Employee> getEmployees();
}
