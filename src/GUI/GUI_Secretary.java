package GUI;

import BE.BEFireman;
import BE.BESalary;
import BLL.BLLSecretary;
import GUI.TableModel.TableModelSalary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.TableRowSorter;

public class GUI_Secretary extends javax.swing.JFrame {

    private TableModelSalary salaryModel;
    private final ArrayList<BESalary> EMPTY_ARRAY_LIST = new ArrayList<>();
    TableRowSorter<TableModelSalary> sorter;
    ArrayList<BESalary> salary = new ArrayList<>();
    int[] selectedRows;
    BESalary besalary;

    public GUI_Secretary() {
        initComponents();
        initializeSettings();

    }

    private void initializeSettings() {
        setTable();
        addListeners();
        tblSalary.setRowSelectionAllowed(true);
        fillComboFireman();


    }

    private void fillComboFireman() {
        cmbFiremen.addItem("vælg Brandmand");
        for (BEFireman be : BLLSecretary.getInstance().readAllFiremen()) {
            cmbFiremen.addItem(be);
        }
    }

    private void addListeners() {
        btnAction btn = new btnAction();
        mouseAction click = new mouseAction();
        btnSearch.addActionListener(btn);
        btnPrint.addActionListener(btn);
        tblSalary.addMouseListener(click);

    }

    private void setTable() {
        salaryModel = new TableModelSalary(EMPTY_ARRAY_LIST);
        tblSalary.setModel(salaryModel);
    }
    
    private void searchForFiremen(){
        String from = (((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText());
        String to = (((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText());
        if(cmbFiremen.getSelectedIndex() != 0){
        BEFireman befireman = (BEFireman) cmbFiremen.getSelectedItem();
        salary = BLLSecretary.getInstance().BLLSearchWithFiremen(befireman,from, to);
        salaryModel = new TableModelSalary(salary);
        }
        else{
            salary = BLLSecretary.getInstance().BLLSearch(from, to);
            salaryModel = new TableModelSalary(salary);
        }
        tblSalary.setModel(salaryModel);
        sorter = new TableRowSorter<>(salaryModel);
        tblSalary.setRowSorter(sorter);
        
    }

    private void onClickSearch() {
        searchForFiremen();
    }
    
    private void onClickPrint(){
        if(!salary.isEmpty())
        BLLSecretary.getInstance().sendToPdf(salary);
    }

//    private void onClickPrint() {
//        int rows[] = tblSalary.getSelectedRows();
//        ArrayList<BESalary> be = new ArrayList<>();
//        for (int i = 0; i < rows.length; i++) {
//            int modelRow = sorter.convertRowIndexToModel(rows[i]);
//            be.add(salaryModel.getSalaryByRow(modelRow));
//            System.out.println(be.get(i).getM_lastName());
//            BLLSecretary.getInstance().sendToPdf(be);
//        }
//    }

//    private void onClickPrint() {
//        //for(BESalary be : salaryModel.getSalaryListByRow(tblSalary.getSelectedRows()))
//            System.out.println(besalary.getM_lastName());
//    }
    private void onMoucseClick() {
        if (tblSalary.getSelectedRow() != -1) {
            int idx = tblSalary.getSelectedRow();
            int modelRow = sorter.convertRowIndexToModel(idx);
            //tblSalary.convertRowIndexToModel(idx);
            besalary = salaryModel.getSalaryByRow(modelRow);

        } else {
            besalary = null;
        }
    }



    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnSearch)) {
                onClickSearch();
            }
            if (e.getSource().equals(btnPrint)) {
                onClickPrint();
            }
        }
    }

    private class mouseAction extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            onMoucseClick();

        }
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalary = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateChooserFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dateChooserTo = new com.toedter.calendar.JDateChooser();
        btnSearch = new javax.swing.JButton();
        btnUsage = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbFiremen = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSalary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSalary);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel1.setLayout(null);

        jLabel1.setText("Fra:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 30, 24, 20);

        dateChooserFrom.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dateChooserFrom);
        dateChooserFrom.setBounds(50, 32, 95, 20);

        jLabel2.setText("Til:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(170, 30, 41, 20);

        dateChooserTo.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dateChooserTo);
        dateChooserTo.setBounds(200, 32, 95, 20);

        btnSearch.setText("Søg");
        jPanel1.add(btnSearch);
        btnSearch.setBounds(310, 30, 90, 25);

        btnUsage.setText("Se Forbrug");

        btnPrint.setText("Udskriv");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medarbejder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel2.setLayout(null);

        jPanel2.add(cmbFiremen);
        cmbFiremen.setBounds(20, 30, 200, 22);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(btnUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(960, 960, 960)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUsage;
    private javax.swing.JComboBox cmbFiremen;
    private com.toedter.calendar.JDateChooser dateChooserFrom;
    private com.toedter.calendar.JDateChooser dateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSalary;
    // End of variables declaration//GEN-END:variables
}
