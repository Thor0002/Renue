package Application;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	private static int coloumn;
	
	private static String fileName;
	
	@Value("${airports.coloumn}")
	public void setName(int value) {this.coloumn = value;}
	
	@Value("${airports.file}")
	public void setFileName(String value) {this.fileName = value;}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите строку:");
		String key = sc.next();
		
		ReaderCSV rd = new ReaderCSV(fileName, coloumn);
		long timeElapsed = rd.read(key);
		System.out.println("Время, затраченное на поиск и сортировку: " + (timeElapsed / 1000000) + " мс");
	}
}
