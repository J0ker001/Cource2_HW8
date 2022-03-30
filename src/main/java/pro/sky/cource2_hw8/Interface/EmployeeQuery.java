package pro.sky.cource2_hw8.Interface;

import pro.sky.cource2_hw8.employeeClass.Employee;
import pro.sky.cource2_hw8.employeeClass.EmployeeID;
import java.util.List;
import java.util.Map;

public interface EmployeeQuery {
    EmployeeID maxSalaryEmployee(int department);

    EmployeeID minSalaryEmployee(int department);

    List printEmployeeDepartment(int department);

    Map<Integer, List<Map.Entry<EmployeeID, Employee>>> printEmployeeAll();
}
