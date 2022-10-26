package com.puzzlesolver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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

    @Test
    public void shouldThrowException() {
        Assertions.assertThrows(InvalidInputException.class, () -> {
            validateService.validateData(dataList, 0);
        });
    }

}
