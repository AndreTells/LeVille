package jogo.controller.gamecontroller;

import jogo.model.boardmodel.IBoardController;
import jogo.model.events.IEventManager;
import jogo.model.player.IPlayerController;
import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.mouse.IActor;
import jogo.view.ui.IStats;

public interface IControllerBuilder {
	public IActor connectTurnController(IPlayerController player,IBoardController board_model,
			IStats stats_view,IBoard3DManager board_view_manager,IEventManager event_manager);
	
	public IActor connectCellController(int i,int j);

}
