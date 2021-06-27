package jogo.model.boardmodel;

import java.util.List;

import jogo.model.boardmodel.components.Castle;
import jogo.model.boardmodel.components.City;
import jogo.model.boardmodel.components.Component;
import jogo.model.boardmodel.components.ConstructableComponent;
import jogo.model.boardmodel.components.Farm;
import jogo.model.boardmodel.components.LumberMill;
import jogo.model.boardmodel.components.PreserveForest;
import jogo.model.boardmodel.mapgenerator.MapGenerator;

public class BoardModel implements IBoardEvent, IBoardController, IBoardPlayer{
	private CellModel map[][];
	private int modifier[];
	/*
	 *modifier
	 0-> food
	 1->production
	 2->population_limit 
	*/
	
	public BoardModel() {
		create(10,10);
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
	
	public boolean addComponent(String comp_name,int x,int y,int player_production) {
		
		//trhow exeption
		ConstructableComponent comp = null;
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
			return false;
		}
		if(comp.construct(player_production)) {
			map[y][x].addComponent(comp);
			return true;
		}
		
		return false;
	}
	public boolean addComponent(Component comp,int x,int y) {
		map[y][x].addComponent(comp);
		return true;
	}
	
	
	public void removeComponente(Class<?> cls,int x,int y) {
		map[y][x].removeComponent(cls);
	}

	public void addModifier(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] += external_modifier[i];
		}
	}
	
	public void removeModifiers(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			
			modifier[i] -= external_modifier[i];
		}
	}
	
	public boolean hasComponent(Class<?> cls, int x, int y) {
		return map[y][x].hasComponent(cls);
	}
	
	public boolean isClaimed(int x,int y) {
		return map[y][x].isClaimed();
	}
	
	public void claim(int x,int y) {
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
