package jogo.view.ui;

import jogo.view.mouse.IMouse;
import jogo.view.ui.composite.GLContainer;

public class UIManager implements IUIManager{
	private UI ui;
	private GLContainer container;
	
	public UIManager() {
		container =  new GLContainer("container",1000,600);
		ui = new UI(container);
	}
	
	public void setMouse(IMouse mouse) {
		GLElementComponent.setMouse(mouse);
	}
	
	public IContainer getContainer() {
		return container;
	}

	public IStats getUI() {
		return ui;
	}
}
