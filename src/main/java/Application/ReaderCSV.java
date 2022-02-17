package Application;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

//@Component
public class ReaderCSV {
	//	@Value("${airports.coloumn}") -- В таком случае переменная не инициализируется
	private int coloumn;

	private String fileName;

	public ReaderCSV(String fileName, int coloumn) {this.fileName = fileName; this.coloumn = coloumn;}
	
    // Считывает CSV файл и выделяет нужные в отсортировном порядке.
	public long read(String key) {
		try {
			CSVReader reader= new CSVReader(new FileReader(fileName, StandardCharsets.UTF_8)); //, ',' , '"' , 1);
			
			String[] nextline;

			long startTime = System.nanoTime();
			Comparator comp = new ComparatorRows(coloumn);
			Set<Row> set = new TreeSet<Row>(comp);
			while ((nextline = reader.readNext()) != null) {
				if (nextline != null) {
					if(begin(key, nextline[coloumn - 1])) {
						Row r = new Row(nextline);
						set.add(r);
					}
				}
			}
			long endTime = System.nanoTime();
			
			Iterator<Row> it = set.iterator();
			while (it.hasNext()) {
				String[] row = it.next().get();
				System.out.print(row[coloumn - 1] + ", ");
				for(int i = 1; i < row.length; i++) {
					if(i != coloumn - 1) {
						System.out.print(row[i] + ", ");
				    }
				}
				System.out.println("");
			}
			System.out.print("Количество найденных строк: " + set.size() + ". ");
			long timeElapsed = endTime - startTime;
			return timeElapsed;
		} catch (CsvValidationException e) {
			System.out.println("Не удалось считать строку с файла");
		} catch (IOException e) {
			System.out.println("Не удалось считать строку с файла");
		}
		return 0;
	}

	private boolean begin(String beg, String str) {
		if(beg.length() <= str.length()) {
			boolean t = true;
			for(int i = 0; i < beg.length(); i++) {
				t = t && (beg.charAt(i) == str.charAt(i));
			}
			return t;
		} else {return false;}
	}
}
