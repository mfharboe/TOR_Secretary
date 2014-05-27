/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import Observer.IObserver;
import Observer.ISubject;
import java.util.ArrayList;

/**
 *
 * @author Morten
 */
public class BLLError implements ISubject {

    private static BLLError m_instance;
    private String error;
    private ArrayList<IObserver> observers;

    private BLLError() {
        observers = new ArrayList<>();

    }
/**
 * 
 * @return current instance of BLLError 
 */
    public static BLLError getInstance() {
        if (m_instance == null) {
            m_instance = new BLLError();
        }
        return m_instance;
    }
/**
 * Text for database error
 */
    public void dbError() {
        error = "Der er sket en fejl, handlingen afsluttes";
        notifyObservers();
    }
/**
 * Text for incident error
 */
    public void readIncidentError() {
        error = "Kunne ikke læse indsatser";
        notifyObservers();
    }
/**
 * Text for salary error
 */
    public void readSalaryError() {
        error = "Kunne ikke indlæse tids rapport";
        notifyObservers();
    }
/**
 * Text for fireman error
 */
    public void readFiremanError() {
        error = "Kunne ikke indlæse Brandmænd";
        notifyObservers();
    }
/**
 * Text for usage error
 */
    public void readUsageError() {
        error = "Kunne ikke indlæse forbrug";
        notifyObservers();
    }
/**
 * Text for incident Details error
 */
    public void readIncidentDetailsError() {
        error = "Kunne ikke indlæse indsats detaljer";
        notifyObservers();
    }
/**
 * Register new observers
 * @param o 
 */
    @Override
    public void register(IObserver o) {
        observers.add(o);
    }
/**
 * Unregister observers
 * @param o 
 */
    @Override
    public void unregister(IObserver o) {
        observers.remove(o);
    }
/**
 * Notify registered observers.
 */
    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(error);

        }
    }
}
