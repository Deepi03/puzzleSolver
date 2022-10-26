package com.puzzlesolver.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.puzzlesolver.dto.SinglePiece;
import com.puzzlesolver.exception.InvalidInputException;
import com.puzzlesolver.services.ArrangePiecesService;

public class ArrangePiecesServiceTest {

    ArrangePiecesService as = new ArrangePiecesService();

    public static List<SinglePiece> allPiecesObjList = new ArrayList<>();

    public void separatePiece() throws InvalidInputException {

        List<String> inputs = Arrays.asList("SOIS",
                "SIII",
                "SSOO",
                "OIOS",
                "OIIO",
                "ISIO",
                "IOSS",
                "OISI",
                "OSSO");
        inputs.stream().forEach((pieceData) -> {
            allPiecesObjList.add(
                    new SinglePiece(Character.toString(pieceData.charAt(0)), Character.toString(pieceData.charAt(1)),
                            Character.toString(pieceData.charAt(2)), Character.toString(pieceData.charAt(3))));
        });
    }

    /**
     * 
     * @throws InvalidInputException
     */
    @Test
    public void testValidateArrange() throws InvalidInputException {
        separatePiece();
        assertEquals(true, as.arrange(allPiecesObjList));
    }
}
