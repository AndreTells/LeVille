package jogo.model.boardmodel;

import java.util.List;

import jogo.model.boardmodel.components.Castle;
import jogo.model.boardmodel.components.City;
import jogo.model.boardmodel.components.Component;
import jogo.model.boardmodel.components.ConstructCostEnum;
import jogo.model.boardmodel.components.ConstructableComponent;
import jogo.model.boardmodel.components.Farm;
import jogo.model.boardmodel.components.LumberMill;
import jogo.model.boardmodel.components.PreserveForest;
import jogo.model.boardmodel.mapgenerator.MapGenerator;
import jogo.model.events.EventManager;

public class BoardModel implements IBoardEvent, IBoardController{
	//private Player player;
	private CellModel map[][];
	private int modifier[];
	private int turn;
	/*
	 *modifier
	 0-> food
	 1->production
	 2->population_limit 
	*/
	
	public BoardModel() {
		create(10,10);
		turn = 0;
		MapGenerator map_generator = new MapGenerator(this);
		map_generator.generateRandomMap();
	}
	
	private void create(int map_height,int map_length) {
		map = new CellModel[map_height][map_length];
		for(int i =0;i< map_height;i++) {
			for(int j=0; j<map_length;j++) {
				map[i][j] = new CellModel(this,j,i,j-1 < 0 ? null:map[i][j-1],i-1 < 0 ? null:map[i-1][j]);
			}
		}

		modifier = new int [3];
		for(int i=0;i<modifier.length;i++) {
			modifier[i] = 0;
		}
	}
	
	public void addComponent(Component comp,int x,int y) {
		map[y][x].addComponent(comp);
	}
	
	public void removeComponente(Class cls,int x,int y) {
		map[y][x].removeComponent(cls);
	}

	public void addModifier(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] += external_modifier[i];
		}

		//System.out.println(modifier[0]+" "+modifier[1]+" "+modifier[2]);
	}
	
	public void removeModifiers(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			
			modifier[i] -= external_modifier[i];
		}
		System.out.println(modifier[0]+" "+modifier[1]+" "+modifier[2]);
	}
	
	public boolean hasComponent(Class<?> cls, int x, int y) {
		return map[y][x].hasComponent(cls);
	}
		
	//public String runRandomEvent() {
	//	return EventManager.ExecuteRandomEvent(this);
	//}
	
	public boolean isClaimed(int x,int y) {
		return map[y][x].isClaimed();
	}
	
	protected void claim(int x,int y) {
		map[y][x].claim();
	}
	
	//---- get methods 
	
	public String getCellHighestComponents(int x, int y) {
		return map[y][x].getHighestComponent();
	}
	
	public String getCellInfo(int x, int y) {
		return map[y][x].getInfo();
	}

	public int getMapHeight() {
		return map.length;
	}
	
	public int getMapLength() {
		return map[0].length;
	}

	public List<String> getPossibleActions(int x,int y){
		return map[y][x].getPossibleActions();
	}
	
	public int[] getModifier() {
		return modifier;
	}
	
}
