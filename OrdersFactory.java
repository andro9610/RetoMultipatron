public class OrdersFactory{

    public OrdersFactory(){

    }

    public Order crearObjetoOrden(String orderType, double orderAmount){
        if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
                return new CaliforniaOrder();
            }
            if (orderType.equalsIgnoreCase(
                OrderManager.NON_CA_ORDER)) {
                return new NonCaliforniaOrder();
            }
            if (orderType.equalsIgnoreCase(
                OrderManager.OVERSEAS_ORDER)) {
                return new OverseasOrder();
            }
            if (orderType.equalsIgnoreCase(
                OrderManager.COLOMBIAN_ORDER)){
                return new ColombianOrder();
            }
            return null;
    }

}