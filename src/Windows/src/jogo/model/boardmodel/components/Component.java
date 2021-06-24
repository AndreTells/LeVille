package jogo.model.boardmodel.components;

import jogo.model.boardmodel.CellModel;

public abstract class Component {
	protected CellModel cell;
	protected int rank;
	protected int modifier[];
	protected int cost;
	/*
	 * modifier index
	 0-> food
	 1->production
	 2->population_limit 
	*/
	
	public int[] getModifier() {
		return modifier;
	}
	
	public boolean greaterThan(Component comp) {
		if(this.rank > comp.rank) {
			return true;
		}
		return false;
	}

	public boolean setCell(CellModel cell) {
		this.cell = cell;
		cell.addModifiers(modifier);
		return true;
	}	
	
	
	public static boolean isConstructalbe(CellModel cell) {
		return false;
	}
	
	protected void setModifier(int food,int production,int population) {
		modifier = new int[3];
		modifier[0] = food;
		modifier[1] = production;
		modifier[2] = population;
	}

}
