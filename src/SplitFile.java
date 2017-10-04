import java.util.ArrayList;

public class SplitFile {
	
	public static ArrayList<String> split(String[] paragraphs, int length) {
		ArrayList<String> line = new ArrayList<>();
		for(int i = 0; i < paragraphs.length; i++) {	
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
		}
		return line;
	}

}
