import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public interface FileFormatService {

    void processingFile(String sortElement, List<String> employeeDetails);

    default void generateOutputFile(List<Employee> employees) {
        try {
            FileWriter fw = new FileWriter(FileProcessingConstants.USER_HOME_DIRECTORY + "/MyOutFile.txt");
            String str = "";
            int count = 1;
            for (Employee emp : employees) {
                str = count++ + "\n";
                str += emp.toString();
                System.out.println(str);
                fw.write(str);
            }
            fw.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error while writing in the file. or File location not found.");
        }
    }

    default Date convertDateFormat(Date startDate, SimpleDateFormat df) throws ParseException {
        String sDate = df.format(startDate);
        Date date = df.parse(sDate);
        return date;
    }

    default void sortingEmployeeDetails(List<Employee> employees, String sortElement) {
        if (sortElement.contains("name")) {
            Collections.sort(employees, Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName));
        } else if (sortElement.contains("date")) {
            Collections.sort(employees, Comparator.comparing(Employee::getStartDate));
        } else {
            Collections.sort(employees, Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName));
        }
        generateOutputFile(employees);
    }

}
