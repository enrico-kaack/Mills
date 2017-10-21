package de.ek.data;

import java.util.ArrayList;

public class Row {
	public ArrayList<Field> fields = new ArrayList<>(3);
	public boolean isMuehle = false;
	@Override
	public String toString() {
		return "Row [fields=" + fields + ", isMuehle=" + isMuehle + "]";
	}
	
	

}
