package src;

import java.awt.event.*;
import javax.swing.AbstractButton;

class ButtonHandler implements ActionListener, MouseListener {

  private Vista objOrderManager;
  private OrdersFactory factoriaOrdenes;
  private VisitorInterface objVisitorIncremental;
  private VisitorInterface objVisitorDecremental;
  private CollectionHandler objCollectionHandler;

  public void actionPerformed(ActionEvent e) {
    String totalResult = null;
    if (e.getActionCommand().equals(Vista.EXIT)) {
      System.exit(1);
    }
    else if (e.getActionCommand().equals(Vista.CREATE_ORDER)
        ) {
      //get input values
      String strCode = objOrderManager.getCode();
      String orderType = objOrderManager.getOrderType();
      Object[] obj = {strCode, orderType};
      String strOrderAmount =
        objOrderManager.getOrderAmount();
      String strAditional = objOrderManager.getAditional();

      int code = 0;
      double dblOrderAmount = 0.0;
      double dblAditional = 0.0;

      if(strCode.trim().length()==0){
        objOrderManager.setResult("El código no puede estár vacio");
        return;
      }
      if (strOrderAmount.trim().length() == 0) {
        strOrderAmount = "0.0";
      }
      if (strAditional.trim().length() == 0) {
        strAditional = "0.0";
      }

      code = Integer.parseInt(strCode);
      dblOrderAmount = Double.parseDouble(strOrderAmount);
      dblAditional = Double.parseDouble(strAditional);

      //Create the order
      Order order = factoriaOrdenes.createOrder(orderType, dblOrderAmount, dblAditional);

      if(comprobarExistenciaOrden(code)){
        objCollectionHandler.getOrder(code).accept(objVisitorDecremental);
        objCollectionHandler.addOrder(code, order);
        order.accept(objVisitorIncremental);
        objOrderManager.modifyRow(obj);
        objOrderManager.setResult("modified order");
      }
      else{
        objCollectionHandler.addOrder(code, order);
        order.accept(objVisitorIncremental);
        objOrderManager.addRow(obj);
        objOrderManager.setResult("Order Created Successfully");
      }
    }
    else if (e.getActionCommand().equals(Vista.GET_TOTAL)) {
      totalResult = ((Double) objCollectionHandler.getOrderTotal()).toString();
      totalResult = " Orders Total = " + totalResult;
      objOrderManager.setResult(totalResult);
    }
  }

  private boolean comprobarExistenciaOrden(int code){

    IteradorOrdenes iterador = objCollectionHandler.getIteradorOrdenes();

    while(iterador.hasNext()){
      if(iterador.next()==code){
        return true;
      }
    }

    return false;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    switch (( (AbstractButton) e.getSource()).getActionCommand()) {
      case Vista.VIEW_ROW:
          System.out.println("Not supported yet.");
          break;
      case Vista.MODIFY_ROW:
          objOrderManager.modify();
          break;
      case Vista.DELETE_ROW:
          Integer key = Integer.parseInt(objOrderManager.getOrderValue());
          objCollectionHandler.removeOrder(key).accept(objVisitorDecremental);
          objOrderManager.removeRow();
          break;
      default:
          break;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseClicked(MouseEvent e) {}

  public ButtonHandler() {}

  public ButtonHandler(Vista inObjOrderManager) {
    objCollectionHandler = new CollectionHandler();
    objVisitorIncremental = new OrderVisitorIncremental(objCollectionHandler);
    objVisitorDecremental = new OrderVisitorDecremental(objCollectionHandler);
    objOrderManager = inObjOrderManager;
    factoriaOrdenes = new OrdersFactory();
  }

}