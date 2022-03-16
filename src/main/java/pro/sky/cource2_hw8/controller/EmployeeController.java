package pro.sky.cource2_hw8.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.cource2_hw8.Exception.BadRequestException;
import pro.sky.cource2_hw8.employeeClass.Employee;
import pro.sky.cource2_hw8.employeeClass.EmployeeID;
import pro.sky.cource2_hw8.service.EmployeeQuery;
import pro.sky.cource2_hw8.service.MapStorageEmployeeManager;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    public final MapStorageEmployeeManager employeeManager;
    public final EmployeeQuery employeeQuery;

    public EmployeeController(MapStorageEmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
        this.employeeQuery = new EmployeeQuery(this.employeeManager);
    }

    @GetMapping("/welcome")
    public String wellcome() {
        return "Книга учета сотрудников";

    }


    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("fistName") String fistName, @RequestParam("lastName") String lastName) {
        if (!(StringUtils.isAlpha(lastName) && StringUtils.isAlpha(fistName))) {
            throw new BadRequestException();
        }
        EmployeeID employeeID = new EmployeeID(StringUtils.capitalize(fistName), StringUtils.capitalize(lastName));
        employeeManager.removeEmployee(employeeID);
        return "Сотрудник: " + fistName + " " + lastName + " найден и удален!";
    }


    @GetMapping("/find")
    public String findEmployee(@RequestParam("fistName") String fistName, @RequestParam("lastName") String lastName) {
        if (!(StringUtils.isAlpha(lastName) && StringUtils.isAlpha(fistName))) {
            throw new BadRequestException();
        }
        EmployeeID employeeID = new EmployeeID(StringUtils.capitalize(fistName), StringUtils.capitalize(lastName));
        Employee employee = employeeManager.findEmployee(employeeID);
        return "Сотрудник: " + employeeID + " найден!";
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("fistName") String fistName, @RequestParam("lastName") String lastName,
                              @RequestParam("salary") double salary, @RequestParam("department") int department) {
        if (!(StringUtils.isAlpha(lastName) && StringUtils.isAlpha(fistName))) {
            throw new BadRequestException();
        }
        EmployeeID newemployeeID = new EmployeeID(StringUtils.capitalize(fistName), StringUtils.capitalize(lastName));
        Employee newemployee = new Employee(salary, department);
        employeeManager.addEmployee(newemployeeID, newemployee);
        return "Сотрудник: " + newemployeeID + "добавлен книгу учета";
    }

    @GetMapping("/getEmployee")
    public List<String> printList() {
        List<String> result = new ArrayList<String>();
        employeeManager.getEmployees().forEach((employeeID, employee) -> {
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
        return employeeQuery.printEmployeeDepartment(department);
    }


    @GetMapping("/departments/all")
    public Map<Integer, List<Map.Entry<EmployeeID, Employee>>> printEmployeeAll() {
        return employeeQuery.printEmployeeAll();
    }
}


