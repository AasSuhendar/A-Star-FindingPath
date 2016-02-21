/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aassuhendar.my.id;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Aas Suhendar
 */
public class AStarFindingPath extends javax.swing.JFrame {

    /**
     * Creates new form AStarFindingPath
     */
    public AStarFindingPath() {
        initComponents();
        setLocationRelativeTo(null);
//           double f, double g, double h, double cost
//           
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

        CustomOutputStream taos = new CustomOutputStream(jTextAreaConsole);
        PrintStream ps = new PrintStream(taos);
        System.setOut(ps);
        System.setErr(ps);

//        AStar a = new AStar();
        Node awal = Bobbia;
        Node akhir = Ravenna;

        List<Node> hasil = aStarSearch(awal, akhir);
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
            fN = node.fN;

        }
        System.out.println("");
        System.out.println("Total Jarak Rute yang ditempuh : " + fN + " km");
        System.out.println("");
    }

    public List<Node> aStarSearch(Node start, Node goal) {

        Set<Node> open = new HashSet<Node>();
        Set<Node> closed = new HashSet<Node>();

        List<Node> nodes = new ArrayList<Node>();
        start.gN = 0;
        start.fN = start.hN;

        open.add(start);
        boolean found = false;
        int iterasi=1;
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
            System.out.println("Iterasi ke-"+iterasi);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaConsole = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxStart = new javax.swing.JComboBox();
        jComboBoxGoal = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("A* Finding Path");

        jLabel2.setText("Keberangkatan");

        jLabel3.setText("Tujuan");

        jTextAreaConsole.setColumns(20);
        jTextAreaConsole.setRows(5);
        jScrollPane1.setViewportView(jTextAreaConsole);

        jLabel4.setText("Tracing rute sungai yang memiliki jarak terdekat untuk mencapai kota Ravenna dari Bobbia dengan menggunakan Algoritma A*");

        jLabel5.setText("@Created By - Aas Suhendar 1301158668 Telkom University");

        jComboBoxStart.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bobbia" }));

        jComboBoxGoal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ravenna" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxGoal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(314, 314, 314)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxGoal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AStarFindingPath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AStarFindingPath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AStarFindingPath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AStarFindingPath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AStarFindingPath().setVisible(true);
            }
        });

//     
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBoxGoal;
    private javax.swing.JComboBox jComboBoxStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaConsole;
    // End of variables declaration//GEN-END:variables
}
