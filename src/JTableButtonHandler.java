package src;

import java.awt.event.*;
import javax.swing.AbstractButton;

public class JTableButtonHandler implements MouseListener {

    OrderManager objOrderManager;

    public JTableButtonHandler(OrderManager objOrderManager){
        this.objOrderManager = objOrderManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        switch (( (AbstractButton) e.getSource()).getActionCommand()) {
            case OrderManager.VIEW_ROW:
                System.out.println("View in button");
                break;
            case OrderManager.MODIFY_ROW:
                objOrderManager.modify();
                break;
            case OrderManager.DELETE_ROW:
                objOrderManager.removeRow();
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    
}