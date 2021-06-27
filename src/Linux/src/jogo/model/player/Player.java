package jogo.model.player;

import jogo.model.boardmodel.IBoardPlayer;

public class Player implements IPlayerController{
	private int population;
	private int population_limit;
	private int production;
	private int food;
	private int food_target;
	private IBoardPlayer board;
	
	public Player() {
		population = 1;
		population_limit = 1;
		production = 80;
		food = 0;
		food_target = 6;
	}
	public void connect(IBoardPlayer board) {
		this.board = board;
	}
	
	public int getPopulationValue() {
		return population;
	}
	
	public int getPopulationLimitValue() {
		return population_limit;
	}

	public int getProductionValue() {
		return production;
	}

	public int getFoodValue() {
		return food;
	}
	
	public int getFoodTargetValue() {
		return food_target;
	}
	
	public void claim(int x,int y) {
		if(production>=ConstructCostEnum.CLAIM.getCost()) {
			board.claim(x,y);
			useProduction(ConstructCostEnum.CLAIM.getCost());		
		}	
	}
	
	private void useProduction(int value) {
		production -=value;
	}
	
	public void addModifier(int modifier[]) {
		food += modifier[0];
		production += modifier[1];
		if(production < 0) {
			production =0;
		}
		population_limit += modifier[2];
		modifier[2] = 0;
		while(food >= food_target) {
			food -= food_target;
			population +=1;
			modifier[0] -=1;
			food_target = (population*2) +3;
		}
	}
	
	public  void constructComponent(String comp_name,int x, int y) {
		if(board.addComponent(comp_name,x,y,this.production)) {
			int cost = 0;
			switch(comp_name) {
				case"City":
					cost = ConstructCostEnum.CITY.getCost();
					break;
				case"Farm":
					cost = ConstructCostEnum.FARM.getCost();
					break;
				case"LumberMill":
					cost = ConstructCostEnum.LUMBERMILL.getCost();
					break;
				case"Castle":
					cost = ConstructCostEnum.CASTLE.getCost();
					break;
				case"PreserveForest":
					cost = ConstructCostEnum.PRESERVEFOREST	.getCost();
					break;
			}
			this.useProduction(cost);
		}
	}

}
