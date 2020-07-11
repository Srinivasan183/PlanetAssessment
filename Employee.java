import java.util.Date;
import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    private Date startDate;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private Long zipCode;

    public Employee() {
        this.state = "CA";
        this.country = "USA";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", startDate=" + startDate +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(startDate, employee.startDate) &&
                Objects.equals(address1, employee.address1) &&
                Objects.equals(address2, employee.address2) &&
                Objects.equals(city, employee.city) &&
                Objects.equals(state, employee.state) &&
                Objects.equals(country, employee.country) &&
                Objects.equals(zipCode, employee.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, startDate, address1, address2, city, state, country, zipCode);
    }
}
