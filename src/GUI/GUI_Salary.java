package GUI;

import BE.BEFireman;
import BE.BESalary;
import BLL.BLLError;
import BLL.BLLPDFCreator;
import BLL.BLLSalary;
import DAL.DALRead;
import GUI.TableModel.TableModelSalary;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class GUI_Salary extends javax.swing.JFrame {

    private TableModelSalary salaryModel;
    private final ArrayList<BESalary> EMPTY_ARRAY_LIST = new ArrayList<>();
    ArrayList<BESalary> salary = new ArrayList<>();
    ImageIcon imageLogo;

    /**
     * Creates a new form GUI_Salary
     */
    public GUI_Salary() {
        BLLSalary.getInstance().setDAL(DALRead.getInstance());
        BLLError.getInstance().register(MessageDialog.getInstance());
        initComponents();
        initializeSettings();

    }

    /**
     * The initial setting for this class
     */
    private void initializeSettings() {
        addColors();
        addImage();
        setTable();
        addListeners();
        fillComboFireman();
    }

    /**
     * Adjust color of GUI
     */
    private void addColors() {
        this.getContentPane().setBackground(Color.WHITE);
        jPanel1.setBackground(Color.WHITE);
        jPanel2.setBackground(Color.WHITE);
        cmbFiremen.setBackground(Color.WHITE);
        lblImage.setBackground(Color.WHITE);
    }

    /**
     * Adds a logo in the GUI
     */
    private void addImage() {
        imageLogo = new ImageIcon("ebr.jpg");
        Image newimg = imageLogo.getImage().getScaledInstance(250, 50, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        lblImage.setIcon(newIcon);
    }

    /**
     * Fills the Fireman ComboBox
     */
    private void fillComboFireman() {
        cmbFiremen.addItem(MessageDialog.getInstance().cmbFireman());
        for (BEFireman be : BLLSalary.getInstance().readAllFiremen()) {
            cmbFiremen.addItem(be);
        }
    }

    /**
     * Adds listeners to the class
     */
    private void addListeners() {
        btnAction btn = new btnAction();
        btnSearch.addActionListener(btn);
        btnPrint.addActionListener(btn);
        btnUsage.addActionListener(btn);

    }

    /**
     * Sets the initial tablemodel
     */
    private void setTable() {
        salaryModel = new TableModelSalary(EMPTY_ARRAY_LIST);
        tblSalary.setModel(salaryModel);
    }

    /**
     * Searches for Firemen
     */
    private void searchForFiremen() {
        String from = (((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText());
        String to = (((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText());
        if (cmbFiremen.getSelectedIndex() != 0) {
            BEFireman befireman = (BEFireman) cmbFiremen.getSelectedItem();
            salary = BLLSalary.getInstance().readSalariesInTimePeriodWithFiremen(befireman, from, to);
            salaryModel = new TableModelSalary(salary);
        } else {
            salary = BLLSalary.getInstance().readSalariesInTimePeriod(from, to);
            salaryModel = new TableModelSalary(salary);
        }
        tblSalary.setModel(salaryModel);

    }

    /**
     * Determines what happens when you cick the search button
     */
    private void onClickSearch() {
        boolean isEmpty1 = ((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText().isEmpty();
        boolean isEmpty2 = ((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText().isEmpty();
        if (!(isEmpty1 || isEmpty2)) {
            if (!dateChooserTo.getDate().before(dateChooserFrom.getDate())) {
                searchForFiremen();
            } else {
                MessageDialog.getInstance().reverseDatesText();
            }
        } else {
            MessageDialog.getInstance().selectDatesText();
        }

    }

    private void onClickPrint() {
        String from = (((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText());
        String to = (((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText());
        if (cmbFiremen.getSelectedIndex() != 0) {
            BEFireman befireman = (BEFireman) cmbFiremen.getSelectedItem();
            onClickPrintFireman(befireman, from, to);
        } else if (cmbFiremen.getSelectedIndex() == 0) {
            onClickPrintRooster(from, to);
        }
    }

    /**
     * Send relevant fireman information to PDF Creator class.
     *
     * @param befireman
     * @param from
     * @param to
     */
    private void onClickPrintFireman(BEFireman befireman, String from, String to) {

        if (!salary.isEmpty()) {
            BLLPDFCreator.getInstance().printPDFFireman(salary, befireman, from, to);
            MessageDialog.getInstance().printedConfirmMessage();

        }
    }

    /**
     * Send relevant incident information to PDF Creator class
     *
     * @param from
     * @param to
     */
    private void onClickPrintRooster(String from, String to) {
        if (!salary.isEmpty()) {
            BLLPDFCreator.getInstance().printPDFRooster(salary, from, to);
            MessageDialog.getInstance().printedConfirmMessage();

        }
    }

    /**
     * Open a new instance of GUI_Usage
     */
    private void onClickUsage() {
        GUI_Usage guiUsage = new GUI_Usage();
        guiUsage.setVisible(true);
    }

    /**
     * Listener for the buttons
     */
    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnSearch)) {
                onClickSearch();
            }
            if (e.getSource().equals(btnPrint)) {
                onClickPrint();
            }
            if (e.getSource().equals(btnUsage)) {
                onClickUsage();
            }
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
        btnUsage = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbFiremen = new javax.swing.JComboBox();
        lblImage = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();

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

        btnUsage.setText("Se Forbrug");

        btnPrint.setText("Udskriv");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medarbejder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel2.setLayout(null);

        jPanel2.add(cmbFiremen);
        cmbFiremen.setBounds(20, 30, 200, 22);

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnSearch.setText("Søg");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(830, 830, 830)
                .addComponent(btnUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnSearch))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tblSalary;
    // End of variables declaration//GEN-END:variables
}
