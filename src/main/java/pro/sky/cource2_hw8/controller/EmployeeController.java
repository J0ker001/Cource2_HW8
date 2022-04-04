package pro.sky.cource2_hw8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.cource2_hw8.Interface.EmployeeService;
import pro.sky.cource2_hw8.employeeClass.Employee;
import pro.sky.cource2_hw8.employeeClass.EmployeeID;
import pro.sky.cource2_hw8.service.EmployeeQuery;
import pro.sky.cource2_hw8.service.MapStorageEmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    public final EmployeeService employeeService;
    public final EmployeeQuery employeeQuery;

    public EmployeeController(MapStorageEmployeeService employeeService) {
        this.employeeService = employeeService;
        this.employeeQuery = new EmployeeQuery(this.employeeService);
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Книга учета сотрудников";
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("fistName") String fistName, @RequestParam("lastName") String lastName) {
        EmployeeID employeeID = employeeService.сheckingAndCreatingEmployeeID(fistName, lastName);
        employeeService.removeEmployee(employeeID);
        return "Сотрудник: " + employeeID + " найден и удален!";
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam("fistName") String fistName, @RequestParam("lastName") String lastName) {
        EmployeeID employeeID = employeeService.сheckingAndCreatingEmployeeID(fistName, lastName);
        Employee employee = employeeService.findEmployee(employeeID);
        return "Сотрудник: " + employeeID + " найден!";
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("fistName") String fistName, @RequestParam("lastName") String lastName,
                              @RequestParam("salary") double salary, @RequestParam("department") int department) {
        EmployeeID employeeID = employeeService.сheckingAndCreatingEmployeeID(fistName, lastName);
        Employee newemployee = new Employee(salary, department);
        employeeService.addEmployee(employeeID, newemployee);
        return "Сотрудник: " + employeeID + " добавлен книгу учета";
    }

    @GetMapping("/getEmployee")
    public List<String> printList() {
        List<String> result = new ArrayList<String>();
        employeeService.getEmployees().forEach((employeeID, employee) -> {
            result.add(employeeID.toString() + " " + employee.toString());
        });
        return result;
    }

    @GetMapping("/departments/max-salary")
    public String maxSalaryEmployee(int department) {
        return employeeQuery.maxSalaryEmployee(department).toString();
    }

    @GetMapping("/departments/mix-salary")
    public String mixSalaryEmployee(int department) {
        return employeeQuery.minSalaryEmployee(department).toString();
    }

    @GetMapping("/departments/allDepartment")
    public List printEmployeeDepartment(int department) {
        return employeeQuery.printEmployeesByDepartment(department);
    }

    @GetMapping("/departments/all")
    public Map<Integer, List<Map.Entry<EmployeeID, Employee>>> printEmployeeAll() {
        return employeeQuery.printAllEmployeesByDepartment();
    }
}


