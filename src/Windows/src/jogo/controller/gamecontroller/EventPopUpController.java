package jogo.controller.gamecontroller;

import java.awt.event.MouseEvent;

import jogo.view.mouse.IActor;

public class EventPopUpController implements IActor{
	private TurnController controller;
	
	EventPopUpController(TurnController controller){
		this.controller = controller;
	}
	
	@Override
	public void act(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void act(MouseEvent e, boolean missed) {
		controller.stats_view.disposeChild("_event-popup");
	}
	
}
