package com.puzzlesolver.services;

import java.util.Arrays;
import java.util.List;

import com.puzzlesolver.exception.InvalidInputException;

public class ValidateService {
    List<String> charList = Arrays.asList("S", "I", "O");

    /**
     * 
     * @param dataList
     * @param numOfDataLines
     * @throws InvalidInputException
     */
    public void validateData(List<String> dataList, int numOfDataLines) throws InvalidInputException {
        dataList.stream()
                .forEach(d -> {
                    try {
                        if (!validate(d)) {
                            throw new InvalidInputException("Validation failed..Please check the input");
                        }
                    } catch (InvalidInputException e) {
                        System.out.println("Invalid input");
                        System.exit(0);
                    }
                });
        if (!(dataList.size() == numOfDataLines)) {
            throw new InvalidInputException("Number of data doesnt match with the count");
        }
    }

    /**
     * 
     * @param data
     * @return
     */

    private boolean validate(String data) {
        return data.length() == 4 && charList.contains(Character.toString(data.charAt(0)))
                && charList.contains(Character.toString(data.charAt(1)))
                && charList.contains(Character.toString(data.charAt(2)))
                && charList.contains(Character.toString(data.charAt(3)));
    }

}
