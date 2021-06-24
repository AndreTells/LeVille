package jogo.model.boardmodel.mapgenerator;

import jogo.model.boardmodel.BoardModel;
import jogo.model.boardmodel.components.Forest;
import jogo.model.boardmodel.components.Grass;
import jogo.model.boardmodel.components.Mountain;
import jogo.model.boardmodel.components.Water;

//-----------------------------------------------------------------------------

public class MapGenerator {
	private BoardModel board;
	public MapGenerator(BoardModel board) {
		this.board = board;
	}
	
	public void generateRandomMap() {
		generateMapBase(0.2);
		generateMapRiver();
		generateMapRiver();
		generateMapForest(0.05);
	}
	
	private void generateMapBase(double limit){
		int map_height = board.getMapHeight();
		int map_length = board.getMapLength();
		for(int i=0; i < map_height;i++) {
			for(int j=0; j < map_length;j++) {
				double value = ImprovedNoise.noise(i, j, Math.random()+1);
				//System.out.println(value);
				if(value<limit) {
					board.addComponent(new Grass(), j, i);
				}
				else {
					board.addComponent(new Mountain(), j, i);
				}
					
			}
		}
	}

	private void generateMapRiver() {
		int start_x = getRandomNumber(0,10);
		int start_y = getRandomNumber(0,10);
		
		int end_x = getRandomNumber(0,10);
		int end_y = getRandomNumber(0,10);
		int i =Math.min(start_x, end_x);
		int j = i ==start_x ? start_y:end_y;
		while(true) {
			if(i==Math.max(start_x, end_x) && j == (i ==start_x ? start_y:end_y)) {
				break;
			}
			if(!board.hasComponent(Mountain.class, i, j)) {
				board.addComponent(new Water(), i, j);
				if(board.hasComponent(Grass.class, i, j)) {
					board.removeComponente(Grass.class, i, j);
				}
			}
			
			
			if(i!=Math.max(start_x, end_x)){
				i++;
			}
			if(j != (i ==start_x ? start_y:end_y)) {
				if(j<(i ==start_x ? start_y:end_y)) {
					j++;
				}
				else {
					j--;
				}
			}
		}
	}
	
	private void generateMapForest(double limit) {
		int map_height = board.getMapHeight();
		int map_length = board.getMapLength();
		for(int i=0; i < map_height;i++) {
			for(int j=0; j < map_length;j++) {
				if(board.hasComponent(Mountain.class, j, i) || board.hasComponent(Water.class, j, i)){
					continue;
				}
				double value = ImprovedNoise.noise(i, j, Math.random()+1);
				//System.out.println(value);
				if(value>limit) {
					board.addComponent(new Forest(), j, i);
				}
					
			}
		}
	}
	
	//---- helper functions
	private int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
