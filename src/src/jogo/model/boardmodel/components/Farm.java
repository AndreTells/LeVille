package jogo.model.boardmodel.components;

import jogo.model.boardmodel.CellModel;

public class Farm extends ConstructableComponent{
	public Farm () {
		this.rank = 3;
		this.cost = ConstructCostEnum.FARM.getCost();
		this.setModifier(14, 0,0);
	}
	
	
	public static boolean isConstructalbe(CellModel cell) {
		if(cell.hasComponent(Forest.class) || cell.hasComponent(Water.class)) {
			return false;
		}
		else if(cell.hasComponent(Grass.class)&& cell.adjacentHas(Water.class)) {
			return true;
		}
		
		
		return false;
	}
}
