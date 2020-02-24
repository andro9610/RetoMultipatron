package src;

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
import javax.swing.table.TableCellRenderer;
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
    public static final String VIEW_ROW = "View";
    public static final String MODIFY_ROW = "Modify";
    public static final String DELETE_ROW = "Delete";
    private ButtonHandler buttonAction;

    /** Elementos de la interfaz */
    private JLabel lblTittle, lblOrderType, lblOrderAmount, lblSearchTooltip, lblAdditionalTax, lblAdditionalSH,
            lblAdditionalFPT, lblResult, lblCode;

    private JComboBox<String> cmbOrderType;

    private JTextField txtOrderAmount, txtSearch, txtAdditionalTax, txtAdditionalSH, txtAdditionalFPT, txtCode;

    private JButton getTotalButton, createOrderButton, exitButton;

    /** Elementos Visitor */
    private VisitorInterface objVisitorIncremental;
    private VisitorInterface objVisitorDecremental;
    private CollectionHandler objCollectionHandler;

    /** Elementos para el manejo visual de la tabla */
    private DefaultTableModel dtm;
    private JTable orderTable;
    private JScrollPane tableContainer;

    public OrderManager() {
        buttonAction = new ButtonHandler(this);
        setSize(1380, 320);
        setTitle("Reto multipatron");
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(null);

        /** Elementos de tipo Visitor */
        objCollectionHandler = new CollectionHandler();
        objVisitorIncremental = new OrderVisitorIncremental(objCollectionHandler);
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

        lblCode = new JLabel("Code");
        txtCode = new JTextField();
        lblOrderAmount = new JLabel("Order Amount");
        txtOrderAmount = new JTextField();
        lblSearchTooltip = new JLabel("Search Order");
        txtSearch = new JTextField();

        /** Item especial: tabla de ordenes */
        Object[] columns = new Object[] { "ID", "Type", "Data", "modify", "Delete" };
        dtm = new DefaultTableModel(null, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        orderTable = new JTable(dtm);
        orderTable.addMouseListener(new TableEvent(orderTable));
        //orderTable.addMouseMotionListener(new TableEvent(orderTable));
        TableCellRenderer defaultRender = orderTable.getDefaultRenderer(Object.class);
        orderTable.setDefaultRenderer(Object.class, new RenderComponent(defaultRender));
        tableContainer = new JScrollPane(orderTable);
        /** Items variables */
        lblAdditionalTax = new JLabel("Additional Tax (CA Orders Only)");
        txtAdditionalTax = new JTextField();
        lblAdditionalSH = new JLabel("Additional S&H (Overseas Orders Only)");
        txtAdditionalSH = new JTextField();
        lblAdditionalFPT = new JLabel("Additional FFT (Colombian Orders Only)");
        txtAdditionalFPT = new JTextField();
        lblResult = new JLabel();
        /** Botones */
        getTotalButton = new JButton(OrderManager.GET_TOTAL);
        createOrderButton = new JButton(OrderManager.CREATE_ORDER);
        exitButton = new JButton(OrderManager.EXIT);
        /** Fin de la construccion de la interfaz grafica */

        /** Posicionamiento de elementos */
        lblTittle.setBounds(12, 12, 100, 50);

        lblCode.setBounds(20, 60, 250, 50);
        txtCode.setBounds(260, 75, 150, 20);

        lblOrderType.setBounds(20, 30, 250, 50);
        cmbOrderType.setBounds(260, 45, 150, 20);

        lblOrderAmount.setBounds(20, 90, 250, 50);
        txtOrderAmount.setBounds(260, 102, 150, 25);

        lblAdditionalTax.setBounds(20, 120, 250, 50);
        txtAdditionalTax.setBounds(260, 132, 150, 25);

        lblAdditionalSH.setBounds(20, 120, 250, 50);
        txtAdditionalSH.setBounds(260, 132, 150, 25);

        lblAdditionalFPT.setBounds(20, 120, 250, 50);
        txtAdditionalFPT.setBounds(260, 132, 150, 25);

        getTotalButton.setBounds(20, 172, 100, 20);
        createOrderButton.setBounds(126, 172, 120, 20);
        exitButton.setBounds(252, 172, 100, 20);

        lblSearchTooltip.setBounds(20, 260, 250, 50);
        txtSearch.setBounds(160, 276, 250, 25);

        tableContainer.setBounds(450, 20, 900, 290);
        lblResult.setBounds(150, 202, 250, 50);
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
        add(lblCode);
        add(txtCode);
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
        add(lblResult);
        /** Fin del agregado de items */

    }

    public void setResult(String msg) {
        lblResult.setText(msg);
    }

    public void modify(){
        txtCode.setText((String)dtm.getValueAt(orderTable.getSelectedRow(), 0));
    }

    public VisitorInterface getVisitorIncremental() {
        return objVisitorIncremental;
    }

    public VisitorInterface getVisitorDecremental() {
        return objVisitorDecremental;
    }

    public CollectionHandler getCollectionHandler() {
        return objCollectionHandler;
    }

    public JTable getTable(){
        return orderTable;
    }

    public String getOrderType() {
        return (String) cmbOrderType.getSelectedItem();
    }

    public String getCode(){
        String texto = (String) txtCode.getText();
        txtCode.setText("");
        return texto;
    }

    public String getOrderAmount() {
        String texto = (String) txtOrderAmount.getText();
        txtOrderAmount.setText("");
        return texto;
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
                tr.setRowFilter(RowFilter.regexFilter(txtSearch.getText(), 0));
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
                        txtAdditionalSH.setText("");
                        lblAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setText("");
                        break;
                    case OrderManager.NON_CA_ORDER:
                        lblAdditionalTax.setVisible(false);
                        txtAdditionalTax.setVisible(false);
                        txtAdditionalTax.setText("");
                        lblAdditionalSH.setVisible(false);
                        txtAdditionalSH.setVisible(false);
                        txtAdditionalSH.setText("");
                        lblAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setText("");
                        break;
                    case OrderManager.OVERSEAS_ORDER:
                        lblAdditionalTax.setVisible(false);
                        txtAdditionalTax.setVisible(false);
                        txtAdditionalTax.setText("");
                        lblAdditionalSH.setVisible(true);
                        txtAdditionalSH.setVisible(true);
                        lblAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setVisible(false);
                        txtAdditionalFPT.setText("");
                        break;
                    case OrderManager.COLOMBIAN_ORDER:
                        lblAdditionalTax.setVisible(false);
                        txtAdditionalTax.setVisible(false);
                        txtAdditionalTax.setText("");
                        lblAdditionalSH.setVisible(false);
                        txtAdditionalSH.setVisible(false);
                        txtAdditionalSH.setText("");
                        lblAdditionalFPT.setVisible(true);
                        txtAdditionalFPT.setVisible(true);
                        break;
                }
            }
            
        });
    }

    public boolean addRow(Object[] obj){
        JButton btn1 = new JButton(OrderManager.VIEW_ROW);
        JButton btn2 = new JButton(OrderManager.MODIFY_ROW);
        JButton btn3 = new JButton(OrderManager.DELETE_ROW);
        btn1.addMouseListener(buttonAction);
        btn2.addMouseListener(buttonAction);
        btn3.addMouseListener(buttonAction);
        Object[] row = {obj[0], obj[1], btn1, btn2, btn3};
        dtm.addRow(row);
        return true;
    }

    public boolean removeRow(){
        Integer key = Integer.parseInt((String) dtm.getValueAt(orderTable.getSelectedRow(), 0));
        objCollectionHandler.removeOrder(key).accept(objVisitorDecremental);;
        dtm.removeRow(orderTable.getSelectedRow());
        return true;
    }

    public boolean modifyRow(Object[] obj){
        for(int i = 0 ; i < dtm.getRowCount() ; i++){
            if(((String) obj[0]).equals((String) dtm.getValueAt(i, 0))){
                dtm.setValueAt(obj[0], i, 0);
                dtm.setValueAt(obj[1], i, 1);
                return true;
            }
        }
        return false;
    }

    private void addButtonListeners() {

        getTotalButton.setActionCommand(OrderManager.GET_TOTAL);
        createOrderButton.setActionCommand(OrderManager.CREATE_ORDER);
        exitButton.setActionCommand(OrderManager.EXIT);

        getTotalButton.addActionListener(buttonAction);
        createOrderButton.addActionListener(buttonAction);
        exitButton.addActionListener(buttonAction);

    }
}