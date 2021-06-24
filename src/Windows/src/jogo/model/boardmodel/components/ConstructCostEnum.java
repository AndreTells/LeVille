package jogo.model.boardmodel.components;

public enum ConstructCostEnum {
	CLAIM(10),
	CITY(30),
	LUMBERMILL(40),
	FARM(40),
	CASTLE(50),
	PRESERVEFOREST(20);

	private int cost;
	private ConstructCostEnum(int cost){
		this.cost = cost;
	}
	
	public int getCost() {
		return this.cost;
	}
}
