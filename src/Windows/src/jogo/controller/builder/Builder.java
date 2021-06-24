package jogo.controller.builder;

import jogo.controller.gamecontroller.CellController;
import jogo.controller.gamecontroller.TurnController;
import jogo.model.boardmodel.IBoardController;
import jogo.model.boardmodel.IBoardModelBuilder;
import jogo.model.boardmodel.IPlayerController;
import jogo.model.events.IEventManager;
import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.mouse.IMouse;
import jogo.view.screen.IScreenManager;
import jogo.view.ui.IStats;
import jogo.view.ui.IUIManager;

public class Builder implements IGameBuilder{
	private IBoardModelBuilder model;
	private IScreenManager screen;
	private IUIManager ui_manager;
	private IMouse mouse;
	private IBoard3DManager board_view_manager;
	private IEventManager event_manager;
	
	public void connect(IBoardModelBuilder model) {
		this.model = model;
	}
	
	public void connect(IScreenManager screen) {
		this.screen = screen;
	}
	
	public void connect(IUIManager ui_manager) {
		this.ui_manager = ui_manager;
	}
	
	public void connect(IMouse mouse) {
		this.mouse = mouse;
	}
	
	public void connect(IBoard3DManager board_view_manager) {
		this.board_view_manager = board_view_manager;
	}
	
	public void connect(IEventManager event_manager) {
		this.event_manager = event_manager;
	}
	
	public void buildGame() {
		
		
		IBoardController board_model = model.getBoardController();
		IPlayerController player = model.getPlayer();
		
		//setups the screen
		screen.set2D(ui_manager.getContainer());
		screen.set3D(board_view_manager);
		
		screen.setMouse(mouse);
		ui_manager.setMouse(mouse);
		
		mouse.addDraggObservers("camera-dragg", board_view_manager.getCameraDraggObserver());
		mouse.addMotionObservers("camera-motion", board_view_manager.getCameraMotionObserver());
		mouse.addActionObservers("picker", board_view_manager.getCellPicker());
		
		IStats stats_view = ui_manager.getUI();
		
		event_manager.setBoard(model.getBoardEvent());
		
		TurnController controller = new TurnController(player,board_model,stats_view,board_view_manager,event_manager);
		stats_view.setTurnListener(controller);
		
		int map_length = board_model.getMapLength();
		int map_height = board_model.getMapHeight();
		
		for(int i=0;i<map_length;i++) {
			for(int j =0;j<map_height;j++) {
				CellController cell_controller = new CellController(i,j,controller);
				board_view_manager.setCellActionObserver(i, j, cell_controller);
			}
		}
		
		controller.updateStats();
		controller.updataMap();
	}

}
