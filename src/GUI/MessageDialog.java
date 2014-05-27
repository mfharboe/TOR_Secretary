/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Observer.IObserver;
import javax.swing.JOptionPane;

/**
 *
 * @author Morten
 */
public class MessageDialog implements IObserver{

    private static MessageDialog m_instance;

    private MessageDialog() {
    }
/**
 * 
 * @return current m_instance of MessageDialog
 */
    public static MessageDialog getInstance() {
        if (m_instance == null) {
            m_instance = new MessageDialog();
        }
        return m_instance;
    }
    /**
     * 
     * @return text for Fireman combo box
     */
    public String cmbFireman(){
        return "Alle Medarbejdere...";
    }
    /**
     * Dialog if the dates are not filled
     */
    public void selectDatesText(){
        JOptionPane.showMessageDialog(null, "Vælg fra og til dato");
    }
    /**
     * Dialoges of the entered dates are reversed
     */
    public void reverseDatesText(){
        JOptionPane.showMessageDialog(null, "Kan ikke vælge dato til der er før end dato fra og omvendt");
    }
    /**'
     * Dialoges if it is successfully printed
     */
    public void printedConfirmMessage(){
        JOptionPane.showMessageDialog(null, "Rapport oprettet");
    }
    /**
     * Observer method for observer pattern
     * @param error 
     */
    @Override
    public void update(String error) {
        JOptionPane.showMessageDialog(null, error);
    }
    
    
}