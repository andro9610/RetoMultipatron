package src;

import java.awt.event.*;
import javax.swing.AbstractButton;

class ButtonHandler implements ActionListener, MouseListener {

  private OrderManager objOrderManager;
  private OrdersFactory factoriaOrdenes;

  public void actionPerformed(ActionEvent e) {
    String totalResult = null;
    if (e.getActionCommand().equals(OrderManager.EXIT)) {
      System.exit(1);
    }
    else if (e.getActionCommand().equals(OrderManager.CREATE_ORDER)
        ) {
      //get input values
      String strCode = objOrderManager.getCode();
      String orderType = objOrderManager.getOrderType();
      Object[] obj = {strCode, orderType};
      String strOrderAmount =
        objOrderManager.getOrderAmount();
      String strTax = objOrderManager.getTax();
      String strSH = objOrderManager.getSH();
      String strFFT = objOrderManager.getFPT();

      int code = 0;
      double dblOrderAmount = 0.0;
      double dblTax = 0.0;
      double dblSH = 0.0;
      double dblFFT = 0.0;

      if(strCode.trim().length()==0){
        objOrderManager.setResult("El código no puede estár vacio");
        return;
      }
      if (strOrderAmount.trim().length() == 0) {
        strOrderAmount = "0.0";
      }
      if (strTax.trim().length() == 0) {
        strTax = "0.0";
      }
      if (strSH.trim().length() == 0) {
        strSH = "0.0";
      }
      if(strFFT.trim().length() == 0){
        strFFT = "0.0";
      }

      code = Integer.parseInt(strCode);
      dblOrderAmount = Double.parseDouble(strOrderAmount);
      dblTax = Double.parseDouble(strTax);
      dblSH = Double.parseDouble(strSH);
      dblFFT = Double.parseDouble(strFFT);

      //Create the order
      Order order = factoriaOrdenes.createOrder(orderType, dblOrderAmount,
                    dblTax, dblSH,dblFFT);

      VisitorInterface visitor;

      if(comprobarExistenciaOrden(code)){
        visitor = objOrderManager.getVisitorDecremental();
        objOrderManager.getCollectionHandler().getOrder(code).accept(visitor);
        objOrderManager.getCollectionHandler().addOrder(code, order);
        visitor = objOrderManager.getVisitorIncremental();
        order.accept(visitor);
        objOrderManager.modifyRow(obj);
        objOrderManager.setResult("modified order");
      }
      else{
        visitor = objOrderManager.getVisitorIncremental();
        objOrderManager.getCollectionHandler().addOrder(code, order);
        order.accept(visitor);
        objOrderManager.addRow(obj);
        objOrderManager.setResult("Order Created Successfully");
      }
    }
    else if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {
      totalResult = ((Double) objOrderManager.getCollectionHandler().getOrderTotal()).toString();
      totalResult = " Orders Total = " + totalResult;
      objOrderManager.setResult(totalResult);
    }
  }

  private boolean comprobarExistenciaOrden(int code){

    IteradorOrdenes iterador = objOrderManager.getCollectionHandler().getIteradorOrdenes();

    while(iterador.hasNext()){
      if(iterador.next()==code){
        return true;
      }
    }

    return false;
  }

  public ButtonHandler() {}

  public ButtonHandler(OrderManager inObjOrderManager) {
    objOrderManager = inObjOrderManager;
    factoriaOrdenes = new OrdersFactory();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    switch (( (AbstractButton) e.getSource()).getActionCommand()) {
      case OrderManager.VIEW_ROW:
          System.out.println("View in button");
          break;
      case OrderManager.MODIFY_ROW:
          objOrderManager.modify();
          break;
      case OrderManager.DELETE_ROW:
          objOrderManager.removeRow();
          break;
      default:
          break;
  }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}