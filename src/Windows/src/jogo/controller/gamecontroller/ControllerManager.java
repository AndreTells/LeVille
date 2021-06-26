package jogo.controller.gamecontroller;

import jogo.model.boardmodel.IBoardController;
import jogo.model.events.IEventManager;
import jogo.model.player.IPlayerController;
import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.mouse.IActor;
import jogo.view.ui.IStats;

public class ControllerManager implements IControllerBuilder{
	private TurnController controller;
	
	public IActor connectTurnController(IPlayerController player,IBoardController board_model,
			IStats stats_view,IBoard3DManager board_view_manager,IEventManager event_manager) {
		controller = new TurnController(player,board_model,stats_view,board_view_manager,event_manager);
		controller.updateStats();
		controller.updataMap();
		return controller;
	}
	
	public IActor connectCellController(int i,int j) {
		if(controller == null) {
			return null;
		}
		return new CellController(i,j,controller);
		
	}
}
