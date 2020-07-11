import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDetails {

    public static SimpleDateFormat outDF = new SimpleDateFormat("MM/dd/yyyy");
    public static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    public static String userHomeDirectory = System.getProperty("user.home") + "/Documents/";

    public static void main(String arg[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the File name to get the details of Employee : ");
        String fileName = scan.next();
        System.out.println("Enter the sort element (either by firstName, LastName or Start date) : ");
        String sortBy = scan.next();
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i + 1);
        }
        if (extension.equalsIgnoreCase("csv")) {
            csvFile(fileName, sortBy);
        } else if (extension.equalsIgnoreCase("txt")) {
            txtFile(fileName, sortBy);
        } else {
            System.out.println("Invalid file");
        }
    }

    private static void txtFile(String inputFile, String sortElement) {
        try {
            List<String> employeeLines = Files.lines(new File(userHomeDirectory + inputFile).toPath()).collect(Collectors.toList());
            List<Employee> list = new ArrayList<>();
            if (employeeLines.size() > 0) {
                for (String employee : employeeLines) {
                    Employee emp = new Employee();
                    System.out.println(employee.substring(27, 35).trim());
                    emp.setFirstName(employee.substring(0, 9).trim());
                    emp.setLastName(employee.substring(10, 26).trim());
                    emp.setStartDate(convertDateFormat(df.parse(employee.substring(27, 35))));
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
            sortingEmployeeDetails(list, sortElement);
        } catch (FileNotFoundException file) {
            System.out.println("File Not Found");
        } catch (IOException exp) {
            System.out.println("No Such File Found");
        } catch (ParseException e) {
            System.out.println("Date format is wrong");
        }
    }

    private static void csvFile(String inputFile, String sortElement) {
        try {
            List<String> employeeLines = Files.lines(new File(userHomeDirectory + inputFile).toPath()).collect(Collectors.toList());
            List<Employee> list = new ArrayList<>();
            if (employeeLines.size() > 0) {
                for (String employee : employeeLines) {
                    String[] split = employee.split(",");
                    Employee emp = new Employee();
                    emp.setFirstName(split[0]);
                    emp.setLastName(split[1]);
                    emp.setStartDate(convertDateFormat(df.parse(split[2])));
                    emp.setAddress1(split[3]);
                    emp.setAddress2(split[4]);
                    emp.setCity(split[5]);
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
            sortingEmployeeDetails(list, sortElement);
        } catch (FileNotFoundException file) {
            System.out.println("File Not Found");
        } catch (IOException exp) {
            System.out.println("No Such File Found");
        } catch (ParseException e) {
            System.out.println("Date format is wrong");
        }
    }

    private static Date convertDateFormat(Date startDate) throws ParseException {
        String sDate = outDF.format(startDate);
        Date date = outDF.parse(sDate);
        return date;
    }

    private static void sortingEmployeeDetails(List<Employee> employees, String sortElement) {
        List<Employee> sortedEmp = new ArrayList<>();
        if (sortElement.contains("name")) {
            Collections.sort(employees, Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName));
        } else if (sortElement.contains("date")) {
            Collections.sort(employees, Comparator.comparing(Employee::getStartDate));
        } else {
            Collections.sort(employees, Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName));
        }
        generateOutputFile(employees);
    }

    private static void generateOutputFile(List<Employee> employees) {
        try {

            FileWriter fw = new FileWriter(userHomeDirectory + "MyOutFile.txt");
            String str = "";
            int count = 1;
            for (Employee emp : employees) {
                str = count++ + "\n";
                str += str.join(" ", emp.getFirstName(), emp.getLastName());
                str += "(" + outDF.format(emp.getStartDate()) + ")\n";
                str += str.join(",", emp.getAddress1(), emp.getAddress2()) + "\n";
                str += str.join(",", emp.getCity(), emp.getState()) + "\n";
                str += str.join(",", emp.getCountry(), emp.getZipCode().toString()) + "\n";
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
}
