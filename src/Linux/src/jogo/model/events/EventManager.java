package jogo.model.events;

import java.util.LinkedList;
import java.util.List;

import jogo.model.boardmodel.IBoardEvent;

public class EventManager implements IEventManager{
	private List<Event> events;
	private IBoardEvent board;
	
	public EventManager() {
		events = new LinkedList<Event>();
		String path =  System.getProperty("user.dir") + "/assets/events/";
		events.addAll(CityEvent.getEvents(path+"CityEvents.csv"));
	}
	
	public void setBoard(IBoardEvent board) {
		this.board = board;
	}
	
	public String ExecuteRandomEvent() {
		int index = getRandomNumber(0,events.size());
		return events.get(index).executeEvent(board);
	}
	
	private int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}
