package jogo.model.boardmodel;

import java.util.LinkedList;
import java.util.List;

import jogo.model.boardmodel.components.Castle;
import jogo.model.boardmodel.components.City;
import jogo.model.boardmodel.components.Component;
import jogo.model.boardmodel.components.Farm;
import jogo.model.boardmodel.components.LumberMill;
import jogo.model.boardmodel.components.PreserveForest;
import jogo.model.boardmodel.components.Water;

public class CellModel {
	private BoardModel board;
	private List<Component> components;
	private int x;
	private int y;
	private int modifier[];
	private boolean claimed;
	/*
	 * modifier index
	 0-> food
	 1->production
	 2->population_limit
	*/
	
	private CellModel up;
	private CellModel down;
	private CellModel left;
	private CellModel right;
	
	public CellModel(BoardModel board,int x,int y,CellModel left, CellModel up){
		this.board = board;
		this.x = x;
		this.y = y;
		this.components = new LinkedList<Component>();
		this.claimed = false;
		
		this.modifier = new int[3];
		for(int i=0;i<modifier.length;i++) {
			modifier[i] = 0;
		}
		
		this.up = null;
		this.down = null;
		this.right = null;
		this.left = null;
		
		this.setRelative(left, null, up, null);
		
		if(left!=null) {
			left.setRelative(null, this, null, null);
		}
		if(up!=null) {
			up.setRelative(null, null, null, this);
		}
	}
	
	private void setRelative(CellModel left,CellModel right,CellModel up,CellModel down) {
		this.left = left==null ?this.left:left;
		this.right = right==null ?this.right:right;
		this.up = up==null ?this.up:up;
		this.down = down==null ?this.down:down;
	}

	public String getInfo() {
		String info = new String();
		info+="Position: ("+x+","+y+")";
		info+="\nComponentes:";
		for(Component comp:components) 	{
			info+="\n-"+comp.getClass().getSimpleName();
		}
		info+="\nHousing:"+modifier[2];
		info+="\nProduction:"+modifier[1];
		info+="\nFood:"+modifier[0];
		info+="\nClaimed:"+this.claimed;
		return info;
	}
	
	public void addComponent(Component comp) {
		if(!this.hasComponent(comp.getClass())) {
			components.add(comp);
			if(this.claimed) {
				board.removeModifiers(modifier);
				comp.setCell(this);
				board.addModifier(modifier);
			}
			else {
				this.addModifiers(comp.getModifier());
				comp.setCell(this);
			}
		}		
	}
	
	public void removeComponent(Class cls) {
		for(int i =0; i < components.size();i++) {
			Component comp = components.get(i);
			if(cls.isInstance(comp)) {
				this.removeModifiers(comp.getModifier());
				if(this.claimed) {
					board.removeModifiers(comp.getModifier());
				}
				components.remove(i);
				break;
			}
		}
	}
	
	
	public void addModifiers(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] += external_modifier[i];
		}
	}
	
	public void removeModifiers(int external_modifier[]) {
		for(int i=0;i<modifier.length;i++) {
			modifier[i] -= external_modifier[i];
		}
	}
	
	public String getHighestComponent() {
		if(components.size() == 0) {
			return null;
		}
		
		Component top= components.get(0);
		for(Component comp: components) {
			if(comp.greaterThan(top)) {
				top = comp;
			}
		}
		return top.getClass().getSimpleName();
	}
	
	public boolean hasComponent(Class cls) {
		for(Component comp: components) {
			if(comp.getClass().equals(cls)) {
				return true;
			}
		}
		return false;
	}

	public List<String> getPossibleActions(){
		List<String> result = new LinkedList<String>();
		
		if(City.isConstructalbe(this) && !this.hasComponent(City.class)) {
			result.add(City.class.getSimpleName());
		}
		if(LumberMill.isConstructalbe(this) && !this.hasComponent(LumberMill.class)) {
			result.add(LumberMill.class.getSimpleName());
		}
		if(Farm.isConstructalbe(this) && !this.hasComponent(Farm.class)) {
			result.add(Farm.class.getSimpleName());
		}
		if(Castle.isConstructalbe(this) && !this.hasComponent(Castle.class)) {
			result.add(Castle.class.getSimpleName());
		}
		if(PreserveForest.isConstructalbe(this) && !this.hasComponent(PreserveForest.class)) {
			result.add(PreserveForest.class.getSimpleName());
		}
		
		if(result.isEmpty()) {
			result.add("None");
		}
		
		return result;
	}

	public boolean isClaimed() {
		return claimed;
	}

	public void claim() {
		this.claimed = true;
		board.addModifier(modifier);
	}
	
	public boolean adjacentHas(Class cls) {
		if(up!=null && up.hasComponent(cls)) {
			return true;
		}
		if(down!=null && down.hasComponent(cls)) {
			return true;
		}
		if(left!=null && left.hasComponent(cls)) {
			return true;
		}
		if(right!=null && right.hasComponent(cls)) {
			return true;
		}
		return false;
	}
	
	public CellModel getUp() {return this.up;}
	public CellModel getDown() {return this.down;}
	public CellModel getLeft() {return this.left;}
	public CellModel getRight() {return this.right;}
	public BoardModel getBoard() {return this.board;}
	

}