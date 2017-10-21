package de.ek.ui;

import java.util.HashMap;

public class FieldPositions {
	public static HashMap<Integer, RelativePoint> fieldPosistionTable = new HashMap<Integer, RelativePoint>(){
		{

			put(0, new RelativePoint(0, 0));
			put(1, new RelativePoint(0.5, 0));
			put(2, new RelativePoint(1, 0));
			put(3, new RelativePoint(1d/6d, 1d/6d));
			put(4, new RelativePoint(0.5, 1d/6d));
			put(5, new RelativePoint(5d/6d, 1d/6d));
			put(6, new RelativePoint(2d/6d, 2d/6d));
			put(7, new RelativePoint(0.5, 2d/6d));
			put(8, new RelativePoint(4d/6d, 2d/6d));
			put(9, new RelativePoint(0, 0.5));
			put(10, new RelativePoint(1d/6d, 0.5));
			put(11, new RelativePoint(2d/6d, 0.5));
			put(12, new RelativePoint(4d/6d, 0.5));
			put(13, new RelativePoint(5d/6d, 0.5));
			put(14, new RelativePoint(1, 0.5));
			put(15, new RelativePoint(2d/6d, 4d/6d));
			put(16, new RelativePoint(0.5, 4d/6d));
			put(17, new RelativePoint(4d/6d, 4d/6d));
			put(18, new RelativePoint(1d/6d, 5d/6d));
			put(19, new RelativePoint(0.5, 5d/6d));
			put(20, new RelativePoint(5d/6d, 5d/6d));
			put(21, new RelativePoint(0, 1));
			put(22, new RelativePoint(0.5, 1));
			put(23, new RelativePoint(1, 1));
		}
	};
	

}
