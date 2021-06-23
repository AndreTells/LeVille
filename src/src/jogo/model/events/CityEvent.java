package jogo.model.events;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jogo.model.boardmodel.IBoardController;
import jogo.model.boardmodel.IBoardEvent;

public class CityEvent extends Event{
	private int modifier[];
	CityEvent(String text,int modifier[]){
		super(text);
		this.modifier = modifier;
	}

	@Override
	public String executeEvent(IBoardEvent board) {
		board.addModifier(modifier);
		return this.toString();
	}
	
	public static List<Event> getEvents(String dataSource) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(dataSource));	
			String line = file.readLine();
			
			List<Event> event_list = new LinkedList<Event>();
			
			while (line != null) {
				String event_s []= line.split(",");
				int modifier[] = new int[] {Integer.parseInt(event_s[1]),Integer.parseInt(event_s[2]),Integer.parseInt(event_s[3])};
				event_list.add(new CityEvent(event_s[0],modifier));
				
				line = file.readLine();
			}
			file.close();
			
			
			return event_list;
		} catch (IOException erro) {
			erro.printStackTrace();
			return null;
		}
	}

	public String getDescription() {
		return "Entire city gets: "+
				(modifier[2]<0 ? modifier[2]:"+"+modifier[2])+" housing |"+
				(modifier[1]<0 ? modifier[1]:"+"+modifier[1])+" production |"+
				(modifier[0]<0 ? modifier[0]:"+"+modifier[0])+" food";
	}
}
