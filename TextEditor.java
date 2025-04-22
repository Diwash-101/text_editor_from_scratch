import java.util.*;
import java.io.*;

class TextEditor {
    public static void showOutput(List<String> buffer) {
                StringBuilder output = new StringBuilder();
        for (String line : buffer) {
            if (line.equals("EOF")) { 
                break; // Stop processing if "/n" is encountered
            } else {
                output.append(line).append("\n");
            }
        }

        System.out.print(output);
    }
    public static void replaceLine(List<String> buffer, int editLine, String editText) {
        // Check if the line number is valid
        if (editLine < 0 || editLine >= buffer.size()) {
            System.out.println("Invalid line number.");
            return;
        }
        // Replace the specified line with the new text
    buffer.set(editLine, editText); // Update the buffer with the new text
    }
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
            if (input.equals(":q")) // Exit command
            {
                break;
            } else if(input.equals(":w")) // To write to a file
            {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                    for (String line : buffer) {
                        writer.write(line);
                        //writer.newLine();
                    }
                    writer.write("EOF"); // Indicate end of file
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
                
            } else if (input.equals(":r")) // To read from a file
            {
                try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
                    String line;
                    buffer.clear(); // Clear the buffer before reading
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("EOF")) { 
                            break; // Stop processing if "EOF" is encountered
                        }
                        buffer.add(line);
                    }
                    showOutput(buffer); // Display the buffer contents after reading
                } catch (IOException e) {
                    System.out.println("Error reading from file: " + e.getMessage());
                }
            } else if (input.equals(":e")) // To edit a specific line in the buffer
            {
                System.out.println("Enter the line number to edit (1 to " + buffer.size() + ") and the text to replace with: ");
                int editLine = scanner.nextInt() - 1; 
                System.out.println("Enter the new text: ");
                String editText = scanner.next(); // Read the new text
                replaceLine(buffer, editLine, editText); // Update the buffer with the new text
                showOutput(buffer);
            } else if(input.equals(":dd")) // To delete a specific line from the buffer
            {
                System.out.println("Enter the line number to delete (1 to " + buffer.size()+ "): ");
                int deleteLine = scanner.nextInt() - 1; 
                if (deleteLine >= 0 && deleteLine < buffer.size()) {
                    buffer.remove(deleteLine); // Remove the specified line from the buffer
                } else {
                    System.out.println("Invalid line number.");
                }
            } else if(input.equals(":c")) // To clear the buffer
            {
                buffer.clear(); // Clear the buffer
            } else if(input.equals(":d")) // To display the buffer
            {
                showOutput(buffer); 
            } else if(input.equals(":h")) // for help
            {
                System.out.println("Commands: :q - quit, :w - write, :r - read, :e - edit, :dd - delete, :c - clear, :h - help");
            }
            else {
                buffer.add(input);
            }

            
        }

        showOutput(buffer); // Display the buffer contents

        // Closing the scanner to release resources
        scanner.close();
    }}
