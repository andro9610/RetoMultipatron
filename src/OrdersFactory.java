package src;

public class OrdersFactory{

    public OrdersFactory(){}

    public Order createOrder(String orderType,
        double orderAmount, double aditional){
        if (orderType.equalsIgnoreCase(Vista.CA_ORDER)) {
            return new CaliforniaOrder(orderAmount, aditional);
        }
        else if (orderType.equalsIgnoreCase(
            Vista.NON_CA_ORDER)) {
            return new NonCaliforniaOrder(orderAmount);
        }
        else if (orderType.equalsIgnoreCase(
            Vista.OVERSEAS_ORDER)) {
            return new OverseasOrder(orderAmount, aditional);
        }
        else if (orderType.equalsIgnoreCase(
            Vista.COLOMBIAN_ORDER)){
            return new ColombianOrder(orderAmount,aditional);
        }
        return null;
    }

}