class OrderVisitorIncremental implements VisitorInterface {

    CollectionHandler objCollectionHandler;

    public OrderVisitorIncremental(CollectionHandler objCollectionHandler) {
        this.objCollectionHandler = objCollectionHandler;
    }

    public void visit(NonCaliforniaOrder inp_order){
        objCollectionHandler.orderTotal = objCollectionHandler.orderTotal + inp_order.getOrderAmount();
    }
    public void visit(CaliforniaOrder inp_order){
        objCollectionHandler.orderTotal = objCollectionHandler.orderTotal + inp_order.getOrderAmount() +
                  inp_order.getAdditionalTax();
    }
    public void visit(OverseasOrder inp_order){
        objCollectionHandler.orderTotal = objCollectionHandler.orderTotal + inp_order.getOrderAmount() +
                  inp_order.getAdditionalSH();
    }
    public void visit(ColombianOrder inp_order){
        objCollectionHandler.orderTotal = objCollectionHandler.orderTotal + inp_order.getOrderAmount() +
                  inp_order.getAdditionalFFT();
    }

}
