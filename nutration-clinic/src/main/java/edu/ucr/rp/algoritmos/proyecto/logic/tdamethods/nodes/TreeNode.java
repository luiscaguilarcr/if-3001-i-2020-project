package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.nodes;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;

/**
 * @author Luis Carlos
 */
public class TreeNode {
    public CustomerDate customerDate;
    public HistoryApp historyApp;
    public TreeNode left, right;
    //BTree
    public String label; //root/left/right
    public int level; //0(root), 1, 2, 3, ....
    //AVL-BST
    public String sequence; //rotation sequence

    public TreeNode() {
    }

    //Constructor #1
    public TreeNode(CustomerDate customerDate) {
        this.customerDate = customerDate;
        this.left = this.right = null;
    }

    //Contructor sobrecargado #2
    public TreeNode(CustomerDate customerDate, String label, int level) {
        this.customerDate = customerDate;
        this.label = label;
        this.level = level;
        this.left = this.right = null;
    }

    //Constructor #3
    public TreeNode(HistoryApp historyApp, String sequence) {
        this.historyApp = historyApp;
        this.sequence = sequence;
        this.left = this.right = null;
    }

    public CustomerDate getCustomerDate() {
        return customerDate;
    }

    public void setCustomerDate(CustomerDate customerDate) {
        this.customerDate = customerDate;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

}
