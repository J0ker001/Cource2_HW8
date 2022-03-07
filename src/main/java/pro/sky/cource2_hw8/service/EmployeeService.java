package pro.sky.cource2_hw8.service;

import org.springframework.stereotype.Service;
import pro.sky.cource2_hw8.Exception.BadRequestException;
import pro.sky.cource2_hw8.Exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeService implements IEmployeeManager {


    @Override
    public void addEmployee(EmployeeID employeeID, Employee employee) {
        if (employeeMap.containsKey(employeeID)) {
            throw new BadRequestException();
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

    private HashMap<EmployeeID, Employee> employeeMap = new HashMap<>(Map.of(


            new EmployeeID("Иван", "Сухин"), new Employee(0, 1),
            new EmployeeID("Семен", "Семенов"), new Employee(0, 1),
            new EmployeeID("Виктор", "Замков"), new Employee(0, 1),
            new EmployeeID("Сергей", "Матвеев"), new Employee(0, 1),
            new EmployeeID("Маргарита", "Степакова"), new Employee(0, 1),
            new EmployeeID("Игнат", "Федотов"), new Employee(0, 1),
            new EmployeeID("Марина", "Жукова"), new Employee(0, 1),
            new EmployeeID("Станислав", "Сумкин"), new Employee(0, 1),
            new EmployeeID("Светлана", "Сумка"), new Employee(0, 1),
            new EmployeeID("Андрей", "Петров"), new Employee(0, 1)

    ));
}
