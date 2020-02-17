import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class IteradorOrdenes implements Iterator {

    private final Map<Integer, Order> coleccion;
    private int index = 0;
    private final Integer[] keyArray;
    private Order nextCandidate;

    public IteradorOrdenes(Map<Integer, Order> coleccion) {
        this.coleccion = coleccion;
        keyArray = (Integer[]) coleccion.keySet().toArray();
    }

    public boolean hasNext() {
        if(index < keyArray.length && keyArray[index] != null ){
            nextCandidate = coleccion.get(keyArray[index]);
            index++;
            return true;
        }
        return false;
    }

    public Object next() {
        if (nextCandidate == null) {
            throw new NoSuchElementException();
        } else {
            return nextCandidate;
        }
    }

}