package pro.sky.cource2_hw8.EmployeeQueryTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.cource2_hw8.Exception.NotFoundException;
import pro.sky.cource2_hw8.Interface.EmployeeService;
import pro.sky.cource2_hw8.employeeClass.Employee;
import pro.sky.cource2_hw8.employeeClass.EmployeeID;
import pro.sky.cource2_hw8.service.EmployeeQuery;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeQueryTest {

    EmployeeID minSalary = new EmployeeID("Зарат", "Пизотов");
    EmployeeID person2 = new EmployeeID("Марат", "Ризотов");
    EmployeeID person3 = new EmployeeID("Барат", "Лизотов");
    EmployeeID maxSalary = new EmployeeID("Карат", "Мизотов");

    private HashMap<EmployeeID, Employee> fistDepartmentOut = new HashMap<EmployeeID, Employee>((Map.of(
            minSalary, new Employee(1, 1),
            person2, new Employee(2, 1),
            person3, new Employee(3, 1),
            maxSalary, new Employee(4, 1)
    )));

    @Mock
    public EmployeeService serviceMock;

    @InjectMocks
    private EmployeeQuery employeeQuery;

    @Test
    public void shouldFindEmployeeWithMaxSalaryByDepartmentID() {
        when(serviceMock.getEmployees()).thenReturn(fistDepartmentOut);
        assertEquals(maxSalary, employeeQuery.maxSalaryEmployee(1));
    }

    @Test
    public void shouldTrowWhenNotFindDepartmentEmployeeWithMaxSalaryByDepartmentID() {
        when(serviceMock.getEmployees()).thenReturn(fistDepartmentOut);
        assertThrows(NotFoundException.class, () -> employeeQuery.maxSalaryEmployee(2));
    }

    @Test
    public void shouldFindEmployeeWithMinSalaryByDepartmentID() {
        when(serviceMock.getEmployees()).thenReturn(fistDepartmentOut);
        assertEquals(minSalary, employeeQuery.minSalaryEmployee(1));
    }

    @Test
    public void shouldTrowWhenNotFindDepartmentEmployeeWithMinSalaryByDepartmentID() {
        when(serviceMock.getEmployees()).thenReturn(fistDepartmentOut);
        assertThrows(NotFoundException.class, () -> employeeQuery.minSalaryEmployee(2));
    }

    @Test
    public void shouldListEmployeeAll() {
        when(serviceMock.getEmployees()).thenReturn(fistDepartmentOut);
        var expented = fistDepartmentOut.entrySet()
                .stream().collect(Collectors.toList());
        assertEquals(expented, employeeQuery.printEmployeesByDepartment(1));
    }

    @Test
    public void shouldPrintAllListSortedDepartment() {
        EmployeeID personD1 = new EmployeeID("Марат", "Ризотов");
        EmployeeID personD2 = new EmployeeID("Барат", "Лизотов");
        HashMap<EmployeeID, Employee> out = new HashMap<EmployeeID, Employee>((Map.of(
                personD1, new Employee(1, 1),
                personD2, new Employee(2, 2)
        )));
        when(serviceMock.getEmployees()).thenReturn(out);
        var expented = out.entrySet().stream()
                .collect(Collectors.groupingBy(iEmployeeManager -> iEmployeeManager.getValue().getDepartment()));
        assertEquals(expented, employeeQuery.printAllEmployeesByDepartment());
    }
}
