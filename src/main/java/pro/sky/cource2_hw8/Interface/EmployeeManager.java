package pro.sky.cource2_hw8.Interface;

import pro.sky.cource2_hw8.employeeClass.Employee;
import pro.sky.cource2_hw8.employeeClass.EmployeeID;
import java.util.Map;

public interface EmployeeManager {

    void addEmployee(EmployeeID employeeID, Employee employee);

    void removeEmployee(EmployeeID employeeID);

    Employee findEmployee(EmployeeID employeeID);

    Map<EmployeeID, Employee> getEmployees();
}
