package com.puzzlesolver;

import java.util.List;

import com.puzzlesolver.dto.SinglePiece;
import com.puzzlesolver.exception.InvalidInputException;
import com.puzzlesolver.services.ArrangePiecesService;
import com.puzzlesolver.services.FileRead;
import com.puzzlesolver.services.ValidateService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InvalidInputException {
        FileRead fileRead = new FileRead();
        List<String> inputDataAsList = fileRead.readFile();

        ValidateService validateService = new ValidateService();
        validateService.validateData(inputDataAsList, fileRead.numberOfPieces);

        ArrangePiecesService arrangePieces = new ArrangePiecesService();
        List<SinglePiece> singlePieceObjList = arrangePieces.buildSinglePiece(inputDataAsList);
        arrangePieces.findPossibleSolutions(singlePieceObjList);

    }
}
