package jogo.view.ui;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;

public abstract class GLElementComposite extends GLElementComponent{
	protected List<GLElementLeaf> children_leaf;
	protected List<GLElementComposite> children_composite;

	protected GLElementComposite(String id,GLElementComposite parent, float pos_x, float pos_y, float width, float height) {
		super(id,parent, pos_x, pos_y, width, height);
		if(parent!=null) {
			parent.addChild(this);
		}
		
		
		this.children_composite = new LinkedList<GLElementComposite>();
		this.children_leaf = new LinkedList<GLElementLeaf>();
		
	}
	
	//return the child if it is found and null otherwise
	public GLElementComponent getChild(String child_id) {
		for(GLElementLeaf child:children_leaf) {
			if(child.getID().equals(child_id)) {
				return child;
			}
		}
		
		for(GLElementComposite child:children_composite) {
			if(child.getID().equals(child_id)) {
				return child;
			}
			
			GLElementComponent desired_child = child.getChild(child_id);
			if(desired_child != null) {
				return desired_child;
			}
		}
		
		return null;
	}

	//trhow exception if there is a child with the same name
	public void addChild(GLElementComposite child) {
		children_composite.add(child);
	}
	
	public void addChild(GLElementLeaf child) {
		children_leaf.add(child);
	}
	
	//return true if child is found and removed and false otherwise
	public boolean removeChild(String child_id) {
		for(GLElementLeaf child:children_leaf) {
			if(child.getID().equals(child_id)) {
				children_leaf.remove(child);
				return true;
			}
		}
		
		for(GLElementComposite child:children_composite) {
			if(child.getID().equals(child_id)) {
				children_composite.remove(child);
				return true;
			}
			
			if(child.removeChild(child_id)) {
				return true;
			}
		}
		
		return false;
	}

	
	public List<GLElementComponent> getAllChildren() {
		List<GLElementComponent> all_children = new LinkedList<GLElementComponent>();
		if(children_leaf !=null) {
			all_children.addAll(children_leaf);
		}
		
		if(children_composite !=null) {
			all_children.addAll(children_composite);
		}
		return all_children;
	}
	
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		float[] pos = this.getPos();
		gl.glTranslatef(pos[0], pos[1],0);
		
		
		for(GLElementComponent element: this.getAllChildren()) {
			element.draw(gl);
		}
		
		gl.glPopMatrix();
	}

	//deletes this element and all below it
	public void dispose() {
		for(GLElementComponent element: this.getAllChildren()) {
			element.dispose();
		}
		
		GLElementComposite parent = this.getParent();
		if(parent!=null) {
			parent.removeChild(this.getID());
		}
	}

}
