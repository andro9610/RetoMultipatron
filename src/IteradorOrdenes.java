package src;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class IteradorOrdenes implements Iterator {

    private final Map<Integer, Order> coleccion;
    private int index = 0;
    private final Object[] keyArray;
    private Integer nextCandidate;

    public IteradorOrdenes(Map<Integer, Order> coleccion) {
        this.coleccion = coleccion;
        System.out.println("Aqui entro-------------------------");
        keyArray =  coleccion.keySet().toArray();
    }

    public boolean hasNext() {
        if(index < keyArray.length && keyArray[index] != null ){
            nextCandidate = (Integer) keyArray[index];
            index++;
            return true;
        }
        return false;
    }

    public Integer next() {
        if (nextCandidate == null) {
            throw new NoSuchElementException();
        } else {
            return nextCandidate;
        }
    }

}