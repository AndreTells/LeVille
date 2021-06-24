package jogo.model.boardmodel.components;

import jogo.model.boardmodel.CellModel;

public class Castle extends ConstructableComponent{
	public Castle() {
		this.rank = 3;
		this.cost = ConstructCostEnum.CASTLE.getCost();
		this.setModifier(-6,2,3);
	}
	
	public static boolean isConstructalbe(CellModel cell) {
		if(cell.hasComponent(Forest.class) || cell.hasComponent(Water.class) ) {
			return false;
		}
		else if(cell.hasComponent(Grass.class)&& cell.adjacentHas(Mountain.class) && cell.adjacentHas(City.class)) {
			return true;
		}
		
		return false;
	}
}
