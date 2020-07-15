import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvFileProcessing implements FileFormatService {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat outDF = new SimpleDateFormat("MM/dd/yyyy");

    public CsvFileProcessing(String sortElement, List<String> employeeDetails) {
        this.processingFile(sortElement, employeeDetails);
    }

    @Override
    public void processingFile(String sortElement, List<String> employeeDetails) {
        try {
            List<Employee> list = new ArrayList<>();
            if (employeeDetails.size() > 1) {
                for (String employee : employeeDetails) {
                    if (employee.length() > 1) {
                        String[] split = employee.split(",");
                        Employee emp = new Employee();
                        emp.setFirstName(split[0]);
                        emp.setLastName(split[1]);
                        emp.setStartDate(convertDateFormat(df.parse(split[2]), outDF));
                        if (!split[3].isEmpty()) {
                            emp.setAddress1(split[3]);
                        }
                        if (!split[4].isEmpty()) {
                            emp.setAddress2(split[4]);
                        }
                        if (!split[5].isEmpty()) {
                            emp.setCity(split[5]);
                        }
                        if (!split[6].isEmpty()) {
                            emp.setState(split[6]);
                        }
                        if (!split[7].isEmpty()) {
                            emp.setCountry(split[7]);
                        }
                        emp.setZipCode(Long.parseLong(split[8]));
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
