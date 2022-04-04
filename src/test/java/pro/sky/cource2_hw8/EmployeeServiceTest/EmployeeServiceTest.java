package pro.sky.cource2_hw8.EmployeeServiceTest;

import org.junit.jupiter.api.Test;
import pro.sky.cource2_hw8.Exception.AlreadyAddedException;
import pro.sky.cource2_hw8.Exception.BadRequestException;
import pro.sky.cource2_hw8.Exception.NotFoundException;
import pro.sky.cource2_hw8.Interface.EmployeeService;
import pro.sky.cource2_hw8.employeeClass.Employee;
import pro.sky.cource2_hw8.employeeClass.EmployeeID;
import pro.sky.cource2_hw8.service.MapStorageEmployeeService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new MapStorageEmployeeService();

    //add
    @Test
    public void shouldNotReturnAnyEmployeeWhenNoEmployeeAdded() {
        //given
        EmployeeID person = new EmployeeID("Иван", "Соколов");
        //when
        //Then
        assertThrows(NotFoundException.class, () -> employeeService.findEmployee(person));
    }

    @Test
    public void shouldAddNewEmployee() {
        // given
        EmployeeID person = new EmployeeID("Иван", "Соколов");
        Employee employee = new Employee(1, 7);
        // when
        employeeService.addEmployee(person, employee);
        // then
        assertEquals(employee, employeeService.findEmployee(person));
    }

    @Test
    public void shouldThrowWhenAddedTwice() {
        //given
        EmployeeID person = new EmployeeID("Иван", "Соколов");
        Employee employee = new Employee(1, 7);
        employeeService.addEmployee(person, employee);
        //when
        //then
        assertThrows(AlreadyAddedException.class, () -> employeeService.addEmployee(person, employee));
    }

    @Test
    public void shouldThrowWhenAddedIncorrectData() {
        //given
        EmployeeID person = new EmployeeID("1", "Соколов");
        Employee employee = new Employee(1, 7);
        //when
        //then
        assertThrows(BadRequestException.class, () -> employeeService.addEmployee(person, employee));
    }

    //Find

    @Test
    public void shouldThrowWhenNotFindEmployee() {
        EmployeeID person = new EmployeeID("Ивkан", "Сухин");
        assertThrows(NotFoundException.class, () -> employeeService.findEmployee(person));
    }

    //Remove

    @Test
    public void shouldRemoveEmployee() {
        //given
        EmployeeID person = new EmployeeID("Алексей", "Сухин");
        Employee employee = new Employee(1, 3);
        //when
        employeeService.addEmployee(person, employee);
        employeeService.removeEmployee(person);
        //then
        assertThrows(NotFoundException.class, () -> employeeService.findEmployee(person));
    }

    @Test
    public void shouldThrowWhenRemoveNonExisted() {
        EmployeeID person = new EmployeeID("Ив1ан", "Сухин");
        assertThrows(NotFoundException.class, () -> employeeService.removeEmployee(person));
    }

    //Get

    @Test
    public void shouldAllEmployeeList() {
        //give
        EmployeeID person = new EmployeeID("Алексей", "Сухин");
        Employee employee = new Employee(1, 3);
        employeeService.addEmployee(person, employee);
        //When
        var actual = employeeService.getEmployees();
        //Then
        var expect = new HashMap<>(Map.of(person, employee));
        assertEquals(expect, actual);
    }

    @Test
    public void shouldReturnEmptyMap() {
        var actual = employeeService.getEmployees();
        assertTrue(actual.isEmpty());
    }
//Check

    @Test
    public void shouldThrowWhenNotString() {
        String fistName = "Ива1н";
        String lastName = "Соколов";
        assertThrows(BadRequestException.class, () -> employeeService.сheckingAndCreatingEmployeeID(fistName, lastName));
    }
}

