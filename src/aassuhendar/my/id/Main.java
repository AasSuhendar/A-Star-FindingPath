/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aassuhendar.my.id;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Aas Suhendar
 */
public class Main {

    public static void main(String[] args) {
//		double f, double g, double h, double cost
        Node Bobbia = new Node("Bobbia", 0, 0, 10.5, 0);
        Node Piacenza = new Node("Piacenza", 0, 0, 10, 5);
        Node Terme = new Node("Terme", 0, 0, 7, 3);
        Node Cessena = new Node("Cessena", 0, 0, 4.5, 15);
        Node Ravenna = new Node("Ravenna", 0, 0, 0, 3);
        Node Ramini = new Node("Rimini", 0, 0, 0.5, 6);
        Node Ferrara = new Node("Ferrara", 0, 0, 5, 8);
        Node Forli = new Node("Forli", 0, 0, 2, 2);
        Node Faenza = new Node("Faenza", 0, 0, 4, 3);
        Node Imola = new Node("Imola", 0, 0, 5, 2);
        Node Emilia = new Node("Emilia", 0, 0, 6, 2);
        Node Carpi = new Node("Carpi", 0, 0, 8, 3);

        ArrayList<Node> suksesorBobia = new ArrayList<Node>();
        suksesorBobia.add(Piacenza);
        suksesorBobia.add(Terme);
        suksesorBobia.add(Cessena);
        Bobbia.setChild(suksesorBobia);

        ArrayList<Node> suksesorPiacenza = new ArrayList<Node>();
        suksesorPiacenza.add(Terme);
        suksesorPiacenza.add(Carpi);
        Piacenza.setChild(suksesorPiacenza);
        Piacenza.setParent(Bobbia);

        ArrayList<Node> suksesorCessena = new ArrayList<Node>();
        suksesorCessena.add(Ramini);
        Cessena.setChild(suksesorPiacenza);
        Cessena.setParent(Bobbia);
        Cessena.setParent(Faenza);
        Cessena.setParent(Forli);

        ArrayList<Node> suksesorTerme = new ArrayList<Node>();
        suksesorTerme.add(Faenza);
        suksesorTerme.add(Emilia);
        Terme.setChild(suksesorTerme);
        Terme.setParent(Bobbia);
        Terme.setParent(Piacenza);

        ArrayList<Node> suksesorFaenza = new ArrayList<Node>();
        suksesorFaenza.add(Cessena);
        suksesorFaenza.add(Forli);
        Faenza.setChild(suksesorFaenza);
        Faenza.setParent(Terme);
        Faenza.setParent(Imola);

        ArrayList<Node> suksesorEmilia = new ArrayList<Node>();
        suksesorEmilia.add(Imola);
        Emilia.setChild(suksesorEmilia);
        Emilia.setParent(Terme);
        Emilia.setParent(Carpi);

        ArrayList<Node> suksesorCapri = new ArrayList<Node>();
        suksesorCapri.add(Emilia);
        suksesorCapri.add(Ferrara);
        Carpi.setChild(suksesorCapri);
        Carpi.setParent(Piacenza);

        ArrayList<Node> suksesorImola = new ArrayList<Node>();
        suksesorImola.add(Faenza);
        suksesorImola.add(Forli);
        Imola.setChild(suksesorImola);
        Imola.setParent(Emilia);
        Imola.setParent(Ferrara);

        ArrayList<Node> suksesorForli = new ArrayList<Node>();
        suksesorForli.add(Cessena);
        suksesorForli.add(Ravenna);
        Forli.setChild(suksesorForli);
        Forli.setParent(Imola);
        Forli.setParent(Faenza);

        ArrayList<Node> suksesorFeraara = new ArrayList<Node>();
        suksesorFeraara.add(Imola);
        suksesorFeraara.add(Ravenna);
        Ferrara.setChild(suksesorForli);
        Ferrara.setParent(Carpi);

        ArrayList<Node> suksesorRamini = new ArrayList<Node>();
        suksesorRamini.add(Ravenna);
        Ramini.setChild(suksesorRamini);
        Ramini.setParent(Cessena);

        AStar a = new AStar();
        Node awal = Bobbia;
        Node akhir = Ravenna;

        List<Node> hasil = a.aStarSearch(awal, akhir);
        System.out.println();
//        Collections.reverse(hasil);

        System.out.print("Rute Terbaik dari " + awal.nama + " - " + akhir.nama + " = \n");
        double fN = 0;
        for (Node node : hasil) {
            if (node.nama.equals(akhir.nama)) {
                System.out.print(node.nama);
            } else {
                System.out.print(node.nama + "->");
            }
            fN=node.fN;

        }
        System.out.println("");
        System.out.println("Total Jarak Rute yang ditempuh : "+fN+" km");
        System.out.println("");

    }
}
