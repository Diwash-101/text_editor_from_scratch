    import java.util.*;
    import java.io.*;

    class TextEditor {
        public static void clearConsole() {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Clear the console (Windows)
            } catch (IOException | InterruptedException e) {
                System.out.println("Error clearing console: " + e.getMessage());
            }
        }
        public static String charWiseInput() {
            StringBuilder line = new StringBuilder();
            try{
            int ch;
            while((ch = System.in.read()) != -1) {
                if(ch == '\n' || ch == '\r') { 
                    break; // Stop reading on newline character
                }
                line.append((char)ch); 
            }}
            catch(IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
            return line.toString(); // Return the read line as a string
        }
        public static void showOutput(List<String> buffer) {
            for (String line : buffer) {
                System.out.print(line); // Append each line to the output
            }

        }
        public static void replaceLine(List<String> buffer, int editLine, String editText) {
            // Check if the line number is valid
            if (editLine < 0 || editLine >= buffer.size()) {
                System.out.print("Invalid line number.\n");
                return;
            }
            // Replace the specified line with the new text
        buffer.set(editLine, editText); // Update the buffer with the new text
        }
        public static void saveToFile(List<String> buffer) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                for (String line : buffer) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
        public static void readFromFile(List<String> buffer) {
            try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
                String line;
                buffer.clear(); // Clear the buffer before reading
                while ((line = reader.readLine()) != null) {
                    buffer.add(line+"\n"); 
                }
                showOutput(buffer);
            } catch (IOException e) {
                System.out.println("Error reading from file: " + e.getMessage());
            }
        }
        public static void writeToFile(List<String> buffer) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                for (String line : buffer) {
                    writer.write(line + "\n"); // Write each line to the file
                    writer.newLine();
                }
                
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
        public static void deleteLine(List<String> buffer, int deleteLine) {
            // Check if the line number is valid
            if (deleteLine < 0 || deleteLine >= buffer.size()) {
                System.out.print("Invalid line number.");
                return;
            }
            // Remove the specified line from the buffer
            buffer.remove(deleteLine); 
        }
        public static void clearBuffer(List<String> buffer) {
            // Clear the buffer
            buffer.clear(); 
        }
        public static void processCommand(String input, List<String> buffer) {
            if(input.equals(":w")) 
                {
                    writeToFile(buffer);
                } 
                else if (input.equals(":r")) // To read from a file
                {
                    readFromFile(buffer);
                } else if (input.equals(":e")) // To edit a specific line in the buffer
                {
                    System.out.print("Enter the line number to edit (1 to " + buffer.size()+ "): ");
                    String editLineInput = charWiseInput(); // Convert to zero-based index
                    int editLine;
                    try {
                        editLine = Integer.parseInt(editLineInput) - 1; // Convert to zero-based index
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid line number. Please enter a valid number.");
                        return;// Skip to the next iteration of the loop
                    } 
                    System.out.print("Enter the new text for line " + (editLine + 1) + ": ");
                    String editText = charWiseInput(); 
                    replaceLine(buffer, editLine, editText);
                } else if(input.equals(":dd")) // To delete a specific line from the buffer
                {
                    System.out.print("Enter the line number to delete (1 to " + buffer.size()+ "): ");
                    int deleteLine;
                    try {
                        deleteLine = Integer.parseInt(charWiseInput()) - 1; // Convert to zero-based index
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid line number. Please enter a valid number.");
                        return; // Skip to the next iteration of the loop
                    }
                    deleteLine(buffer, deleteLine);
                } else if(input.equals(":c")) // To clear the buffer
                {
                   clearBuffer(buffer);
                } else if(input.equals(":d")) // To display the buffer
                {
                    showOutput(buffer);
                } else if(input.equals(":h")) // for help
                {
                    System.out.print("Commands: :q - quit, :w - write, :r - read, :e - edit, :dd - delete, :c - clear, :h - help");
                }
        }
        public static void main(String[] args)
        {
            clearConsole();
    List<String> buffer = new ArrayList<>();
    boolean skipNextEmptyInput = false; // required to skip enter pressed after command

    while (true) {
        String input = charWiseInput();

        if (skipNextEmptyInput && input.equals("")) {
            skipNextEmptyInput = false; // reset
            continue; // skip only this enter
        }

        if (input.startsWith(":")) {
            if (input.equals(":q")) {
                break;
            } else {
                processCommand(input, buffer);
                skipNextEmptyInput = true; // set flag to skip next ""
            }
            continue;
        }

        // Add actual input (including user-entered blank lines)
        buffer.add(input);
    }

    showOutput(buffer);
    System.out.println(buffer);
        }}
