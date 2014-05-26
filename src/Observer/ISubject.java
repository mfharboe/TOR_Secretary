/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

/**
 *
 * @author Morten
 */
public interface ISubject {
    
    public void register(IObserver o);
    public void unregister(IObserver o);
    public void notifyObservers();
    
}
