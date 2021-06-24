package jogo.view.ui;

import jogo.view.mouse.IActor;
import jogo.view.ui.composite.GLButton;
import jogo.view.ui.composite.GLPopUpMenu;
import jogo.view.ui.leaf.GLLabel;
import jogo.view.ui.leaf.GLRectangle;

public class UI extends GLElementComposite implements IStats{
	
	private GLRectangle backdrop;
	private GLLabel population;
	private GLLabel production;
	private GLLabel food;
	private GLLabel info;
	private GLButton next_turn;
	
	private final static float x_size = 0.4f;
	private final static float y_size = 1.96f;
	private final static float transparency =0.95f;
	private final static float margin = 0.01f;
	
	public UI(GLElementComposite parent) {
		super("ui",parent,1-x_size-margin, 0,x_size,y_size);
			
		this.population = new GLLabel(
				id+"_population_text", this,
				margin, 0.9f,
				"population: ", new float[] {1,1,1,1},0.5f);
		
		this.production = new GLLabel(
				id+"_production_text", this,
				margin, 0.7f,
				"production: ", new float[] {1,1,1,1},0.5f);
		
		this.food = new GLLabel(
				id+"_food_text", this,
				margin, 0.5f,
				"food: ", new float[] {1,1,1,1},0.5f);
		
		this.info = new GLLabel(
				id+"_info_text", this,
				margin, 0.3f,
				"cell info: ", new float[] {1,1,1,1},0.5f);
		
		this.backdrop = new GLRectangle(
				id+"_backdrop",this,
				0,-y_size/2,
				x_size,y_size,
				0.02f,new float[] {0.09f, 0.1f, 0.1f,transparency},
				1
				);
		
		this.next_turn = new GLButton(
				id+"_btn",this,
				margin,(-y_size/2)+0.02f,
				"Next Turn", new float[] {1,1,1,1},
				x_size-margin*2, 0.2f,
				0.02f,new float[] {0.26f, 0.52f, 0.96f,transparency},1
				);
		
	}
	
	public void setTurnListener(IActor controller) {
		next_turn.setOnClickObserver(controller,2);
	}
	
	public float[] getDims() {
		return backdrop.getDims();
	}
	
	public IPopUpMenu createSubMenu(String id,float pos_x, float pos_y,float width, String[] items) {
		return new GLPopUpMenu(id,this,pos_x-(1-x_size-margin),pos_y,width,items);
	}
	
	public IPopUpMenu createSubMenu(String id,float pos_x, float pos_y,String text, String[] items) {
		return new GLPopUpMenu(id,this,pos_x-(1-x_size-margin),pos_y,text,items);
	}
	
	public void disposeChild(String id) {
		GLElementComponent child = this.getChild(id);
		if(child!= null) {
			child.dispose();
		}
	}
	
	//----------- setter methods 
	
	public void setPopulation(String population_text) {
		population.setText(population_text);
	}
	
	public void setProduction(String production_text) {
		production.setText(production_text);
	}
	
	public void setFood(String food_text) {
		food.setText(food_text);
	}
	
	public void setInfo(String info_text) {
		this.info.setText(info_text);
		
		float height = this.info.getDims()[1];
		int num_lines = info_text.split("\n").length;
		
		this.info.setPosition(0, 0.3f-height*((float)num_lines-1)/num_lines);
		
	}

	
}