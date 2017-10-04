import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlignText {
	private char just;
	public static char LEFT = 'L';
	public static char RIGHT = 'R';
	public static char CENTER = 'C';
	public static char JUSTIFY = 'J';
	
	public void show(ArrayList arraylist) {
		for(int i = 0; i < arraylist.size(); i++)  
			System.out.println(arraylist.get(i));
	}  
	
	public static void main(String[] args) {
		AlignText aligntext = new AlignText();
		try {
			if ((args.length < 2) )
				throw new IllegalInputException("java AlignText file_name line_length")
		}
		if (args.length < 2) 
			System.out.println("java AlignText file_name line_length");
		else {
			String[] paragraphs = FileUtil.readFile(args[0]);			
			int length = new Integer(args[1]);
			for(int i = 0; i < paragraphs.length; i++) 
				System.out.println(paragraphs[i]);
			aligntext.show(SplitFile.split(paragraphs, length));
			
//			if (args.length == 3) {
//				switch(args[2]) {
//				case "L": ;
//				case "C": ;
//				case "J": ;
//				default:System.out.println("");
//				}
//			}
		}
	}
}



