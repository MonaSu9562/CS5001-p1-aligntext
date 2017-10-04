import java.util.ArrayList;

public class SplitFile {
	
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
		/*for(int i = 0; i < paragraphs.length; i++) {	
			int p = 0;
			while((paragraphs[i].length() - p) > length) {
				if(paragraphs[i].substring(p + length - 1, p + length).equals(" ")) {
					line.add(paragraphs[i].substring(p, p + length - 1));
					p = p + length;
				}else if(paragraphs[i].substring(p + length, p + length + 1).equals(" ")){
					line.add(paragraphs[i].substring(p, p + length));
					p = p + length + 1;
				}else {
					int q = 0;
					while(!paragraphs[i].substring(p+length-q, p+length-q+1).equals(" ") && q < length) q++;
					if (q == length) {
						for(q = 0; !paragraphs[i].substring(p+length-q, p+length-q+1).equals(" "); q++) {
							line.add(paragraphs[i].substring(p, p+length+q+1 ));
							p = p + q + length +2;
						}
					}else if (q < length) {
						line.add(paragraphs[i].substring(p, p+length-q+1));
						p = p + length - q + 1;
					}
				}
			} 
		}*/
		
		
//		for(int i = 0; i < paragraphs.length; i++) {	
//			String str = "";
//			String[] words = paragraphs[i].split(" ");
//			for(int n = 0; n < words.length; n++){
//				if (str == ""){                                                                                                       
//					str = words[n];
//				}else if ((str + " " + words[n]).length() <= length){
//					str = str + " " + words[n];	
//				}else if (words[n].length() >= length){
//					line.add(str);
//					str = words[n];
//				}else{
//					line.add(str);
//					str = words[n];
//				}
//			}
//			line.add(str);
//		}
//		return line;
		
		
	}
	
	protected final static String genespace(int n){//static?????
		String to = "";
		for(int i = 0; i < n; i++){
			to = to + " ";
		}
		return to;
	}
	
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
	
	public static ArrayList<String> left(ArrayList<String> line, int length){
		String str = "";
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < line.size(); i++){
			str = line.get(i);
			if (length - str.length() > 0){
				str = str + genespace(length-str.length());
				result.add(str);
			}else result.add(str);
		}
		return result;
	}

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

}
