package jogo.controller.gamecontroller;

import java.awt.event.MouseEvent;

import jogo.view.mouse.IActor;
import jogo.view.ui.IPopUpMenu;

public class ConstructPopUpController implements IActor{
	private TurnController controller;
	private int map_x;
	private int map_y;
	private String command;
	private IPopUpMenu menu;
	
	public ConstructPopUpController(TurnController controller,int map_x,int map_y,String  command) {
		this.controller = controller;
		this.map_x = map_x;
		this.map_y = map_y;
		this.command = command;
	}
	
	@Override
	public void act(MouseEvent e) {
		if(command.equals("Claim")) {
			controller.player.claim(map_x, map_y);

			controller.stats_view.setInfo(controller.board.getCellInfo(map_x, map_y));
			controller.updateStats();
			return;
		}
		else if(command.equals("None")) {
			return;
		}
		controller.player.constructComponent(command, map_x, map_y);
		controller.stats_view.setInfo(controller.board.getCellInfo(map_x, map_y));
		controller.updateStats();
		controller.updataMap();
	}
	
	public void setPopUpMenu(IPopUpMenu menu) {
		this.menu = menu;
	}

	//throws exception if no popup menu was set
	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
			controller.stats_view.disposeChild("_construct-popup");
		}
		
		menu.checkItem();
		if(menu.allChecked()) {
			controller.stats_view.disposeChild("_construct-popup");
		}
		
	}

}
