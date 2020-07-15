import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class FileSelection {

    public static String checkFileCode(int format, String sortBy) throws IOException {
        AtomicReference<String> message = new AtomicReference<>("File Format code not found");
        Files.walk(Paths.get(FileProcessingConstants.USER_HOME_DIRECTORY))
                .filter(Files::isRegularFile)
                .forEach( file -> {
                    try {
                        List<String> empLines = Files.lines(new File(String.valueOf(file)).toPath()).collect(Collectors.toList());
                        String fileFormatCode = empLines.stream().findFirst().get();
                        if (Integer.parseInt(fileFormatCode) == format) {
                            createFileProcess(format, sortBy, empLines);
                            message.set("Successfully Processed");
                            return;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return message.get();
    }

    static FileFormatService createFileProcess(int format, String sortBy, List<String> empDetails) {
        FileFormatService formatService = null;
        switch (format) {
            case 1:
                formatService = new TextFileProcessing(sortBy, empDetails);
                break;

            case 2:
                formatService = new CsvFileProcessing(sortBy, empDetails);
                break;

            default:
                System.out.println("File format code not found");
                break;
        }
        return formatService;
    }
}
