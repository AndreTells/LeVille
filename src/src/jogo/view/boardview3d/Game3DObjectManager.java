package jogo.view.boardview3d;

import java.util.Dictionary;
import java.util.Hashtable;

import jogo.view.boardview3d.customobject.Obj3D;

public class Game3DObjectManager {
	private static Dictionary<String,Obj3D> models;
	public static void loadModels() {
		models = new Hashtable<String,Obj3D>();
		
		models.put("City", new Obj3D("City_1"));
		models.put("Forest", new Obj3D("Forest_1"));
		models.put("Mountain",  new Obj3D("Mountain_1"));
		models.put("Water",  new Obj3D("Water_1"));
		models.put("Grass",  new Obj3D("Grass_1"));
		models.put("LumberMill", new Obj3D("LumberMill_1"));
		models.put("Farm", new Obj3D("Farm_1"));
		models.put("Castle", new Obj3D("Castle_1"));
		models.put("PreserveForest", new Obj3D("PreserveForest_1"));
		
	}
	
	public static Obj3D getModel(String model) {
		return models.get(model);
	}
}
	