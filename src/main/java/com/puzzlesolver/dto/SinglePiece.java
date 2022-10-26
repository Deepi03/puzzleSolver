package com.puzzlesolver.dto;

public class SinglePiece {
    private String top;
    private String right;
    private String bottom;
    private String left;

    public SinglePiece() {
    }

    /**
     * 
     * @param top
     * @param right
     * @param bottom
     * @param left
     */

    public SinglePiece(String top, String right, String bottom, String left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public String getRight() {
        return right;
    }

    public String getBottom() {
        return bottom;
    }

    public String getLeft() {
        return left;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return "Piece [top=" + top + ", right=" + right + ", bottom=" + bottom + ", left=" + left + " ]";
    }

}
