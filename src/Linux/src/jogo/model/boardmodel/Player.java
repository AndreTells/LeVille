package jogo.model.boardmodel;

import jogo.model.boardmodel.components.Castle;
import jogo.model.boardmodel.components.City;
import jogo.model.boardmodel.components.ConstructCostEnum;
import jogo.model.boardmodel.components.ConstructableComponent;
import jogo.model.boardmodel.components.Farm;
import jogo.model.boardmodel.components.LumberMill;
import jogo.model.boardmodel.components.PreserveForest;

public class Player implements IPlayerController{
	private int population;
	private int population_limit;
	private int production;
	private int food;
	private int food_target;
	private BoardModel board;
	
	protected Player(BoardModel board) {
		population = 1;
		population_limit = 1;
		production = 80;
		food = 0;
		food_target = 6;
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
		board.claim(x,y);
		useProduction(ConstructCostEnum.CLAIM.getCost());		
	}
	
	public void useProduction(int value) {
		production -=value;
	}
	
	public void addModifier(int modifier[]) {
		food += modifier[0];
		production += modifier[1];
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
		//trhow exeption
		ConstructableComponent comp = null;
		System.out.println(comp_name);
		if(comp_name.equals("City")) {
			comp = new City();
		}
		else if(comp_name.equals("Farm")) {
			comp = new Farm();
		}
		else if(comp_name.equals("LumberMill")) {
			comp = new LumberMill();
		}
		else if(comp_name.equals("Castle")) {
			comp = new Castle();
		}
		else if(comp_name.equals("PreserveForest")) {
			comp = new PreserveForest();
		}
		
		else{
			return;
		}
		
		if(comp.construct(this)) {
			board.addComponent(comp,x,y);
		}
	}

}
