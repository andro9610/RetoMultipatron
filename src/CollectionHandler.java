package src;

import java.util.Map;
import java.util.TreeMap;

public class CollectionHandler {

    Map<Integer, Order> orderObjList;
    public double orderTotal;

    public CollectionHandler(){
        orderObjList = new TreeMap<Integer, Order>();
    }
    
    public double getOrderTotal(){
        return orderTotal;
    }

    public IteradorOrdenes getIteradorOrdenes(){
        return new IteradorOrdenes(orderObjList);
    }

    public Order getOrder(int key){
        return orderObjList.get(key);
    }

    public boolean addOrder(int key, Order order){
        orderObjList.put(key, order);
        return true;
    }

    public Order removeOrder(int key){
        return orderObjList.remove(key);
    }
}