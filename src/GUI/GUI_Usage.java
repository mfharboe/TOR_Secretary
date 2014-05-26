package GUI;

import BE.BEIncident;
import BE.BEUsage;
import BLL.BLLPDFCreator;
import BLL.BLLSecretary;
import GUI.TableModel.TableModelIncident;
import GUI.TableModel.TableModelSalary;
import GUI.TableModel.TableModelUsage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.TableRowSorter;

public class GUI_Usage extends javax.swing.JFrame {

    private TableModelIncident incidentModel;
    private TableModelUsage usageModel;
    private final ArrayList<BEIncident> EMPTY_ARRAY_LIST_INCIDENT = new ArrayList<>();
    private final ArrayList<BEUsage> EMPTY_ARRAY_LIST_USAGE = new ArrayList<>();
    ArrayList<BEIncident> incident = new ArrayList<>();
    ArrayList<BEUsage> usage = new ArrayList<>();
    TableRowSorter<TableModelIncident> sorter;
    BEIncident beincident;

    public GUI_Usage() {
        initComponents();
        initializeSettings();

    }

    private void initializeSettings() {
        addColors();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTable();
        addListeners();
    }

    private void addColors() {
        this.getContentPane().setBackground(Color.WHITE);
        jPanel1.setBackground(Color.WHITE);
        jPanel2.setBackground(Color.WHITE);


    }

    private void addListeners() {
        btnAction btn = new btnAction();
        mouseAction click = new mouseAction();
        btnSearch.addActionListener(btn);
        btnPrint.addActionListener(btn);
        btnOk.addActionListener(btn);
        tblIncident.addMouseListener(click);
    }

    private void setTable() {
        incidentModel = new TableModelIncident(EMPTY_ARRAY_LIST_INCIDENT);
        tblIncident.setModel(incidentModel);
        usageModel = new TableModelUsage(EMPTY_ARRAY_LIST_USAGE);
        tblUsage.setModel(usageModel);
    }

    private void onClickSearch() {
        if (((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText().isEmpty() || ((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText().isEmpty()) {
            MessageDialog.getInstance().selectDatesText();
        } else {
            java.util.Date utilDateFrom = dateChooserFrom.getDate();
            java.sql.Date from = new java.sql.Date(utilDateFrom.getTime());
            java.util.Date utilDateTo = dateChooserTo.getDate();
            java.sql.Date to = new java.sql.Date(utilDateTo.getTime());
            incident = BLLSecretary.getInstance().sortIncidents(from, to);
            incidentModel = new TableModelIncident(incident);
            tblIncident.setModel(incidentModel);
            sorter = new TableRowSorter<>(incidentModel);
            tblIncident.setRowSorter(sorter);
        }
    }

    private void onClickPrint() {
        if (tblIncident.getSelectedRow() != -1) {
            int idx = tblIncident.getSelectedRow();
            int modelRow = sorter.convertRowIndexToModel(idx);
            //tblSalary.convertRowIndexToModel(idx);
            beincident = incidentModel.getIncidentByRow(modelRow);
            BLLPDFCreator.getInstance().printPdfUsage(BLLSecretary.getInstance().sortIncidentDetails(beincident), beincident, usage);

        }
    }

    private void onMouseClick() {
        if (tblIncident.getSelectedRow() != -1) {
            int idx = tblIncident.getSelectedRow();
            int modelRow = sorter.convertRowIndexToModel(idx);
            //tblSalary.convertRowIndexToModel(idx);
            beincident = incidentModel.getIncidentByRow(modelRow);
            txtIncidentName.setText(beincident.getM_incidentName());
            txtAlarm.setText(BLLSecretary.getInstance().sortIncidentDetails(beincident).get(0).getM_alarm().getM_description());
            txtDetectorNumber.setText(BLLSecretary.getInstance().sortIncidentDetails(beincident).get(0).getM_detectorNumber());
            txtGroupNumber.setText(BLLSecretary.getInstance().sortIncidentDetails(beincident).get(0).getM_groupNumber());
            usage = BLLSecretary.getInstance().sortUsages(beincident);
            usageModel = new TableModelUsage(usage);
            tblUsage.setModel(usageModel);

        } else {
            beincident = null;
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
            if (e.getSource().equals(btnOk)) {
                dispose();
            }
        }
    }

    private class mouseAction extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            onMouseClick();

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIncident = new javax.swing.JTable();
        dateChooserFrom = new com.toedter.calendar.JDateChooser();
        dateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsage = new javax.swing.JTable();
        txtDetectorNumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAlarm = new javax.swing.JTextField();
        txtGroupNumber = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtIncidentName = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Indsats", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel1.setLayout(null);

        tblIncident.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblIncident);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(180, 20, 770, 200);
        jPanel1.add(dateChooserFrom);
        dateChooserFrom.setBounds(50, 30, 120, 22);
        jPanel1.add(dateChooserTo);
        dateChooserTo.setBounds(50, 60, 120, 22);

        jLabel1.setText("Til");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 60, 20, 20);

        jLabel2.setText("Fra");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 30, 19, 20);

        btnSearch.setText("Søg");
        jPanel1.add(btnSearch);
        btnSearch.setBounds(100, 90, 70, 25);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Material Forbrug", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel2.setLayout(null);

        tblUsage.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblUsage);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(190, 20, 760, 240);
        jPanel2.add(txtDetectorNumber);
        txtDetectorNumber.setBounds(10, 200, 160, 22);

        jLabel3.setText("Detektor nummer");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 180, 110, 16);
        jPanel2.add(txtAlarm);
        txtAlarm.setBounds(10, 100, 160, 22);
        jPanel2.add(txtGroupNumber);
        txtGroupNumber.setBounds(10, 150, 160, 22);

        jLabel4.setText("Beretning");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 80, 60, 16);

        jLabel5.setText("Gruppe nr.");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 130, 80, 16);

        btnPrint.setText("Udskriv");
        jPanel2.add(btnPrint);
        btnPrint.setBounds(90, 230, 79, 25);

        jLabel6.setText("Indsats Navn");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 30, 73, 16);
        jPanel2.add(txtIncidentName);
        txtIncidentName.setBounds(10, 50, 160, 22);

        btnOk.setText("Ok");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(890, 890, 890)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(btnOk)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSearch;
    private com.toedter.calendar.JDateChooser dateChooserFrom;
    private com.toedter.calendar.JDateChooser dateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblIncident;
    private javax.swing.JTable tblUsage;
    private javax.swing.JTextField txtAlarm;
    private javax.swing.JTextField txtDetectorNumber;
    private javax.swing.JTextField txtGroupNumber;
    private javax.swing.JTextField txtIncidentName;
    // End of variables declaration//GEN-END:variables
}
