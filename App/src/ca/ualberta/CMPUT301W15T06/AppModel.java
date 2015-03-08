/**
 * The <code>AppModel</code> class can create a hash set for listeners,
 * set up listeners, add a listener, remove a listener, 
 * and notify listeners for updates.
 * 
 * @author CMPUT301W15T06
 * @version 02/28/2015
 * @see java.util.ArrayList
 * @see java.util.HashSet
 * @see java.util.Set
 */

package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AppModel{
	
	private transient ArrayList<Listener> listners;
	private transient ArrayList<Listener> modelListners;
	private boolean missValue;
	
//	/**
//	 * This method creates a hash set for listeners that 
//	 * records all listener
//	 */
	public AppModel(){
		listners=new ArrayList<Listener>();
		modelListners=new ArrayList<Listener>();
	}
	
//	/**
//	 * This method allows user to add a listener to the 
//	 * hash set listeners
//	 * 
//	 * @param l  a Listener type l
//	 */
	public void addListener(Listener l){
		if (!getListeners().contains(l)){
			getListeners().add(l);
		}
	}
	
//	/**
//	 * This method allows user to remove a listener from
//	 * the hash set listeners
//	 * 
//	 * @param l  a Listener type l
//	 */
	public void removeListener(Listener l){
		getListeners().remove(l);
	}
	
	/**
	 * This method checks if there's any listeners, 
	 * if not, set up a new one and return it for further use
	 * 
	 * @return listeners
	 */
	private ArrayList<Listener> getListeners(){
		if (listners==null){
			listners=new ArrayList<Listener>();
		}
		return listners;
	}
	

//	/**
//	 * This method is design for notifying all listener 
//	 * in listeners hash set if there's any update
//	 */

	public void notifyListeners() {
		for (Listener  listener : getListeners()) {
			listener.update();
		}
		for (Listener  listener : getModelListeners()) {
			listener.update();
		}
		ClaimListManager.getInstance().save();
	}


	public void addModelListener(Listener l){
		if (!getModelListeners().contains(l)){
			getModelListeners().add(l);
		}
	}
	

	public void removeModelListener(Listener l){
		getModelListeners().remove(l);
	}
	

	private ArrayList<Listener> getModelListeners(){
		if (modelListners==null){
			modelListners=new ArrayList<Listener>();
		}
		return modelListners;
	}
	




	public void setMissValue(boolean b) {
		missValue=b;
		notifyListeners();
	}

	public boolean getMissValue() {
		return missValue;
	}
}