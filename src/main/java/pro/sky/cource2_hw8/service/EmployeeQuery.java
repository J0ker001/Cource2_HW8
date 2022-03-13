package pro.sky.cource2_hw8.service;


import org.springframework.stereotype.Service;
import pro.sky.cource2_hw8.Exception.NotFoundException;
import pro.sky.cource2_hw8.Interface.IEmployeeManager;
import pro.sky.cource2_hw8.Interface.IEmployeeQuery;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeQuery implements IEmployeeQuery {
    private IEmployeeManager iEmployeeManager;


    public EmployeeQuery(IEmployeeManager iEmployeeManager) {
        this.iEmployeeManager = iEmployeeManager;
    }

    public IEmployeeManager getiEmployeeManager() {
        return iEmployeeManager;
    }

    @Override
    public EmployeeID maxSalaryEmployee(int department) {
        return iEmployeeManager.getEmployees().entrySet()
                .stream()
                .filter(iEmployeeManager -> iEmployeeManager.getValue().getDepartment() == department)
                .max(Comparator.comparing(employeeIDEmployeeEntry -> employeeIDEmployeeEntry.getValue().getSalary()))
                .orElseThrow(() -> new NotFoundException("Сотрудник не найден"))
                .getKey();
    }

    @Override
    public EmployeeID minSalaryEmployee(int department) {
        return iEmployeeManager.getEmployees().entrySet()
                .stream()
                .filter(iEmployeeManager -> iEmployeeManager.getValue().getDepartment() == department)
                .min(Comparator.comparing(employeeIDEmployeeEntry -> employeeIDEmployeeEntry.getValue().getSalary()))
                .orElseThrow(() -> new NotFoundException("Сотрудник не найден"))
                .getKey();
    }

    @Override
    public List printEmployeeDepartment(int department) {

        return iEmployeeManager.getEmployees().entrySet().stream()
                .filter(iEmployeeManager -> iEmployeeManager.getValue().getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Map.Entry<EmployeeID, Employee>>> printEmployeeAll() {

        return iEmployeeManager.getEmployees().entrySet().stream()
                .collect(Collectors.groupingBy(iEmployeeManager -> iEmployeeManager.getValue().getDepartment()));
    }
}








