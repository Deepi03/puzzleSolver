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
        return list;
    }

    /**
     * 
     * @param list
     * @param resultList
     * @param pieces
     */
    private void permuteHelper(List<List<SinglePiece>> list, List<SinglePiece> resultList, List<SinglePiece> pieces) {

        if (resultList.size() == pieces.size()) {
            list.add(new ArrayList<>(resultList));
        } else {
            for (int i = 0; i < pieces.size(); i++) {

                if (resultList.contains(pieces.get(i))) {
                    continue;
                }
                resultList.add(pieces.get(i));
                permuteHelper(list, resultList, pieces);
                resultList.remove(resultList.size() - 1);
            }
        }
    }
}
