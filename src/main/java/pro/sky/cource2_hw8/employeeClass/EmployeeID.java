package pro.sky.cource2_hw8.employeeClass;

import org.apache.commons.lang3.StringUtils;
import java.util.Objects;

public class EmployeeID {

    private final String fistName;
    private final String lastName;

    public EmployeeID(String fistName, String lastName) {
        this.fistName = StringUtils.capitalize(fistName);
        this.lastName = StringUtils.capitalize(lastName);
    }

    public String getFistName() {
        return fistName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeID that = (EmployeeID) o;
        return Objects.equals(fistName, that.fistName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistName, lastName);
    }

    @Override
    public String toString() { return fistName + " " + lastName + " ";}
}
