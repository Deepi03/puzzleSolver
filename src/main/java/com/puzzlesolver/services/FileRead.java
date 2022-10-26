package com.puzzlesolver.services;

import java.io.File;//Import the file class
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

import com.puzzlesolver.exception.InvalidInputException;

public class FileRead {
    public static ArrayList<String> piecesDataList = new ArrayList<String>();
    public int numberOfPieces = 0;

    public List<String> readFile() {
        try {
            File myObj = new File("puzzle.txt");
            Scanner scanner = new Scanner(myObj);
            int lineNum = 1;
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (lineNum == 1) {
                    numberOfPieces = Integer.parseInt(data);
                    lineNum++;
                    continue;
                }
                piecesDataList.add(data);
            }
            scanner.close();

        } catch (NumberFormatException e) {
            System.out.println("Invalid number ::" + e.getLocalizedMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found ::" + e.getLocalizedMessage());
        }
        return piecesDataList;
    }

}
