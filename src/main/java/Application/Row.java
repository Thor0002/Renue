package Application;

// Обертка для строки CSV файла
public class Row {
	private String[] row;
	
	public Row(String[] row) {this.row = row;}
	
	public String[] get() {return row;}

}
