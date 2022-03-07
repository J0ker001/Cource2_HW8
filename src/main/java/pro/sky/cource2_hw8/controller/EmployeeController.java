package pro.sky.cource2_hw8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.cource2_hw8.service.Employee;
import pro.sky.cource2_hw8.service.EmployeeID;
import pro.sky.cource2_hw8.service.EmployeeService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/welcome")
    public String wellcome() {
        return "Книга учета сотрудников";

    }


    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("fistName") String fistName, @RequestParam("lastName") String lastName) {
        EmployeeID employeeID = new EmployeeID(fistName, lastName);
        employeeService.removeEmployee(employeeID);
        return "Сотрудник: " + fistName + " " + lastName + " найден и удален!";
    }


    @GetMapping("/find")
    public String findEmployee(@RequestParam("fistName")String fistName,@RequestParam("lastName") String lastName) {
        EmployeeID employeeID = new EmployeeID(fistName, lastName);
        Employee employee = employeeService.findEmployee(employeeID);
        return "Сотрудник: " + employeeID + " найден!";
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("fistName")String fistName,@RequestParam("lastName") String lastName,
                              @RequestParam("salary")double salary,@RequestParam("department") int department) {

        EmployeeID newemployeeID = new EmployeeID(fistName, lastName);
        Employee newemployee = new Employee(salary, department);
        employeeService.addEmployee(newemployeeID, newemployee);
        return "Сотрудник: " + newemployeeID + "добавлен книгу учета";
    }

    @GetMapping("/getEmployee")
    public List<String> printList() {
        List<String> result = new ArrayList<String>();
        employeeService.getEmployees().forEach((employeeID, employee) -> {
            result.add(employeeID.toString() + " " + employee.toString());
        });
        return result;
    }
}


