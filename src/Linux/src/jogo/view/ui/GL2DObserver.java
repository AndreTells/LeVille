package jogo.view.ui;

import jogo.view.mouse.IActor;
import jogo.view.mouse.IMouseObserver;

import java.awt.event.MouseEvent;

public class GL2DObserver implements IMouseObserver{
	private GLElementComponent element;
	private IActor actor;
	private int rank;
	
	public GL2DObserver(GLElementComponent element, IActor actor,int rank) {
		this.element = element;
		this.actor = actor;
		this.rank = rank;
	}
	
	@Override
	public boolean conditonIsMet(float mouse_x, float mouse_y) {
		float[] element_pos = element.getAbsolutePos();
		float[] dimensions = element.getDims();
		
		//System.out.println(element.getID()+" x: "+element_pos[0]+" y: "+element_pos[1]+" width: "+dimensions[0]+" height: "+dimensions[1]);
		//System.out.println(mouse_x+" "+mouse_y);
		
		if((mouse_x>element_pos[0] && mouse_x<(element_pos[0]+dimensions[0])) 
				&& (mouse_y>element_pos[1] && mouse_y<(element_pos[1]+dimensions[1]))) {
			return true;
		}
		
		return false;
	}

	@Override
	public void performAction(MouseEvent e,boolean missed) {
		// TODO Auto-generated method stub
		actor.act(e,missed);
	}

	@Override
	public int getRank() {
		return rank;
	}

}
