import java.awt.event.*;

class ButtonHandler implements ActionListener {
  OrderManager objOrderManager;
  public void actionPerformed(ActionEvent e) {
    String totalResult = null;

    if (e.getActionCommand().equals(OrderManager.EXIT)) {
      System.exit(1);
    }
    if (e.getActionCommand().equals(OrderManager.CREATE_ORDER)
        ) {
      //get input values
      String orderType = objOrderManager.getOrderType();
      String strOrderAmount =
        objOrderManager.getOrderAmount();
      String strTax = objOrderManager.getTax();
      String strSH = objOrderManager.getSH();
      String strFFT = objOrderManager.getFPT();

      double dblOrderAmount = 0.0;
      double dblTax = 0.0;
      double dblSH = 0.0;
      double dblFFT = 0.0;

      if (strOrderAmount.trim().length() == 0) {
        strOrderAmount = "0.0";
      }
      if (strTax.trim().length() == 0) {
        strTax = "0.0";
      }
      if (strSH.trim().length() == 0) {
        strSH = "0.0";
      }

      dblOrderAmount = Double.parseDouble(strOrderAmount);
      dblTax = Double.parseDouble(strTax);
      dblSH = Double.parseDouble(strSH);
      dblFFT = Double.parseDouble(strFFT);

      //Create the order
      Order order = createOrder(orderType, dblOrderAmount,
                    dblTax, dblSH,dblFFT);

      //Get the Visitor
      OrderVisitorDecremental visitor =
        objOrderManager.getOrderVisitor();

      // accept the visitor instance
      order.accept(visitor);

      objOrderManager.setTotalValue(
        " Order Created Successfully");
    }
    if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {
      totalResult = ((Double) objOrderManager.getCollectionHandler().getOrderTotal()).toString();
      totalResult = " Orders Total = " + totalResult;
      objOrderManager.setTotalValue(totalResult);
    }
  }

  public Order createOrder(String orderType,
      double orderAmount, double tax, double SH, double FFT) {
    if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
      return new CaliforniaOrder(orderAmount, tax);
    }
    if (orderType.equalsIgnoreCase(
      OrderManager.NON_CA_ORDER)) {
      return new NonCaliforniaOrder(orderAmount);
    }
    if (orderType.equalsIgnoreCase(
      OrderManager.OVERSEAS_ORDER)) {
      return new OverseasOrder(orderAmount, SH);
    }
    if (orderType.equalsIgnoreCase(
      OrderManager.COLOMBIAN_ORDER)){
        return new ColombianOrder(orderAmount,FFT);
      }
    return null;
  }

  public ButtonHandler() {
  }
  public ButtonHandler(OrderManager inObjOrderManager) {
    objOrderManager = inObjOrderManager;
  }

}