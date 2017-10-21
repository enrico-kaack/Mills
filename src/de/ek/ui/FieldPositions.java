package de.ek.ui;

import java.awt.Point;
import java.util.HashMap;

public class FieldPositions {
	public static HashMap<Integer, RelativePoint> fieldPosistionTable = new HashMap<>();
	
	public FieldPositions() {
		int i = 0;
		FieldPositions.fieldPosistionTable.put(0, new RelativePoint(0, 0));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(0.5, 0));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(1, 0));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(1/6, 1/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(0.5, 1/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(5/6, 1/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(2/6, 2/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(0.5, 2/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(4/6, 2/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(0, 0.5));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(1/6, 0.5));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(2/6, 0.5));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(4/6, 0.5));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(5/6, 0.5));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(1, 0.5));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(2/6, 4/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(0.5, 4/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(4/6, 4/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(1/6, 5/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(0.5, 5/6));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(5/6, 5/6));
		FieldPositions.fieldPosistionTable.put(0, new RelativePoint(0, 1));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(0.5, 1));
		FieldPositions.fieldPosistionTable.put(i++, new RelativePoint(1, 1));
		
		
	}
}
