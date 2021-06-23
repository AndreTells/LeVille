package jogo.view.ui.composite;

import jogo.view.mouse.IActor;
import jogo.view.ui.GLElementComposite;
import jogo.view.ui.IPopUpMenu;
import jogo.view.ui.leaf.GLLabel;
import jogo.view.ui.leaf.GLRectangle;

public class GLPopUpMenu extends GLElementComposite implements IPopUpMenu{
	private static float item_height = 0.1f;
	private GLButton[] items;
	private int item_checked;
	
	public GLPopUpMenu(String id, GLElementComposite parent,
			float pos_x, float pos_y, float width,
			String[] button_text
			) {
		super(id, parent, pos_x, pos_y-(item_height*(button_text.length)+(button_text.length+1)*0.01f),
				width, item_height*(button_text.length)+(button_text.length+1)*0.01f);
		

		GLRectangle backdrop = new GLRectangle(id+"_backdrop",this,0,0,width, item_height*(button_text.length)+0.02f,0.02f,new float[] {0.09f, 0.1f, 0.1f,0.95f});
		
		float y = item_height*(button_text.length-1)+0.01f;
		items = new GLButton[button_text.length];
		
		for(int i=0;i<button_text.length;i++) {
			items[i] = new GLButton(id+"_btn"+i,this,
					0.01f,y,
					button_text[i],new float[] {1,1,1,1},
					width-0.02f,item_height,0.02f,
					new float[] {0.26f, 0.52f, 0.96f,0.95f},3);
			y -= item_height;
			y -=0.01f;
		}
	}
	
	public GLPopUpMenu(String id, GLElementComposite parent,
			float pos_x, float pos_y,
			String text,
			String[] button_text) {
		super(id, parent, pos_x, pos_y,
				0, item_height*(button_text.length)+(button_text.length+1)*0.01f);
				
		GLLabel header = new GLLabel(id+"_header",this,
				0.01f,item_height*(button_text.length)-0.02f,
				text,new float[] {1,1,1,1});
		
		float [] header_dims = header.getDims();
		header.setPosition(0.01f, item_height*(button_text.length)-0.02f+header_dims[1]);
		
		float width = header_dims[0]<0.2f?  0.2f:header_dims[0];
		
		GLRectangle backdrop = new GLRectangle(id+"_backdrop",this,0,0,
				width+0.03f,
				item_height*(button_text.length)+0.03f+header_dims[1]*2,0.02f,
				new float[] {0.09f, 0.1f, 0.1f,0.95f});
		
		
		float y = item_height*(button_text.length-1)+0.01f;
		items = new GLButton[button_text.length];
		
		for(int i=0;i<button_text.length;i++) {
			
			items[i] = new GLButton(id+"_btn"+i,this,
					0.01f,y,
					button_text[i],new float[] {1,1,1,1},
					width,item_height,0.02f,
					new float[] {0.26f, 0.52f, 0.96f,0.95f},3);
			
			y -= item_height;
			y -=0.01f;
			
			this.setPosition(pos_x, pos_y-(item_height*(button_text.length)+0.03f+header_dims[1] - 0.01f));
			this.setDims(width,  item_height*(button_text.length)+(button_text.length+1)*0.01f);
		}
		
		
	}
	
	public void setActionObservers(IActor actors[]) {
		
		for(int i=0;i<actors.length;i++) {
			this.items[i].setOnClickObserver(actors[i],3);
		}
	}

	public void checkItem() {
		item_checked+=1;
	}
	
	public boolean allChecked() {
		return item_checked == items.length;
	}


}
