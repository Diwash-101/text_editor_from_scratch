import java.util.*;
import java.io.*;

class TextEditor {

       public static void main(String[] args)
    {
        List<String> buffer = new ArrayList<String>();

        // Creating Scanner class object
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(":q")) {
                break;
            } else {
                buffer.add(input);
            }

            
        }
        StringBuilder output = new StringBuilder();
        for (String line : buffer) {
            output.append(line).append("\n");
        }

        System.out.print(output);

        // Closing the scanner to release resources
        scanner.close();
    }}
