package jogo.model.boardmodel.components;

import jogo.model.boardmodel.CellModel;

public class PreserveForest extends ConstructableComponent{
	public PreserveForest() {
		this.rank = 3;
		this.cost = ConstructCostEnum.PRESERVEFOREST.getCost();
		this.setModifier(-4,-1,0);
	}
	
	public static boolean isConstructalbe(CellModel cell) {
		if(cell.hasComponent(Water.class) ) {
			return false;
		}
		else if(cell.hasComponent(Forest.class)&& cell.adjacentHas(City.class) && cell.adjacentHas(City.class)) {
			return true;
		}
		
		return false;
	}
}
