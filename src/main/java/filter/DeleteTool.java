package filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DeleteTool {

	public static void main(String[] args) {
		
		File fichier=new File("E:\\Correction.txt");
		try {
			FileReader rd=new FileReader(fichier);
			BufferedReader br=new BufferedReader(rd);
			StringBuffer sb=new StringBuffer();
			String line;
			try {
				while((line=br.readLine())!=null)
				{
					sb.append(line);
					System.out.println(sb.toString());
				}
				rd.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
