import java.util.ArrayList;

public class AlignText {
	
	/* PURPOSE:	
	 * INPUT:	args[0]: the path of the file containing the text
	 * 			args[1]: the desired length of the line for wrapping the text
	 * 			args[2]:(optional)	args[2] = "L": Life-align; 
	 * 							  	args[2] = "C": Centre-align; 
	 * 							   	args[2] = "J": Justify 	
	 * OUTPUT:*/
	public static void main(String[] args) {
		try {
			//get values
			String[] paragraphs = FileUtil.readFile(args[0]);
			//If the content of args[1] is not a integer, stop the program and output message
			if (isInteger(args[1])){
				//change the form of input from string to integer
				int length = new Integer(args[1]);
				//operate each paragraph
				for(int i = 0; i < paragraphs.length; i++){
					ArrayList<String> result = split(paragraphs[i], length);
					if (args.length == 3) {
						switch(args[2]) {
							case "L": 
								show(result);
								break;
							case "C": 
								show(centre(result, length));
								break;
							case "J": 
								show(justify(result, length));
								break;
							default:System.out.println("");
						}
					}else show(right(result, length));
				}
			}else System.out.println("usage: java AlignText file_name line_length <align_mode>");				
		}catch(Exception e){
			System.out.println("usage: java AlignText file_name line_length <align_mode>");
		}		
	}
	
	
	/* PURPOSE:	split the input paragraph(String) into lines which meet the length limit 
	 * INPUT:	paragraph:	a whole text of one paragraph in the type of String
	 * 			length:		the desired length of the line for wrapping the text
	 * OUTPUT:	line:		a ArrayList which includes the separate lines 
	 * METHODS:	split the text by space and put them into a string array. Add word and space into 
	 * 			a string one by one and judge whether or not keep this word in the current line*/
	public static ArrayList<String> split(String paragraph, int length) {
		ArrayList<String> line = new ArrayList<>();
		String str = "";
		String[] words = paragraph.split(" ");
		for(int n = 0; n < words.length; n++){
			if (str == ""){                                                                                                       
				str = words[n];
			}else if ((str + " " + words[n]).length() <= length){
				str = str + " " + words[n];	
			}else if (words[n].length() >= length){
				line.add(str);
				str = words[n];
			}else{
				line.add(str);
				str = words[n];
			}
		}
		line.add(str);
		return line;	
	}
	
	
	/* PURPOSE:	Right-align the input ArrayList of the separated paragraph 
	 * INPUT:	line:	the separated lines of the paragraph wanted to be reformed
	 * OUTPUT:	result:	the right-align form of the input separated paragraph 
	 * METHODS:	use the limitation of length minus the length of current line and 
	 * 			add spaces at the beginning of each line */
	public static ArrayList<String> right(ArrayList<String> line, int length){
		String str = "";
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < line.size(); i++){
			str = line.get(i);
			if (length - str.length() > 0){
				str = genespace(length-str.length()) + str;
				result.add(str);
			}else result.add(str);
			
		}
		return result;
	}
	
	
	/* PURPOSE:	Centre-align the input ArrayList of the separated paragraph 
	 * INPUT:	line:	the separated lines of the paragraph wanted to be reformed
	 * OUTPUT:	result:	the centre-align form of the input separated paragraph 
	 * METHODS:	use the limitation of length minus the length of current line and 
	 * 			add spaces at the beginning and the  end of each line */
	public static ArrayList<String> centre(ArrayList<String> line, int length){
		String str = "";
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < line.size(); i++){
			str = line.get(i);
			if (length - str.length() > 0){
				if ((length - str.length()) % 2 == 0){
					str = genespace((length - str.length())/2) + str + genespace((length - str.length())/2);
					result.add(str);
				}else{
					str = genespace((length - str.length())/2 + 1) + str + genespace((length - str.length())/2);
					result.add(str);
				}
			}else result.add(str);
		}
		return result;
	}
	
	
	/* PURPOSE:	Justify the input ArrayList of the separated paragraph and distribute 
	 * 			spaces as evenly as possible between words on the line. 
	 * INPUT:	line:	the separated lines of the paragraph wanted to be reformed
	 * OUTPUT:	result:	the justified form of the input separated paragraph 
	 * METHODS:	use the limitation of length minus the number of characters of current 
	 * 			line and add spaces at each gap between two words*/
	public static ArrayList<String> justify(ArrayList<String> line, int length){
		ArrayList<String> result = new ArrayList<String>();
		String[] words;
		int nGap = 0;
		int i = 0;
		for(i = 0; i < line.size() - 1; i++){
			String str = "";
			words = line.get(i).split(" ");
			//get the number of spaces need to be added
			int nChar = 0;
			for(int j = 0; j < words.length; j++) nChar += words[j].length();
			int nSpace = length - nChar;
			//number of gap
			nGap = (words.length) - 1;
			if (nGap == 0){
				str = words[0];
				result.add(str);
			}else{
				int neGap = nSpace % nGap; 
				int n;
				for(n = 0; n < nGap; n++){
					if (n < nGap- neGap){
						str = str + words[n] + genespace(nSpace / nGap);
					}else str = str + words[n] + genespace(nSpace / nGap + 1);
				}
				str = str + words[n];
				result.add(str);
			}
		}
		result.add(line.get(i));
		return result;
	}
	
	
	/* PURPOSE:	Output the ArrayList
	 * INPUT: 	ArrayList
	 * OUTPUT:	void */
	public static void show(ArrayList<String> arraylist) {
		for(int i = 0; i < arraylist.size(); i++)  
			System.out.println(arraylist.get(i));
	}  
	
	
	/* PURPOSE:	Judge if the content of this string is integer.
	 * 			If yes, return true.
	 *  		If not, return false.
	 * INPUT:	String
	 * OUTPUT:	true or false */
	public static boolean isInteger(String str){
		for(int i = 0; i < str.length(); i++){
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57) return false;
		}
		return true;
	}

	
	
	/* PURPOSE:	Generate a string with specific number of spaces. 
	 * INPUT:   n: the length of the generated string 
	 * OUTPUT:	to: a string with argued number of spaces */
	public static String genespace(int n){//static?????
		String to = "";
		for(int i = 0; i < n; i++){
			to = to + " ";
		}
		return to;
	}
}



