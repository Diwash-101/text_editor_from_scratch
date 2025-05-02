### 4/15/2025

    Basic input output

### 4/16/2025

    Multi line input is stored in a List buffer where each line is a new buffer and a new item in the list. The output is then parsed using the string builder class.

### 4/17/2025

    Clear terminal before running anything

### 4/18/2025

    Implementend the basic read and write functionality to a file using file writer and buffered writer which is apparantly better in processing long files

### 4/21/2025

    Implemented the ablity to edit lines. I can now specify the line number and replace the line. No delete yet.

### 4/22/2025

    Implemented the ability to delete lines. A line now can be removed instead of just being replaced by empty text.

### 4/23/2025

    Implemented character by character input instead of line by line for backspace and more granular editing in the future.

### 5/2/2025

    Fixed the bug where entering a command added extra lines to the buffer. This happened when entering a command because the main loop would immediately read the enter key used to submit the command as a new input and add it to the buffer creating unwanted empty strings in the buffer. It took a few day on and off but we got there.

# BUGS

    - editing adds extra /n
    - :d command doesn't work (the display function works)
