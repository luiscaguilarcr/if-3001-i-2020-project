package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.nodes;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;

public class AVLTreeNode {
    public HistoryApp historyApp;

    public AVLTreeNode left, right;

    //AVL-BST
    public String sequence; //rotation sequence

    public AVLTreeNode() {
    }

    public AVLTreeNode(HistoryApp historyApp, String sequence) {
        this.historyApp = historyApp;
        this.sequence = sequence;
        this.left = this.right = null;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public AVLTreeNode setLeft(AVLTreeNode left) {
        this.left = left;
        return this;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public AVLTreeNode setRight(AVLTreeNode right) {
        this.right = right;
        return this;
    }

    public String getSequence() {
        return sequence;
    }

    public AVLTreeNode setSequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public HistoryApp getHistoryApp() {
        return historyApp;
    }

    public AVLTreeNode setHistoryApp(HistoryApp historyApp) {
        this.historyApp = historyApp;
        return this;
    }
}
