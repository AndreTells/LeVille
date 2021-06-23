package jogo.controller.gamecontroller;

import java.awt.event.MouseEvent;
import java.util.List;

import jogo.view.mouse.IActor;
import jogo.view.ui.IPopUpMenu;

public class CellController implements IActor{
	private int map_x;
	private int map_y;
	private TurnController controller;
	
	public CellController(int map_x,int map_y,TurnController controller) {
		this.map_x = map_x;
		this.map_y = map_y;
		this.controller = controller;
	}
	@Override
	public void act(MouseEvent e) {
		// TODO Auto-generated method stub
		controller.stats_view.setInfo("cell info: \n"+controller.board.getCellInfo(map_x, map_y));
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			float formated_x = ((2.0f*e.getX())/e.getComponent().getWidth()) - 1.0f;
			float formated_y = 1.0f - (2.0f * e.getY())/e.getComponent().getHeight();
			
			if(!controller.board.isClaimed(map_x, map_y)) {
				IPopUpMenu menu = controller.stats_view.createSubMenu("_construct-popup",formated_x,
						formated_y,0.2f,
						new String[] {"Claim"});
				ConstructPopUpController item_controller = new ConstructPopUpController(controller,map_x,map_y,"Claim");
				menu.setActionObservers(new ConstructPopUpController[] {item_controller});
				item_controller.setPopUpMenu(menu);
				return;
			}
			
			List<String> possible = controller.board.getPossibleActions(map_x, map_y);

			String[] possible_arr = new String[possible.size()];
			possible.toArray(possible_arr);
			
			IPopUpMenu menu = controller.stats_view.createSubMenu("_construct-popup",formated_x,
					formated_y,0.2f,
					possible_arr);
			
			ConstructPopUpController[] menu_item_controllers = new ConstructPopUpController[possible.size()]; 
			for(int i=0;i<possible.size();i++) {
				menu_item_controllers[i] = new ConstructPopUpController(controller,map_x,map_y,possible.get(i));
				menu_item_controllers[i].setPopUpMenu(menu);
			}
			menu.setActionObservers(menu_item_controllers);
		}
	}
	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
		}
	}

}
