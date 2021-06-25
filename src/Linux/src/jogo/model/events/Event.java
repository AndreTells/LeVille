 package jogo.model.events;

import jogo.model.boardmodel.IBoardEvent;

public abstract class Event{
	protected String text;
	
	protected Event(String text) {
		this.text = text;
	}
	
	public String toString() {
		return ""+text+".\n"+this.getDescription();
	}
	
	public abstract String executeEvent(IBoardEvent board);
	
	public abstract String getDescription();
}
