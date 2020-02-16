public class ColombianOrder implements Order{
    private double orderAmount;
    private double additionalFFT;

    public ColombianOrder() {
    }
    public ColombianOrder(double inp_orderAmount,
            double inp_additionalFFT){
        orderAmount = inp_orderAmount;
        additionalFFT = inp_additionalFFT;
    }
    public double getOrderAmount(){
        return orderAmount;
    }
    public double getAdditionalFFT(){
        return additionalFFT;
    }
    public void accept(OrderVisitor v) {
        v.visit(this);
    }
}
