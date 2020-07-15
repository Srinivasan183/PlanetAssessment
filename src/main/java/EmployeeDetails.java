import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeDetails {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Input file format in number(Integer) : ");
        int fileFormat = 0;
        try {
          fileFormat = scan.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid Integer");
        }
        System.out.println("Enter the sort element (either by firstName, LastName or Start date) : ");
        String sortBy = scan.next();
        try {
            String process = FileSelection.checkFileCode(fileFormat, sortBy);
            System.out.println(process);
        } catch (IOException e) {
            System.out.println(" Exception while getting the files");
        }

    }
}
