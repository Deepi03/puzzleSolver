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

    public void arrange() {
        SinglePiece[][] val = new SinglePiece[noOfRows][noOfColumns];
        SinglePiece firstPiece = possibleFirstRowPieces.stream()
                .filter(piece -> piece.getTop().equals("S") && piece.getLeft().equals("S")).findFirst().get();
        allPiecesObjList.remove(firstPiece);

        for (int i = 0; i < noOfRows; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                // Arrange first row pieces
                if (i == 0 && j == 0) {
                    val[i][j] = firstPiece;
                    System.out.println("foundPiece :: **** " + val[i][j] + " i :: " + i + " j :: " + j);
                    continue;
                } else if (i == 0 && j > 0) {
                    // first row i = 0, j = 1,2,3,4...
                    String expectedTop = expectedShape(val[i][j - 1].getTop());
                    String expectedLeft = expectedShape(val[i][j - 1].getRight());
                    val[i][j] = getNextPiece(allPiecesObjList, expectedTop, expectedLeft);
                    System.out.println("foundPiece :: **** " + val[i][j] + " i :: " + i + " j :: " + j);
                    allPiecesObjList.remove(val[i][j]);
                    continue;
                } else if (i < noOfRows - 1) {
                    // first column of center rows i = 1,2,.. j = 0
                    if (j == 0) {
                        val[i][j] = getNextPiece(allPiecesObjList, expectedShape(val[i - 1][j]
                                .getBottom()),
                                "S");
                        System.out.println("foundPiece ** :: " + val[i][j] + " i :: " + i + " j:: " + j);
                        allPiecesObjList.remove(val[i][j]);
                        continue;
                    } else {
                        // second row i = 1, j=1,2,3
                        val[i][j] = getNextPiece(allPiecesObjList,
                                expectedShape(val[i - 1][j].getBottom()),
                                expectedShape(val[i][j - 1].getRight()));
                        allPiecesObjList.remove(val[i][j]);
                        System.out.println("foundPiece ** :: ****" + val[i][j] + " i :: " + i + " j::" + j);
                        continue;

                    }
                } else if (i == (noOfRows - 1)) {
                    // i = 3(last row) ,j = 0
                    if (j == 0) {
                        val[i][j] = getNextPiece(allPiecesObjList, expectedShape(val[i - 1][j].getBottom()),
                                "S");
                        allPiecesObjList.remove(val[i][j]);
                        System.out.println("foundPiece ** :: ****" + val[i][j] + " i :: " + i + " j::" + j);
                        continue;
                    } else {
                        // i = 3 (last row) , j = 1,2,3,4...
                        val[i][j] = getNextPiece(allPiecesObjList, expectedShape(val[i - 1][j].getBottom()),
                                expectedShape(val[i][j - 1].getRight()));
                        allPiecesObjList.remove(val[i][j]);
                        System.out.println("foundPiece ** :: ****" + val[i][j] + " i :: " + i + " j::" + j);
                        continue;
                    }
                }
            }
        }
    }

    /**
     * 
     * @param input
     * @return
     */

    private static String expectedShape(String input) {

        return input.equals("S") ? "S" : (input.equals("O") ? "I" : "O");
    }

    /**
     * 
     * @param allPiecesObjList
     * @param expectedTopShape
     * @param expectedLeftShape
     * @return
     */

    private static SinglePiece getNextPiece(List<SinglePiece> allPiecesObjList, String expectedTopShape,
            String expectedLeftShape) {
        return allPiecesObjList.stream()
                .filter(p -> p.getTop().equals(expectedTopShape) && p.getLeft().equals(expectedLeftShape)).findFirst()
                .get();
    }

}
