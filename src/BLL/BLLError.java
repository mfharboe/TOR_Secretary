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

    public static BLLError getInstance() {
        if (m_instance == null) {
            m_instance = new BLLError();
        }
        return m_instance;
    }

    public void dbError() {
        error = "Der er sket en fejl, handlingen afsluttes";
        notifyObservers();
    }

    public void readIncidentError() {
        error = "Kunne ikke læse indsatser";
        notifyObservers();
    }

    public void readSalaryError() {
        error = "Kunne ikke indlæse tids rapport";
        notifyObservers();
    }

    public void readFiremanError() {
        error = "Kunne ikke indlæse Brandmænd";
        notifyObservers();
    }

    public void readUsageError() {
        error = "Kunne ikke indlæse forbrug";
        notifyObservers();
    }

    public void readIncidentDetailsError() {
        error = "Kunne ikke indlæse indsats detaljer";
        notifyObservers();
    }

    @Override
    public void register(IObserver o) {
        observers.add(o);
    }

    @Override
    public void unregister(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(error);

        }
    }
}
