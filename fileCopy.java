// A program to Copy the contents of one file to another

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileCopy {
	public static void main(String[] args) {

		try {
			FileReader fileRead = new FileReader(args[1]);
			Scanner readLine = new Scanner(fileRead);
			FileWriter fileWriter = new FileWriter(args[2]);

			while(readLine.hasNextLine()) {
				String str = readLine.nextLine() + "\n";
				fileWriter.write(str);
			}
			fileWriter.close();
		}

		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
