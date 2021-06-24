package jogo.view.mouse;

import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Dictionary;
import java.util.Hashtable;

public class GLMouse implements IMouse {

	private Dictionary<String,IMouseObserver> dic_action_observers;
	private Dictionary<String,IMouseObserver> dic_motion_observers;
	private Dictionary<String,IMouseObserver> dic_dragg_observers;
	
	//implement try catch for this
	public GLMouse() {
		this.dic_action_observers = new Hashtable<String,IMouseObserver>();
		this.dic_motion_observers = new Hashtable<String,IMouseObserver>();

		this.dic_dragg_observers = new Hashtable<String,IMouseObserver>();
		
	}
	
	//do add method for action and motion observers
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		checkObservers(e,dic_action_observers);
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void mouseDragged(MouseEvent e) {
		checkObservers(e,dic_dragg_observers);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		checkObservers(e,dic_motion_observers);
	}

	private void checkObservers(MouseEvent e,Dictionary<String,IMouseObserver> dic_observers) {
		float formated_x = ((2.0f*e.getX())/e.getComponent().getWidth()) - 1.0f;
		float formated_y = 1.0f - (2.0f * e.getY())/e.getComponent().getHeight();
		
		Enumeration<IMouseObserver> observers = dic_observers.elements();
		IMouseObserver selected_observer = null;
		int selected_observer_rank = -1;
		
		while(observers.hasMoreElements()) {
			IMouseObserver observer = observers.nextElement();
			if(observer.conditonIsMet(formated_x,formated_y)) {
				
				int rank = observer.getRank();
				
				if(rank>selected_observer_rank) {
					if(selected_observer!=null) {
						selected_observer.performAction(e,true);
					}
					
					selected_observer = observer;
					selected_observer_rank = observer.getRank();
				}
				else if(rank == selected_observer_rank) {
					//throw exception
				}
			}
			
			else{
				observer.performAction(e, true);
			}
		
		}
		
		if(selected_observer!= null) {
			selected_observer.performAction(e,false);
		}
	}
	
	public void addActionObservers(String id ,IMouseObserver observer) {
		dic_action_observers.put(id, observer);
	}
	
	public void removeActionObserver(String id) {
		dic_action_observers.remove(id);
	}
	
	public void addMotionObservers(String id ,IMouseObserver observer) {
		dic_motion_observers.put(id, observer);
	}
	
	public void removeMotionObserver(String id) {
		dic_motion_observers.remove(id);
	}
	
	public void addDraggObservers(String id ,IMouseObserver observer) {
		dic_dragg_observers.put(id, observer);
		
	}
	
	public void removeDraggObserver(String id) {
		dic_dragg_observers.remove(id);
	}
}
