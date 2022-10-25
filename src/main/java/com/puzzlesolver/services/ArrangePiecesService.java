package com.puzzlesolver.services;

import java.util.ArrayList;
import java.util.List;

import com.puzzlesolver.dto.SinglePiece;
import com.puzzlesolver.exception.InvalidInputException;

public class ArrangePiecesService {
    private List<SinglePiece> allPiecesObjList = new ArrayList<>();
    private List<SinglePiece> possibleFirstRowPieces = new ArrayList<>();
    private List<SinglePiece> possibleRestOfPieces = new ArrayList<>();

    private int noOfColumns;
    private int noOfRows;

    public void separatePiece() throws InvalidInputException {
        ArrayList<String> piecesDataList = FileRead.piecesDataList;
        System.out.println("piece data list " + piecesDataList.size());
        piecesDataList.stream().forEach((pieceData) -> {
            allPiecesObjList.add(
                    new SinglePiece(Character.toString(pieceData.charAt(0)), Character.toString(pieceData.charAt(1)),
                            Character.toString(pieceData.charAt(2)), Character.toString(pieceData.charAt(3))));
        });
        System.out.println("first char " + allPiecesObjList.size());

    }

    /**
     * Get all possible first row pieces
     */
    public void getPossibleFirstRowPieces() {
        allPiecesObjList.stream().forEach(p -> {
            if (p.getTop().equals("S")) {
                possibleFirstRowPieces.add(p);
            }
        });
        noOfColumns = possibleFirstRowPieces.size();
        noOfRows = allPiecesObjList.size() / noOfColumns;
    }

    /**
     * Rest of the rows
     */
    public void addRestOfPieces() {
        possibleRestOfPieces.addAll(allPiecesObjList);
        possibleRestOfPieces.removeAll(possibleFirstRowPieces);
        System.out.println("### possible rest of pieces " + possibleRestOfPieces.size());

    }
}
