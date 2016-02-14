/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aassuhendar.my.id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Aas Suhendar
 */
public class AStar {

    public List<Node> aStarSearch(Node start, Node goal) {

        Set<Node> open = new HashSet<Node>();
        Set<Node> closed = new HashSet<Node>();

        List<Node> nodes = new ArrayList<Node>();
        start.gN = 0;
        start.fN = start.hN;

        open.add(start);
        boolean found = false;
        int iterasi = 0;
        while (found == false) {
            Node bestNode = null;
            if (open.isEmpty()) {
                throw new RuntimeException("no route");
            }

            for (Node node : open) {
                if (bestNode == null || node.fN < bestNode.fN) {
                    bestNode = node;
                }
            }

            if (bestNode == goal) {
                found = true;
            }

            System.out.println("");
            System.out.println("Iterasi ke-" + iterasi);
            System.out.println("OPEN dan CLOSED ketika bestnode akan dievaluasi : ");
            printOutOpen(open);
            printOutClosed(closed);

            System.out.println("## Best Node = " + bestNode.nama + "(f(n):" + bestNode.fN + ")");
            System.out.println("OPEN dan CLOSED ketika bestnode telah selesai dievaluasi : ");
            open.remove(bestNode);
            closed.add(bestNode);

            printOutOpen(open);
            printOutClosed(closed);
            for (Node suksesor : bestNode.child) {
                if (suksesor == null) {
                    continue;
                }
                double gN_dariBest = bestNode.gN + suksesor.cost;
                double fN_baru = bestNode.fN + suksesor.hN;

                if ((open.contains(suksesor)) && (fN_baru < suksesor.fN)) {
                    suksesor.fN = fN_baru;
                }
                if (!open.contains(suksesor)) {
                    suksesor.gN = gN_dariBest;
                    suksesor.fN = suksesor.gN + suksesor.hN;
                    suksesor.parent = bestNode;
                    open.add(suksesor);
                }
            }
            nodes.add(bestNode);
            iterasi++;
        }
        return nodes;
    }

    public void printOutOpen(Set<Node> open) {
        System.out.print("Open \t: ");
        Iterator<Node> iteratorNode = open.iterator();
        Iterator<Node> iteratorFn = open.iterator();
        while (iteratorNode.hasNext() || iteratorFn.hasNext()) {
            System.out.print(iteratorNode.next().nama + " ( f(n):" + iteratorFn.next().fN + " ) - ");
        }
        System.out.println("");
    }

    public void printOutClosed(Set<Node> closed) {
        System.out.print("Closed \t: ");
        Iterator<Node> iteratorNode = closed.iterator();
        Iterator<Node> iteratorFn = closed.iterator();
        while (iteratorNode.hasNext() || iteratorFn.hasNext()) {
            System.out.print(iteratorNode.next().nama + " ( f(n):" + iteratorFn.next().fN + " ) - ");
        }
        System.out.println("");
    }
}
