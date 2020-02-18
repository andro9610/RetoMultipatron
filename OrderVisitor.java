import java.util.*;

class OrderVisitor implements VisitorInterface {
  Map<Integer, Order> orderObjList;
  private double orderTotal;

  public OrderVisitor() {
    orderObjList = new TreeMap<Integer, Order>();
  }
  public void visit(NonCaliforniaOrder inp_order) {
    orderTotal = orderTotal + inp_order.getOrderAmount();
  }
  public void visit(CaliforniaOrder inp_order) {
    orderTotal = orderTotal + inp_order.getOrderAmount() +
                 inp_order.getAdditionalTax();
  }
  public void visit(OverseasOrder inp_order) {
    orderTotal = orderTotal + inp_order.getOrderAmount() +
                 inp_order.getAdditionalSH();
  }

  public void visit(ColombianOrder inp_order) {
    orderTotal = orderTotal + inp_order.getOrderAmount() +
                 inp_order.getAdditionalFFT();
  }

  public IteradorOrdenes getIteradorOrdenes(){
    return new IteradorOrdenes(orderObjList);
  }

  public double getOrderTotal() {
    return orderTotal;
  }

}
