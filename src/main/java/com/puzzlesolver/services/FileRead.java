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
    List<String> charList = Arrays.asList("S", "I", "O");

    private int numberOfPieces;

    public void readFile() throws InvalidInputException {
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
                if (validateData(data)) {
                    piecesDataList.add(data);
                } else {
                    throw new InvalidInputException("Invalid Input");
                }
            }
            System.out.println(piecesDataList);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number ::" + e.getLocalizedMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found ::" + e.getLocalizedMessage());
        } finally {
            System.out.println("i am finally");
        }
    }

    public boolean validateData(String data) throws InvalidInputException {

        return data.length() == 4 && charList.contains(Character.toString(data.charAt(0)))
                && charList.contains(Character.toString(data.charAt(1)))
                && charList.contains(Character.toString(data.charAt(2)));
        /* piecesDataList.add(data); */

    }
}
