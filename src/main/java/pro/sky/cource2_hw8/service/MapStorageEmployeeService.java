package pro.sky.cource2_hw8.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.cource2_hw8.Exception.AlreadyAddedException;
import pro.sky.cource2_hw8.Exception.BadRequestException;
import pro.sky.cource2_hw8.Exception.NotFoundException;
import pro.sky.cource2_hw8.Interface.EmployeeService;
import pro.sky.cource2_hw8.employeeClass.Employee;
import pro.sky.cource2_hw8.employeeClass.EmployeeID;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapStorageEmployeeService implements EmployeeService {

    private HashMap<EmployeeID, Employee> employeeMap = new HashMap<>();

    @Override
    public EmployeeID сheckingAndCreatingEmployeeID(String fistName, String lastName) {
        if (!(StringUtils.isAlpha(lastName) && StringUtils.isAlpha(fistName))) {
            throw new BadRequestException();
        }
        EmployeeID employeeID = new EmployeeID(fistName, lastName);
        return employeeID;
    }

    @Override
    public void addEmployee(EmployeeID employeeID, Employee employee) {
        if (!(StringUtils.isAlpha(employeeID.getFistName()) && StringUtils.isAlpha(employeeID.getLastName()))) {
            throw new BadRequestException();
        }
        if (employeeMap.containsKey(employeeID)) {
            throw new AlreadyAddedException();
        }
        employeeMap.put(employeeID, employee);
    }

    @Override
    public void removeEmployee(EmployeeID employeeID) {
        if (!employeeMap.containsKey(employeeID)) {
            throw new NotFoundException();
        }
        employeeMap.remove(employeeID);
    }

    @Override
    public Employee findEmployee(EmployeeID employeeID) {

        if (!employeeMap.containsKey(employeeID)) {
            throw new NotFoundException();
        }
        return employeeMap.get(employeeID);

    }

    @Override
    public Map<EmployeeID, Employee> getEmployees() {
        return employeeMap;
    }
}
