package src;

public class OrdersFactory{

    public OrdersFactory(){}

    public Order createOrder(String orderType,
        double orderAmount, double tax, double SH, double FFT){
        if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
            return new CaliforniaOrder(orderAmount, tax);
        }
        else if (orderType.equalsIgnoreCase(
            OrderManager.NON_CA_ORDER)) {
            return new NonCaliforniaOrder(orderAmount);
        }
        else if (orderType.equalsIgnoreCase(
            OrderManager.OVERSEAS_ORDER)) {
            return new OverseasOrder(orderAmount, SH);
        }
        else if (orderType.equalsIgnoreCase(
            OrderManager.COLOMBIAN_ORDER)){
            return new ColombianOrder(orderAmount,FFT);
        }
        return null;
    }

}