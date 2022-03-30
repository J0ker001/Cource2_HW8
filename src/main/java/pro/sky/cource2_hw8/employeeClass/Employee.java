package pro.sky.cource2_hw8.employeeClass;

import java.util.Objects;

public class Employee {

    private double salary;
    private Integer department;

    public Employee(double salary, int department) {
        this.salary = salary;
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 && department == employee.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, department);
    }

    @Override
    public String toString() {
        return "Зарпалата: " + salary +
                ", Отдел: " + department;
    }
}
