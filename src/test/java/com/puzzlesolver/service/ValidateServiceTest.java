package com.puzzlesolver.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.puzzlesolver.exception.InvalidInputException;
import com.puzzlesolver.services.ValidateService;

public class ValidateServiceTest {
    ValidateService validateService = new ValidateService();
    List<String> charList = Arrays.asList("S", "I", "O");
    List<String> dataList = Arrays.asList("SOIS",
            "SIII",
            "SSOO",
            "OIOS",
            "OIIO",
            "ISIO",
            "IOSS",
            "OWSI",
            "OSSO");

    @Test(expected = InvalidInputException.class)
    public void shouldThrowException() throws InvalidInputException {

        validateService.validateData(dataList, 0);
    }

}
