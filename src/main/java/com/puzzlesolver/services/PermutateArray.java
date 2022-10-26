package com.puzzlesolver.services;

import java.util.ArrayList;
import java.util.List;

import com.puzzlesolver.dto.SinglePiece;

public class PermutateArray {

    /**
     * 
     * @param pieces
     * @return
     */
    public List<List<SinglePiece>> permute(List<SinglePiece> pieces) {
        List<List<SinglePiece>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<>(), pieces);
        System.out.println("permuted list" + list.size());
        return list;
    }

    /**
     * 
     * @param list
     * @param resultList
     * @param pieces
     */
    private void permuteHelper(List<List<SinglePiece>> list, List<SinglePiece> resultList, List<SinglePiece> pieces) {

        // Base case
        if (resultList.size() == pieces.size()) {
            list.add(new ArrayList<>(resultList));
        } else {
            for (int i = 0; i < pieces.size(); i++) {

                if (resultList.contains(pieces.get(i))) {
                    // If element already exists in the list then skip
                    continue;
                }
                // Choose element
                resultList.add(pieces.get(i));
                // Explore
                permuteHelper(list, resultList, pieces);
                // Unchoose element
                resultList.remove(resultList.size() - 1);
            }
        }
    }
}
