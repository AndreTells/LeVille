package jogo.view.ui;

import jogo.view.mouse.IMouse;

public interface IUIManager {
	public IContainer getContainer();
	
	public IStats getUI();
	
	public void setMouse(IMouse mouse);
}
