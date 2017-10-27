package script;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import entity.Author;
import entity.Conference;
import entity.Paper;
import util.Tool;

public class DataCreater {
	
	public static FileReader fr=null;
	public static BufferedReader br=null;
	public static String line=null;
	
	public static List<Paper> paperList = new LinkedList<>();
	public static List<Author> authorList = new LinkedList<>();
	public static List<Conference> conferenceList = new LinkedList<>();
	/**
	 * input:str
	 * output:newStr
	 * 读取字符串,过滤不含英文字母的字符串，以及过滤空格
	 */
	public static String filter(String str) {
		int i,n;
		n = str.length();
		String newStr = "";
		for(i = 0;i < n;i++){
			if((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')||(str.charAt(i) >= 'a' && str.charAt(i) <= 'z')||(str.charAt(i) == ' ')){
				newStr += str.charAt(i);
			}
		}
		return newStr;
	}
	/**
	 * input:path
	 * 读取文件（文件内容','分割）,过滤不含英文字母的单词，以及过滤空格（文件内容','分割）
	 */
	public static void filterFile(String path) {
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(",");
				int i,n;
				n = arr.length;
				for(i = 0;i < n-1;i++){
					Tool.ps1.print(filter(arr[i]).trim()+",");
				}
				Tool.ps1.println(filter(arr[n-1]).trim());
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * input:path
	 * 删除文件中最后一个多余的分割符（文件内容','分割）
	 */
	public static void deleteLastSymbol(String path) {
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(",");
				int i,n;
				n = arr.length;
				for(i = 0;i < n-1;i++){
					Tool.ps1.print(arr[i]+",");
				}
				Tool.ps1.println(arr[i]);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * input:n
	 * 顺序生成n以内的id
	 */
	public static void makeId(int n) {
		int i;
		for(i = 0;i < n;i++){
			Tool.ps1.println(i);
		}
	}
	/**
	 * input:path
	 * 生成名字列表（生成不含有重复的名字）
	 */
	public static void sparseName(String path) {
		int i,n;
		Set<String> set = new LinkedHashSet<>();
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(",");
				n = arr.length;
				for(i = 0;i < n;i++){
					set.add(arr[i]);
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()){
			Tool.ps1.println(iter.next());
		}
	}
	/**
	 * input:path
	 * output:List<String>
	 * 将文件的每一行以string的格式放入list中
	 */
	public static List<String> readAuthor(String path) {
		List<String> list = new ArrayList<>();
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * input:Kpath,Vpath
	 * 将string与int进行一一映射，将Kpath文件中的string在Vpath中映射出id，即将paper中author_name_list映射出author_id_list
	 */
	public static void mapStringToInt(String Kpath,String Vpath) {
		List<String> list = readAuthor(Vpath);
		try {
			fr = new FileReader(Kpath);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(",");
				int i,n;
				n = arr.length;
				for(i = 0;i < n-1;i++){
					Tool.ps1.print(list.indexOf(arr[i])+",");
				}
				Tool.ps1.print(list.indexOf(arr[n-1]));
				Tool.ps1.println();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * input:Kpath,Vpath
	 * 将int与string进行一一映射，将Kpath文件中的int在Vpath中映射出value，即将paper中author_id_list映射出author_name_list
	 */
	public static void mapIntToString(String Kpath,String Vpath) {
		List<String> list = readAuthor(Vpath);
		try {
			fr = new FileReader(Kpath);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(",");
				int i,n;
				n = arr.length;
				for(i = 0;i < n-1;i++){
					Tool.ps1.print(list.get(Integer.parseInt(arr[i]))+",");
				}
				Tool.ps1.print(list.get(Integer.parseInt(arr[i])));
				Tool.ps1.println();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * input:path,authorPath(path:paper_author_list,authorPath:author_id)
	 * 生成author_paper_id_list
	 */
	public static void makeAuthor_Paper_IdList(String path,String authorPath) {
		int i,j,k,n1,n2,n3;
		List<String> authorList = readAuthor(authorPath);
		List<String> paper_author_list = readAuthor(path);
		n1 = authorList.size();
		n2 = paper_author_list.size();
		for(i = 0;i < n1;i++){
			for(j = 0;j < n2;j++){
				String[] arr = paper_author_list.get(j).split(",");
				n3 = arr.length;
				for(k = 0;k < n3;k++){
					if(authorList.get(i).equals(arr[k])){
						Tool.ps1.print(j+",");
						break;
					}
				}
			}
			Tool.ps1.println();
		}
	}
	/**
	 * input:author_paper_id_path,authorPath,paper_author_path
	 * 生成author在paper中的order序列
	 */
	public static void makeOrderInPaper(String author_paper_id_path,String authorPath,String paper_author_path) {
		List<String> authorList = readAuthor(authorPath);
		List<String> paper_author = readAuthor(paper_author_path);
		List<List<String>> paper_author_list = new ArrayList<>();
		int i,j,n;
		n = paper_author.size();
		for(i = 0;i < n;i++){
			String[] arr = paper_author.get(i).split(",");
			List<String> list = new ArrayList<>();
			for(j = 0;j < arr.length;j++){
				list.add(arr[j]);
			}
			paper_author_list.add(list);
		}
		try {
			i = 0;
			fr = new FileReader(author_paper_id_path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(",");
				n = arr.length;
				for(j = 0;j < n;j++){
					Tool.ps1.print(paper_author_list.get(Integer.parseInt(arr[j])).indexOf(authorList.get(i))+1+",");
				}
				Tool.ps1.println();
				i++;
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * input:conference_paper_path,conference_name_path
	 * 生成conference对应的paper的id列表
	 */
	public static void makeConferenceToPaperId(String conference_paper_path,String conference_name_path) {
		int i,j,n,n2;
		List<String> conferenceList = readAuthor(conference_name_path);
		n = conferenceList.size();
		List[] list = new ArrayList[n];
		for(i = 0;i < n;i++){
			list[i] = new ArrayList<>();
		}
		try {
			i = 0;
			fr = new FileReader(conference_paper_path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				list[Integer.parseInt(line)].add(i);
				i++;
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(i = 0;i < n;i++){
			n2 = list[i].size();
			for(j = 0;j < n2-1;j++){
				Tool.ps1.print(list[i].get(j)+",");
			}
			Tool.ps1.println(list[i].get(j));
		}
	}
	/**
	 * input:conference_paper_id,paper_author_id,conference_n
	 * 生成conference对应的author的id列表
	 */
	public static void makeConferenceToAuthorId(String conference_paper_id,String paper_author_id,int conference_n) {
		int i,j,k,n1,n2;
		Iterator iter;
		List<String> paper_author_list = readAuthor(paper_author_id);
		Set[] set = new LinkedHashSet[conference_n];
		for(i = 0;i < conference_n;i++){
			set[i] = new LinkedHashSet<>();
		}
		try {
			i = 0;
			fr = new FileReader(conference_paper_id);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				String[] arr1 = line.split(",");
				n1 = arr1.length;
				for(j = 0;j < n1;j++){
					String[] arr2 = paper_author_list.get(Integer.parseInt(arr1[j])).split(",");
					n2 = arr2.length;
					for(k = 0;k < n2;k++){
						set[i].add(arr2[k]);
					}
				}
				i++;
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(i = 0;i < conference_n;i++){
			iter = set[i].iterator();
			while(iter.hasNext()){
				Tool.ps1.print(iter.next()+",");
			}
			Tool.ps1.println();
		}
	}

	public static void main(String[] args) {
		String dir,Kpath,Vpath,label,writePath,path;
		dir = "data/file/";
		Kpath = dir + "PaperDataSet.txt";
		Vpath = dir + "conference_name.txt";
		writePath = dir + "id_temp.txt";
		Tool.initWriter1(writePath);
		//makeId(14);
		//filterFile(Kpath);
		//deleteLastSymbol(Kpath);
		//sparseName(Kpath);
		//mapStringToInt(Kpath, path);
		//mapIntToString(Kpath, Vpath);
		//makeAuthor_Paper_IdList(path, Vpath);
		//makeOrderInPaper(Kpath, Vpath, path);
		//makeConferenceToPaperId(Kpath,Vpath);
		//makeConferenceToAuthorId(Kpath, Vpath, 14);
		Tool.closeWriter1();

	}

}
