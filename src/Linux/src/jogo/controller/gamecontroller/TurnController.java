package jogo.controller.gamecontroller;

import java.awt.event.MouseEvent;

import jogo.model.boardmodel.IBoardController;
import jogo.model.events.IEventManager;
import jogo.model.player.IPlayerController;
import jogo.view.boardview3d.IBoard3DManager;
import jogo.view.mouse.IActor;
import jogo.view.ui.IPopUpMenu;
import jogo.view.ui.IStats;

public class TurnController implements IActor{
	private IEventManager event_manager;
	private boolean game_over;
	protected IPlayerController player;
	protected IBoardController board;
	protected IStats stats_view;
	protected IBoard3DManager board_view_manager;
	
	
	public TurnController(IPlayerController player,IBoardController board,IStats stats_view,IBoard3DManager board_view_manager,IEventManager event_manager) {
		this.event_manager = event_manager;
		this.player = player;
		this.board = board;
		this.stats_view = stats_view;
		this.board_view_manager = board_view_manager;
		game_over = false;
	}
	
	public void act(MouseEvent e) {
		
		if(!GameWasOver()) {
			int [] modifier = board.getModifier();
			
			player.addModifier(modifier);
			
			int population = player.getPopulationValue();
			int population_limit = player.getPopulationLimitValue();
			int food = player.getFoodValue();
			int food_target = player.getFoodTargetValue();
			
			updateStats();
			if(!gameOver()){
				
				String description = event_manager.ExecuteRandomEvent();
				IPopUpMenu menu = stats_view.createSubMenu("_event-popup",-0.99f,0.8f,description,new String[]{"ok"});
				String info_help_text = "";
				if(population == population_limit) {
					info_help_text += " -you are close to the\n   population limit\n";
				}
				if(modifier[1] <= 20) {
					info_help_text += " -you are not getting\n   much production\n";
				}
				if(food + modifier[0] >= food_target) {
					info_help_text += " -you are close to\n   getting more \n   population\n";
				}
				
				stats_view.setInfo(info_help_text);
						
				EventPopUpController pop_up_controller = new EventPopUpController(this);
				menu.setActionObservers(new IActor[] {pop_up_controller});
				
				updateStats();
				
			}
			
		}
	}
	
	public boolean gameOver() {
		int population = player.getPopulationValue();
		int population_limit = player.getPopulationLimitValue();
		int [] modifier = board.getModifier();
		
		if(population > population_limit || population == 0) {
			game_over = true;
			stats_view.setInfo("GAME LOST \nBETTER LUCK NEXT \nTIME");
			return true;
		}
		if(modifier[0]<=0) {
			game_over = true;
			stats_view.setInfo("GAME LOST \nBETTER LUCK NEXT \nTIME");
			return true;
		}
		
		if(population >=20) {
			game_over = true;
			stats_view.setInfo("GAME WON YAY!!!!!!");
			return true;
		}
		return false;
	}
	
	public boolean GameWasOver() {
		return this.game_over;
	}
	
	public void updataMap() {
		int map_length = board.getMapLength();
		int map_height = board.getMapHeight();
		
		for(int i=0;i<map_length;i++) {
			for(int j =0;j<map_height;j++) {
				board_view_manager.getCell(i, j).setObj(board.getCellHighestComponents(i, j));
			}
		}
	}
	
	public void updateStats() {
		int[] modifier =board.getModifier();
		stats_view.setPopulation("population: "+player.getPopulationValue()+"/"+player.getPopulationLimitValue()+(modifier[2]>=0?"+"+modifier[2]:modifier[2]));
		stats_view.setProduction("production: "+player.getProductionValue()+(modifier[1]>=0?"+"+modifier[1]:modifier[1]));
		stats_view.setFood("food: "+player.getFoodValue()+"/"+player.getFoodTargetValue()+(modifier[0]>=0?"+"+modifier[0]:modifier[0]));
	}

	@Override
	public void act(MouseEvent e, boolean missed) {
		if(!missed) {
			act(e);
		}
	}
	
}
