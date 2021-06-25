package jogo.view.ui;

import jogo.view.mouse.IActor;

public interface IPopUpMenu {
	public void checkItem();
	
	public boolean allChecked();
	
	public void setActionObservers(IActor actors[]);
	
}
