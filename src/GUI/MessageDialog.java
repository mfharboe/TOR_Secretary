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

    public static MessageDialog getInstance() {
        if (m_instance == null) {
            m_instance = new MessageDialog();
        }
        return m_instance;
    }
    
    public String cmbFireman(){
        return "Alle Medarbejdere...";
    }
    
    public void selectDatesText(){
        JOptionPane.showMessageDialog(null, "Vælg fra og til dato");
    }
    public void reverseDatesText(){
        JOptionPane.showMessageDialog(null, "Kan ikke vælge dato til der er før end dato fra og omvendt");
    }
    
    public void printedConfirmMessage(){
        JOptionPane.showMessageDialog(null, "Rapport oprettet");
    }
    
    @Override
    public void update(String error) {
        JOptionPane.showMessageDialog(null, error);
    }
    
    
}