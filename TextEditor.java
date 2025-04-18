import java.util.*;
import java.io.*;

class TextEditor {
       public static void main(String[] args)
    {
        try {
         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Clear the console (Windows)
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing console: " + e.getMessage());
        }
        List<String> buffer = new ArrayList<String>();

        // Creating Scanner class object
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(":q")) {
                break;
            } else if(input.equals(":w")) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                    for (String line : buffer) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
            } else if (input.equals(":r")) {
                try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
                    String line;
                    buffer.clear(); // Clear the buffer before reading
                    while ((line = reader.readLine()) != null) {
                        buffer.add(line);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading from file: " + e.getMessage());
                }
            } 
            else {
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
