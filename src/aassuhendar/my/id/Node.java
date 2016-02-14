/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aassuhendar.my.id;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aas Suhendar
 */
public class Node {

    List<Node> child = new ArrayList<Node>();
    Node parent;
    double fN; //biaya perhituggan
    double gN; //biaya sesungguhnya
    double hN; //biaya heuristik/perkiraan
    int x;
    int y;
    double cost;
    String nama;

    public Node(String nama, double f, double g, double h, double cost) {
        this.fN = f;
        this.gN = g;
        this.hN = h;
        this.cost = cost;
        this.nama = nama;
    }

    public List<Node> getChild() {
        return child;
    }

    public void setChild(List<Node> child) {
        this.child = child;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
