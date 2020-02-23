import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import com.sun.java.swing.plaf.windows.*;

public class OrderManager extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final String newline = "\n";
    public static final String GET_TOTAL = "Get Total";
    public static final String CREATE_ORDER = "Create Order";
    public static final String EXIT = "Exit";
    public static final String CA_ORDER = "California Order";
    public static final String NON_CA_ORDER = "Non-California Order";
    public static final String OVERSEAS_ORDER = "Overseas Order";
    public static final String COLOMBIAN_ORDER = "Colombian Order";


    private JComboBox cmbOrderType;
    private JTextField txtOrderAmount, txtAdditionalTax,
    txtAdditionalSH, txtAdditionalFFT;//AdditionalFFT se refiere al impuesto de 4x1000 solo valido en colombia
    private JLabel lblOrderType, lblOrderAmount;
    private JLabel lblAdditionalTax, lblAdditionalSH, lblAdditionalFFT;
    private JLabel lblTotal, lblTotalValue;

    private OrderVisitorIncremental objVisitorIncremental;
    private OrderVisitorIncremental objVisitorDecremental;
    private CollectionHandler objCollectionHandler;

    public OrderManager() {
      super("Visitor Pattern - Example");

      objCollectionHandler = new CollectionHandler();
      objVisitorIncremental = new OrderVisitorIncremental(objCollectionHandler);
      objVisitorDecremental = new OrderVisitorIncremental(objCollectionHandler);

      cmbOrderType = new JComboBox();
      cmbOrderType.addItem(OrderManager.CA_ORDER);
      cmbOrderType.addItem(OrderManager.NON_CA_ORDER);
      cmbOrderType.addItem(OrderManager.OVERSEAS_ORDER);
      cmbOrderType.addItem(OrderManager.COLOMBIAN_ORDER);

      //Create the text fields
      txtOrderAmount = new JTextField(10);
      txtAdditionalTax = new JTextField(10);
      txtAdditionalSH = new JTextField(10);
      txtAdditionalFFT = new JTextField(10);

      //Create the labels
      lblOrderType = new JLabel("Order Type:");
      lblOrderAmount = new JLabel("Order Amount:");
      lblAdditionalTax =
        new JLabel("Additional Tax(CA Orders Only):");
      lblAdditionalSH =
        new JLabel("Additional S & H(Overseas Orders Only):");
      lblAdditionalFFT = 
        new JLabel("Additional Colombian Tax (FFT)");
      lblTotal = new JLabel("Result:");
      lblTotalValue =
        new JLabel("Click Create or GetTotal Button");

      //Create the open button
      JButton getTotalButton =
        new JButton(OrderManager.GET_TOTAL);
      getTotalButton.setMnemonic(KeyEvent.VK_G);
      JButton createOrderButton =
        new JButton(OrderManager.CREATE_ORDER);
      getTotalButton.setMnemonic(KeyEvent.VK_C);
      JButton exitButton = new JButton(OrderManager.EXIT);
      exitButton.setMnemonic(KeyEvent.VK_X);
      ButtonHandler objButtonHandler = new ButtonHandler(this);


      getTotalButton.addActionListener(objButtonHandler);
      createOrderButton.addActionListener(objButtonHandler);
      exitButton.addActionListener(new ButtonHandler());

      //For layout purposes, put the buttons in a separate panel
      JPanel buttonPanel = new JPanel();

      JPanel panel = new JPanel();
      GridBagLayout gridbag2 = new GridBagLayout();
      panel.setLayout(gridbag2);
      GridBagConstraints gbc2 = new GridBagConstraints();
      panel.add(getTotalButton);
      panel.add(createOrderButton);
      panel.add(exitButton);
      gbc2.anchor = GridBagConstraints.EAST;
      gbc2.gridx = 0;
      gbc2.gridy = 0;
      gridbag2.setConstraints(createOrderButton, gbc2);
      gbc2.gridx = 1;
      gbc2.gridy = 0;
      gridbag2.setConstraints(getTotalButton, gbc2);
      gbc2.gridx = 2;
      gbc2.gridy = 0;
      gridbag2.setConstraints(exitButton, gbc2);

      //****************************************************
      GridBagLayout gridbag = new GridBagLayout();
      buttonPanel.setLayout(gridbag);
      GridBagConstraints gbc = new GridBagConstraints();

      buttonPanel.add(lblOrderType);
      buttonPanel.add(cmbOrderType);
      buttonPanel.add(lblOrderAmount);
      buttonPanel.add(txtOrderAmount);
      buttonPanel.add(lblAdditionalTax);
      buttonPanel.add(txtAdditionalTax);
      buttonPanel.add(lblAdditionalSH);
      buttonPanel.add(txtAdditionalSH);
      buttonPanel.add(lblAdditionalFFT);
      buttonPanel.add(txtAdditionalFFT);
      buttonPanel.add(lblTotal);
      buttonPanel.add(lblTotalValue);

      gbc.insets.top = 5;
      gbc.insets.bottom = 5;
      gbc.insets.left = 5;
      gbc.insets.right = 5;

      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 0;
      gridbag.setConstraints(lblOrderType, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 0;
      gridbag.setConstraints(cmbOrderType, gbc);

      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 1;
      gridbag.setConstraints(lblOrderAmount, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 1;
      gridbag.setConstraints(txtOrderAmount, gbc);

      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 2;
      gridbag.setConstraints(lblAdditionalTax, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 2;
      gridbag.setConstraints(txtAdditionalTax, gbc);

      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 3;
      gridbag.setConstraints(lblAdditionalSH, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 3;
      gridbag.setConstraints(txtAdditionalSH, gbc);

      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 4;
      gridbag.setConstraints(lblTotal, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 4;
      gridbag.setConstraints(lblTotalValue, gbc);

      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 40;

      //****************************************************

      //Add the buttons and the log to the frame
      Container contentPane = getContentPane();

      contentPane.add(buttonPanel, BorderLayout.NORTH);
      contentPane.add(panel, BorderLayout.CENTER);
      try {
        //UIManager.setLookAndFeel(new WindowsLookAndFeel());
        SwingUtilities.updateComponentTreeUI(
          OrderManager.this);
      } catch (Exception ex) {
        System.out.println(ex);
      }

    }

    public static void main(String[] args) {
      JFrame frame = new OrderManager();

      frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              System.exit(0);
            }
          }
      );

      //frame.pack();
      frame.setSize(700, 400);
      frame.setVisible(true);
    }

    public void setTotalValue(String msg) {
      lblTotalValue.setText(msg);
    }
    public OrderVisitorIncremental getOrderVisitor() {
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

    public String getFFT() {
      return txtAdditionalFFT.getText();
    }

} // End of class OrderManager

