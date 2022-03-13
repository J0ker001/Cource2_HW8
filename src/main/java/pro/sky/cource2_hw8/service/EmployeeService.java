package pro.sky.cource2_hw8.service;


import org.springframework.stereotype.Service;
import pro.sky.cource2_hw8.Exception.BadRequestException;
import pro.sky.cource2_hw8.Exception.NotFoundException;
import pro.sky.cource2_hw8.Interface.IEmployeeManager;


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


            new EmployeeID("Иван", "Сухин"), new Employee(120000, 1),
            new EmployeeID("Семен", "Семенов"), new Employee(90000, 5),
            new EmployeeID("Виктор", "Замков"), new Employee(170000, 1),
            new EmployeeID("Сергей", "Матвеев"), new Employee(15000, 3),
            new EmployeeID("Маргарита", "Степакова"), new Employee(21000, 5),
            new EmployeeID("Игнат", "Федотов"), new Employee(173000, 4),
            new EmployeeID("Марина", "Жукова"), new Employee(269000, 2),
            new EmployeeID("Станислав", "Сумкин"), new Employee(56000, 4),
            new EmployeeID("Светлана", "Сумка"), new Employee(103947, 3),
            new EmployeeID("Андрей", "Петров"), new Employee(21375, 2)

    ));


}
