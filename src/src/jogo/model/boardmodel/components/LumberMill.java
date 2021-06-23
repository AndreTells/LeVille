package jogo.model.boardmodel.components;

import jogo.model.boardmodel.CellModel;

public class LumberMill extends ConstructableComponent{
	public LumberMill () {
		this.rank = 3;
		this.cost = ConstructCostEnum.LUMBERMILL.getCost();
		this.setModifier(0, 14,0);
	}
	
	
	public static boolean isConstructalbe(CellModel cell) {
		if(cell.hasComponent(Forest.class)) {
			return true;
		}
		
		
		return false;
	}
}
