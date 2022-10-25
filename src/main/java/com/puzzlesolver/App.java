package com.puzzlesolver;

import com.puzzlesolver.exception.InvalidInputException;
import com.puzzlesolver.services.ArrangePiecesService;
import com.puzzlesolver.services.FileRead;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InvalidInputException {
        FileRead fileRead = new FileRead();
        fileRead.readFile();

        ArrangePiecesService arrangePieces = new ArrangePiecesService();
        arrangePieces.separatePiece();
        arrangePieces.getPossibleFirstRowPieces();
        arrangePieces.addRestOfPieces();
    }
}
