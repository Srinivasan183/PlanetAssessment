import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TextFileProcessing implements FileFormatService {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat outDF = new SimpleDateFormat("MM/dd/yyyy");

    public TextFileProcessing(String sortElement, List<String> employeeDetails) {
        this.processingFile(sortElement, employeeDetails);
    }

    @Override
    public void processingFile(String sortElement, List<String> employeeDetails) {
        try {
            List<Employee> list = new ArrayList<>();
            if (employeeDetails.size() > 1) {
                for (String employee : employeeDetails) {
                    if (employee.length() > 1) {
                        Employee emp = new Employee();
                        emp.setFirstName(employee.substring(0, 9).trim());
                        emp.setLastName(employee.substring(10, 26).trim());
                        emp.setStartDate(convertDateFormat(df.parse(employee.substring(27, 35)), outDF));
                        emp.setAddress1(employee.substring(35, 44).trim());
                        emp.setAddress2(employee.substring(45, 54).trim());
                        emp.setCity(employee.substring(55, 64).trim());
                        if (!employee.substring(65, 67).trim().isEmpty()) {
                            emp.setState(employee.substring(65, 67).trim());
                        }
                        if (!employee.substring(67, 69).trim().isEmpty()) {
                            emp.setCountry(employee.substring(67, 69).trim());
                        }
                        emp.setZipCode(Long.parseLong(employee.substring(70).trim()));
                        list.add(emp);
                    }
                }
            }
            sortingEmployeeDetails(list, sortElement);
        } catch (ParseException dateParse) {
            System.out.println("Cannot parse the date");
        }
    }
}
