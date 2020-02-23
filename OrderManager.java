import javax.swing.JFrame;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class OrderManager extends JFrame {
    private static final long serialVersionUID = 1L;

    /** Instrucciones de texto */
    public static final String GET_TOTAL = "Get Total";
    public static final String CREATE_ORDER = "Create Order";
    public static final String EXIT = "Exit";
    public static final String CA_ORDER = "California Order";
    public static final String NON_CA_ORDER = "Non-California Order";
    public static final String OVERSEAS_ORDER = "Overseas Order";
    public static final String COLOMBIAN_ORDER = "Colombian Order";

    /** Elementos de la interfaz */
    JLabel lblTittle, lblOrderType, lblOrderAmount, lblSearchTooltip, lblAdditionalTax, lblAdditionalSH,
            lblAdditionalFPT, lblTotalValue;

    JComboBox<String> cmbOrderType;

    JTextField txtOrderAmount, txtSearch, txtAdditionalTax, txtAdditionalSH, txtAdditionalFPT;

    JButton getTotalButton, createOrderButton, exitButton;

    /** Elementos Visitor */
    private OrderVisitorIncremental objVisitorIncremental;
    private OrderVisitorDecremental objVisitorDecremental;
    private CollectionHandler objCollectionHandler;

    /** Elementos para el manejo visual de la tabla */
    DefaultTableModel dtm;
    JTable orderTable;
    JScrollPane tableContainer;

    public OrderManager() {
        setSize(1380, 280);
        setTitle("Reto multipatron");
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(null);

        /** Elementos de tipo Visitor */
        objCollectionHandler = new CollectionHandler();
        objVisitorIncremental = new OrderVisitorDecremental(objCollectionHandler);
        objVisitorDecremental = new OrderVisitorDecremental(objCollectionHandler);
        /** Construccion de la interfaz grafica */

        /** Items fijos */
        lblTittle = new JLabel("Reto Multipatron");

        lblOrderType = new JLabel("OrderType");

        cmbOrderType = new JComboBox<String>();
        cmbOrderType.addItem(OrderManager.CA_ORDER);
        cmbOrderType.addItem(OrderManager.NON_CA_ORDER);
        cmbOrderType.addItem(OrderManager.OVERSEAS_ORDER);
        cmbOrderType.addItem(OrderManager.COLOMBIAN_ORDER);

        lblOrderAmount = new JLabel("Order Amount");
        txtOrderAmount = new JTextField();
        lblSearchTooltip = new JLabel("Search Order");
        txtSearch = new JTextField();

        /** Item especial: tabla de ordenes */
        Object[][] data = new Object[][] { { "0", "Colombian Order", "500", "0", "0", "35", "no" },
                { "1", "Non-CaliforniaOrder", "430", "0", "0", "25", "no" } };
        Object[] columns = new Object[] { "ID", "Type", "Amount", "Tax", "S&H", "FFT", "Delete?" };
        dtm = new DefaultTableModel(data, columns);
        orderTable = new JTable(dtm);
        tableContainer = new JScrollPane(orderTable);
        /** Items variables */
        lblAdditionalTax = new JLabel("Additional Tax (CA Orders Only)");
        txtAdditionalTax = new JTextField();
        lblAdditionalSH = new JLabel("Additional S&H (Overseas Orders Only)");
        txtAdditionalSH = new JTextField();
        lblAdditionalFPT = new JLabel("Additional FFT (Colombian Orders Only)");
        txtAdditionalFPT = new JTextField();
        lblTotalValue = new JLabel();
        /** Botones */
        getTotalButton = new JButton(OrderManager.GET_TOTAL);
        createOrderButton = new JButton(OrderManager.CREATE_ORDER);
        exitButton = new JButton(OrderManager.EXIT);
        /** Fin de la construccion de la interfaz grafica */

        /** Posicionamiento de elementos */
        lblTittle.setBounds(12, 12, 100, 50);

        lblOrderType.setBounds(20, 40, 250, 50);
        cmbOrderType.setBounds(260, 55, 150, 20);

        lblOrderAmount.setBounds(20, 70, 250, 50);
        txtOrderAmount.setBounds(260, 82, 150, 25);

        lblAdditionalTax.setBounds(20, 100, 250, 50);
        txtAdditionalTax.setBounds(260, 112, 150, 25);

        lblAdditionalSH.setBounds(20, 100, 250, 50);
        txtAdditionalSH.setBounds(260, 112, 150, 25);

        lblAdditionalFPT.setBounds(20, 100, 250, 50);
        txtAdditionalFPT.setBounds(260, 112, 150, 25);

        getTotalButton.setBounds(20, 152, 100, 20);
        createOrderButton.setBounds(126, 152, 120, 20);
        exitButton.setBounds(252, 152, 100, 20);

        lblSearchTooltip.setBounds(20, 222, 250, 50);
        txtSearch.setBounds(160, 236, 250, 25);

        tableContainer.setBounds(450, 20, 900, 250);
        lblTotalValue.setBounds(150, 182, 250, 50);
        /** Fin del posicionamiento de elementos */

        /** Configuracion de visibilidad */
        lblAdditionalTax.setVisible(false);
        txtAdditionalTax.setVisible(false);

        lblAdditionalSH.setVisible(false);
        txtAdditionalSH.setVisible(false);

        lblAdditionalFPT.setVisible(false);
        txtAdditionalFPT.setVisible(false);
        /** Fin de configuracion de visibilidad */

        /** Configuracion de comportamiento */
        cmbOrderType.setSelectedItem(OrderManager.NON_CA_ORDER);
        addFilterSearch();
        addElementSwitch();
        addButtonListeners();
        /** Fin de configuracion de comportamiento */

        /** Agregado de items */

        add(lblTittle);
        add(lblOrderType);
        add(cmbOrderType);
        add(lblOrderAmount);
        add(txtOrderAmount);
        add(lblAdditionalTax);
        add(txtAdditionalTax);
        add(lblAdditionalSH);
        add(txtAdditionalSH);
        add(lblAdditionalFPT);
        add(txtAdditionalFPT);
        add(getTotalButton);
        add(createOrderButton);
        add(exitButton);
        add(lblSearchTooltip);
        add(txtSearch);
        add(tableContainer);
        add(lblTotalValue);
        /** Fin del agregado de items */

    }

    public void setTotalValue(String msg) {
        lblTotalValue.setText(msg);
    }

    public OrderVisitorDecremental getOrderVisitor() {
        return objVisitorIncremental;
    }

    public CollectionHandler getCollectionHandler() {
        return objCollectionHandler;
    }

    public String getOrderType() {
        return (String) cmbOrderType.getSelectedItem();
    }

    public String getOrderAmount() {
        return txtOrderAmount.getText();
    }

    public String getTax() {
        return txtAdditionalTax.getText();
    }

    public String getSH() {
        return txtAdditionalSH.getText();
    }

    public String getFPT() {
        return txtAdditionalFPT.getText();
    }

    /** Metodo para manejar las busquedas en vivo */
    private void addFilterSearch() {
        txtSearch.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dtm);
                orderTable.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
            }
        });

    }

    private void addElementSwitch() {
        cmbOrderType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch ((String) cmbOrderType.getSelectedItem()) {
                    case OrderManager.CA_ORDER:
                        lblAdditionalTax.setVisible(true);
                        txtAdditionalTax.setVisible(true);
                        lblAdditionalSH.setVisible(false);
                        txtAdditionalSH.setVisible(false);
                        lblAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setVisible(false);
                        break;
                    case OrderManager.NON_CA_ORDER:
                        lblAdditionalTax.setVisible(false);
                        txtAdditionalTax.setVisible(false);
                        lblAdditionalSH.setVisible(false);
                        txtAdditionalSH.setVisible(false);
                        lblAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setVisible(false);
                        break;
                    case OrderManager.OVERSEAS_ORDER:
                        lblAdditionalTax.setVisible(false);
                        txtAdditionalTax.setVisible(false);
                        lblAdditionalSH.setVisible(true);
                        txtAdditionalSH.setVisible(true);
                        lblAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setVisible(false);
                        break;
                    case OrderManager.COLOMBIAN_ORDER:
                        lblAdditionalTax.setVisible(false);
                        txtAdditionalTax.setVisible(false);
                        lblAdditionalSH.setVisible(false);
                        txtAdditionalSH.setVisible(false);
                        lblAdditionalFPT.setVisible(true);
                        txtAdditionalFPT.setVisible(true);
                        break;
                }
            }
            
        });
    }

    private void addButtonListeners() {
        ButtonHandler buttonAction = new ButtonHandler();

        getTotalButton.addActionListener(buttonAction);
        createOrderButton.addActionListener(buttonAction);
        exitButton.addActionListener(buttonAction);

        getTotalButton.setActionCommand(OrderManager.GET_TOTAL);
        createOrderButton.setActionCommand(OrderManager.CREATE_ORDER);
        exitButton.setActionCommand(OrderManager.EXIT);
    }
}