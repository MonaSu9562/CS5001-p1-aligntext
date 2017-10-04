import java.util.ArrayList;
import java.util.regex.Pattern;

public class AlignText {
	public void show(ArrayList<String> arraylist) {
		for(int i = 0; i < arraylist.size(); i++)  
			System.out.println(arraylist.get(i));
	}  
	
	public static boolean isInteger(String str){
//		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//		return pattern.matcher(str).matches();
		for(int i = 0; i < str.length(); i++){
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		AlignText aligntext = new AlignText();
		try {
			//get values
			String[] paragraphs = FileUtil.readFile(args[0]);
			if (aligntext.isInteger(args[1])){
				//change the form of input from string to integer
				int length = new Integer(args[1]);
				
				for(int i = 0; i < paragraphs.length; i++){
					ArrayList<String> result = SplitFile.split(paragraphs[i], length);
					if (args.length == 3) {
						switch(args[2]) {
							case "L": 
								aligntext.show(SplitFile.left(result, length));
								break;
							case "C": 
								aligntext.show(SplitFile.centre(result, length));
								break;
							case "J": 
								aligntext.show(SplitFile.justify(result, length));
								break;
							default:System.out.println("");
						}
					}else aligntext.show(SplitFile.right(result, length));
				}
			}else System.out.println("usage: java AlignText file_name line_length <align_mode>");
			
			
//			//split 
//			ArrayList<String> result = SplitFile.split(paragraphs, length);
//			//how to show				
//			if (args.length == 3) {
//				switch(args[2]) {
//					case "L": 
//						aligntext.show(SplitFile.left(result, length));
//						break;
//					case "C": 
//						aligntext.show(SplitFile.centre(result, length));
//						break;
//					case "J": 
//						aligntext.show(SplitFile.justify(result, length));
//						break;
//					default:System.out.println("");
//				}
//			}else aligntext.show(SplitFile.right(result, length));
			
			
		}catch(Exception e){
			System.out.println("usage: java AlignText file_name line_length <align_mode>");
		}		
	}
}



