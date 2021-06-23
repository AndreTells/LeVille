package jogo.view.ui;

import jogo.view.mouse.IActor;

public interface IStats {
	public void setPopulation(String population_text);
	
	public void setProduction(String production_text);
	
	public void setFood(String food_text);

	public void setInfo(String info_text);
	
	public IPopUpMenu createSubMenu(String id,float pos_x, float pos_y,float width, String[] items);
	
	public IPopUpMenu createSubMenu(String id,float pos_x, float pos_y,String text, String[] items);

	public void setTurnListener(IActor controller);
	
	public void disposeChild(String id);
}