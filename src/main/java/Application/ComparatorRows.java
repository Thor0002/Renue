package Application;
import java.util.Comparator;

// Позволяет сравнивать строки CSV файла
public class ComparatorRows implements Comparator{
	private int coloumn;
	
	public ComparatorRows(int coloumn) {this.coloumn = coloumn;}
	
	public int compare(Object o1, Object o2) {
		Row r1 = (Row) o1;
		Row r2 = (Row) o2;
		int x = r1.get()[coloumn - 1].compareTo(r2.get()[coloumn - 1]);
		if (x != 0) {return x;}
		else {return Integer.parseInt(r1.get()[0]) -Integer.parseInt(r2.get()[0]);}
	}
}
