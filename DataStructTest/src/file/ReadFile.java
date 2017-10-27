package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ReadFile {
	public static void read_write_File(String readPath){
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
		int i=0;
		try {
			fr=new FileReader(readPath);
			br=new BufferedReader(fr);
			while ((line=br.readLine())!=null) {
				if(i>100)
					break;
				System.out.println(line);
				releaseJSON(line);
				i++;
			}
			br.readLine();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void releaseJSON(String line){
		JSONObject jsonObj = JSONObject.fromString(line);
		String id=jsonObj.getString("id");
		String ids=jsonObj.getString("ids");
		String total_number=jsonObj.getString("total_number");
		String next_cursor=jsonObj.getString("next_cursor");
		String previous_cursor=jsonObj.getString("previous_cursor");
		System.out.println("JSON解析:");
		System.out.println("id:"+id);
		System.out.println("ids:"+ids);
		System.out.println("next_cursor:"+next_cursor);
		System.out.println("previous_cursor:"+previous_cursor);
		System.out.println("total_number:"+total_number);
	}
	public static void showString(String str[]){
		System.out.println("strArray:");
		for(String s:str)
			System.out.println(s);
	}
	public static void main(String[] args) {
		String readPath="relsemple.json";
		read_write_File(readPath);
	}

}
