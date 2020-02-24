package src;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

public class TableEvent implements MouseListener {

    private JTable tabla;

    public TableEvent(JTable tabla) {
        this.tabla = tabla;
    }

    private void operacion(MouseEvent e) {
        TableColumnModel columnModel = tabla.getColumnModel();
        int column = columnModel.getColumnIndexAtX(e.getX());
        int row = e.getY() / tabla.getRowHeight();
        Object value;
        JButton button;
        MouseEvent buttonEvent;

        if (row >= tabla.getRowCount() || row < 0 || column >= tabla.getColumnCount() || column < 0)
            return;

        value = tabla.getValueAt(row, column);

        if ((value instanceof JButton)){
            button = (JButton) value;
            buttonEvent = SwingUtilities.convertMouseEvent(tabla, e, button);
            button.dispatchEvent(buttonEvent);
            button.repaint();
            tabla.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        operacion(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        operacion(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        operacion(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        operacion(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        operacion(e);
    }
}