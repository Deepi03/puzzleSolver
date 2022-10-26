package com.puzzlesolver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.puzzlesolver.dto.SinglePiece;
import com.puzzlesolver.exception.InvalidInputException;

public class ArrangePiecesService {
    PermutateArray pa = new PermutateArray();
    int matchCount = 0;
    private int noOfColumns;
    private int noOfRows;
    List<SinglePiece> possibleFirstRowPieces = new ArrayList<>();

    /**
     * 
     * @param inputDataAsList
     * @return
     * @throws InvalidInputException
     */
    public List<SinglePiece> buildSinglePiece(List<String> inputDataAsList) throws InvalidInputException {
        List<SinglePiece> allPiecesObjList = new ArrayList<>();
        inputDataAsList.stream().forEach((pieceData) -> {
            allPiecesObjList.add(
                    new SinglePiece(Character.toString(pieceData.charAt(0)), Character.toString(pieceData.charAt(1)),
                            Character.toString(pieceData.charAt(2)), Character.toString(pieceData.charAt(3))));
        });
        return allPiecesObjList;
    }

    /**
     * 
     * @param singlePieceList
     */
    public void findPossibleSolutions(List<SinglePiece> singlePieceList) {
        List<List<SinglePiece>> permutationList = pa.permute(singlePieceList);
        permutationList.stream().forEach(eachList -> {
            if (arrange(eachList)) {
                matchCount++;
            }
        });
        System.out.println("Possible unique solution ::" + matchCount);
    }

    /**
     * 
     * @param permutationResultList
     */
    public void getPossibleFirstRowPieces(List<SinglePiece> permutationResultList) {
        permutationResultList.stream().forEach(p -> {
            if (p.getTop().equals("S")) {
                possibleFirstRowPieces.add(p);
            }
        });
        noOfColumns = possibleFirstRowPieces.size();
        noOfRows = permutationResultList.size() / noOfColumns;
    }

    /**
     * 
     * @param permutationResultList
     * @return
     */
    public boolean arrange(List<SinglePiece> permutationResultList) {
        getPossibleFirstRowPieces(permutationResultList);
        try {
            SinglePiece[][] val = new SinglePiece[noOfRows][noOfColumns];
            SinglePiece firstPiece = possibleFirstRowPieces.stream()
                    .filter(piece -> piece.getTop().equals("S") && piece.getLeft().equals("S")).findFirst().get();

            for (int i = 0; i < noOfRows; i++) {
                for (int j = 0; j < noOfColumns; j++) {
                    // Arrange first row pieces
                    if (i == 0 && j == 0) {
                        val[i][j] = firstPiece;
                        permutationResultList.remove(val[i][j]);
                        continue;
                    } else if (i == 0 && j > 0) {
                        // first row i = 0, j = 1,2,3,4...
                        String expectedTop = expectedShape(val[i][j - 1].getTop());
                        String expectedLeft = expectedShape(val[i][j - 1].getRight());
                        val[i][j] = getNextPiece(permutationResultList, expectedTop, expectedLeft);
                        permutationResultList.remove(val[i][j]);
                        continue;
                    } else if (i < noOfRows - 1) {
                        // first column of center rows i = 1,2,.. j = 0
                        if (j == 0) {
                            val[i][j] = getNextPiece(
                                    permutationResultList, expectedShape(val[i - 1][j]
                                            .getBottom()),
                                    "S");
                            permutationResultList.remove(val[i][j]);
                            continue;
                        } else {
                            // second row i = 1, j=1,2,3
                            val[i][j] = getNextPiece(
                                    permutationResultList,
                                    expectedShape(val[i - 1][j].getBottom()),
                                    expectedShape(val[i][j - 1].getRight()));
                            permutationResultList.remove(val[i][j]);
                            continue;

                        }
                    } else if (i == (noOfRows - 1)) {
                        // i = 3(last row) ,j = 0
                        if (j == 0) {
                            val[i][j] = getNextPiece(
                                    permutationResultList, expectedShape(val[i - 1][j].getBottom()),
                                    "S");
                            permutationResultList.remove(val[i][j]);
                            continue;
                        } else {
                            // i = 3 (last row) , j = 1,2,3,4...
                            val[i][j] = getNextPiece(
                                    permutationResultList, expectedShape(val[i - 1][j].getBottom()),
                                    expectedShape(val[i][j - 1].getRight()));
                            permutationResultList.remove(val[i][j]);
                            continue;
                        }
                    }
                }
            }
            return permutationResultList.size() == 0;
        } catch (NoSuchElementException e) {
            return false;
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
     * @param permutationResultList
     * @param expectedTopShape
     * @param expectedLeftShape
     * @return
     */
    private static SinglePiece getNextPiece(List<SinglePiece> permutationResultList, String expectedTopShape,
            String expectedLeftShape) {
        return permutationResultList.stream()
                .filter(p -> p.getTop().equals(expectedTopShape) && p.getLeft().equals(expectedLeftShape)).findFirst()
                .get();
    }

}
