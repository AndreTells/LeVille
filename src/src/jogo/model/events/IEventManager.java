package jogo.model.events;

import jogo.model.boardmodel.IBoardEvent;

public interface IEventManager {
	
	public String ExecuteRandomEvent();
	
	public void setBoard(IBoardEvent board);
	
}
