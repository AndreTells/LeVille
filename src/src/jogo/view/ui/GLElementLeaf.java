package jogo.view.ui;

import jogo.view.mouse.IActor;

public abstract class GLElementLeaf extends GLElementComponent{

	protected float[] color;
	protected float z_index;
	
	protected GLElementLeaf(String id,GLElementComposite parent, float pos_x, float pos_y, float width, float height,float[] color,float z_index) {
		super(id,parent, pos_x, pos_y, width, height);
		if(parent!=null) {
			parent.addChild(this);
		}
		
		this.setColor(color);
		this.z_index = z_index;
	}
	
	public void setActionObserver(IActor actor,int rank) {
		mouse.addActionObservers(this.id, new GL2DObserver(this,actor,rank));
	}

	public void removeActionObserver() {
		mouse.removeActionObserver(this.id);
	}
	
	public void setColor(float[] color) {
		this.color = color;
	}
	
	public void dispose() {
		this.removeActionObserver();
		if(parent!=null) {
			parent.removeChild(this.getID());
		}
		
	}

}
