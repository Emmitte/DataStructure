package script;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import entity.Author;
import entity.Conference;
import entity.Paper;
import util.Tool;

public class CreateTable {
	
	static int c = 0;
	
	public static FileReader fr=null;
	public static BufferedReader br=null;
	public static String line=null;
	
	public static List<Paper> paperList = new LinkedList<>();
	public static List<Author> authorList = new LinkedList<>();
	public static List<Conference> conferenceList = new LinkedList<>();
	/**
	 * input:path,label
	 * 创建table
	 */
	public static void createTable(String path,String label) {
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				switch (label) {
				case "paper":
					addPaper(line);
					break;
				case "author":
					addAuthor(line);
					break;
				case "conference":
					addConference(line);
					break;
				default:
					break;
				}
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
	 * input:str,list
	 * 将str以','分割后加入list中(string类型)
	 */
	public static void addList_String(String str,List<String> list) {
		int i,n;
		String[] arr = str.split(",");
		n = arr.length;
		for(i = 0;i < n;i++){
			list.add(arr[i]);
		}
	}
	/**
	 * input:str,list
	 * 将str以','分割后加入list中(int类型)
	 */
	public static void addList_Integer(String str,List<Integer> list) {
		int i,n;
		String[] arr = str.split(",");
		n = arr.length;
		for(i = 0;i < n;i++){
			list.add(Integer.parseInt(arr[i]));
		}
	}
	/**
	 * input:line
	 * 将paper字符串加入paperList中(以'\t'分割)
	 */
	public static void addPaper(String line) {
		String[] arr = line.split("\t");
		int id = Integer.parseInt(arr[0]);
		String title = arr[1];
		int conference_id = Integer.parseInt(arr[2]);
		String conference_name = arr[3];
		int conference_label = Integer.parseInt(arr[4]);
		int year = Integer.parseInt(arr[5]);
		List<String> author_name_list= new ArrayList<>();
		addList_String(arr[6], author_name_list);
		List<Integer> author_id_list = new ArrayList<>();
		addList_Integer(arr[7], author_id_list);
		Paper paper = new Paper(id, title, conference_id, conference_name, conference_label, year, author_name_list, author_id_list);
		paperList.add(paper);
	}
	/**
	 * input:line
	 * 将author字符串加入authorList中(以'\t'分割)
	 */
	public static void addAuthor(String line) {
		String[] arr = line.split("\t");
		int id = Integer.parseInt(arr[0]);
		String name = arr[1];
		List<Integer> paper_id_list = new ArrayList<>();
		addList_Integer(arr[2], paper_id_list);
		List<String> title_list = new ArrayList<>();
		addList_String(arr[3], title_list);
		List<Integer> orderInPaper_list = new ArrayList<>();
		addList_Integer(arr[4], orderInPaper_list);
		List<String> conference_name_list = new ArrayList<>();
		addList_String(arr[5], conference_name_list);
		List<Integer> conference_id_list = new ArrayList<>();
		addList_Integer(arr[6], conference_id_list);
		List<Integer> conference_label_list = new ArrayList<>();
		addList_Integer(arr[7], conference_label_list);
		Author author = new Author(id, name, paper_id_list, title_list, orderInPaper_list, conference_name_list, conference_id_list, conference_label_list);
		authorList.add(author);
	}
	/**
	 * input:str
	 * 将conference字符串加入conferenceList中(以'\t'分割)
	 */
	public static void addConference(String str) {
		String[] arr = line.split("\t");
		int id = Integer.parseInt(arr[0]);
		String name = arr[1];
		int label = Integer.parseInt(arr[2]);
		List<Integer> paper_id_list = new ArrayList<>();
		addList_Integer(arr[3], paper_id_list);
		List<Integer> author_id_list = new ArrayList<>();
		addList_Integer(arr[4], author_id_list);
		Conference conference = new Conference(id, name, label, paper_id_list, author_id_list);
		conferenceList.add(conference);
	}
	/**
	 * input:paperPath,authorPath,confencePath
	 * 初始化table
	 */
	public static void init(String paperPath,String authorPath,String confencePath) {
		String label;
		label = "paper";
		createTable(paperPath, label);
		label = "author";
		createTable(authorPath, label);
		label = "conference";
		createTable(confencePath, label);
	}
	/**
	 * input:paper_id
	 * 通过paper_id找到对应的level(带回溯)
	 */
	public static void getLevelFromPaperId(int paper_id) {
		List<Integer> paper_visited = new ArrayList<>();
		List<Integer> author_visited = new ArrayList<>();
		List<Integer> conference_visited = new ArrayList<>();
		paperToAuthor(author_visited, paper_visited,conference_visited, paper_id);
		paperToConference(conference_visited, paper_visited,author_visited, paper_id);
	}
	/**
	 * input:author_visited,paper_visited,conference_visited,paper_id
	 * 通过paper_id找到对应的author
	 */
	public static void paperToAuthor(List<Integer> author_visited,List<Integer> paper_visited,List<Integer> conference_visited,int paper_id) {
		List<Integer> author_list;
		int i,n;
		author_list = paperList.get(paper_id).author_id_list;
		n = author_list.size();
		for(i = 0;i < n;i++){
			if(!author_visited.contains(author_list.get(i))){
				System.out.println("paperToAuthor "+authorList.get(author_list.get(i)));
				author_visited.add(author_list.get(i));
				authorToPaper(author_visited, paper_visited,conference_visited, author_list.get(i));
				paperToConference(conference_visited, paper_visited, author_visited, paper_id);
			}
		}
	}
	/**
	 * input:author_visited,paper_visited,conference_visited,author_id
	 * 通过author_id找到对应的paper
	 */
	public static void authorToPaper(List<Integer> author_visited,List<Integer> paper_visited,List<Integer> conference_visited,int author_id) {
		List<Integer> paper_list;
		int i,n;
		paper_list = authorList.get(author_id).paper_id_list;
		n = paper_list.size();
		for(i = 0;i < n;i++){
			if(!paper_visited.contains(paper_list.get(i))){
				System.out.println("authorToPaper "+paperList.get(paper_list.get(i)));
				paper_visited.add(paper_list.get(i));
				paperToAuthor(author_visited, paper_visited,conference_visited, paper_list.get(i));
				authorToConference(conference_visited, author_visited, paper_visited, author_id);
			}
		}
	}
	/**
	 * input:conference_visited,paper_visited,author_visited,paper_id
	 * 通过paper_id找到对应的conference
	 */
	public static void paperToConference(List<Integer> conference_visited,List<Integer> paper_visited,List<Integer> author_visited,int paper_id) {
		if(!conference_visited.contains(paperList.get(paper_id).conference_id)){
			System.out.println("paperToConference "+conferenceList.get(paperList.get(paper_id).conference_id));
			conference_visited.add(paperList.get(paper_id).conference_id);
			conferenceToPaper(conference_visited, paper_visited,author_visited, paperList.get(paper_id).conference_id);
			paperToAuthor(author_visited, paper_visited, conference_visited, paper_id);
		}
	}
	/**
	 * input:conference_visited,paper_visited,author_visited,conference_id
	 * 通过conference_id找到对应的paper
	 */
	public static void conferenceToPaper(List<Integer> conference_visited,List<Integer> paper_visited,List<Integer> author_visited,int conference_id) {
		List<Integer> paper_list = conferenceList.get(conference_id).paper_id_list;
		int i,n;
		n = paper_list.size();
		for(i = 0;i < n;i++){
			if(!paper_visited.contains(paper_list.get(i))){
				System.out.println("conferenceToPaper "+paperList.get(paper_list.get(i)));
				paper_visited.add(paper_list.get(i));
				paperToConference(conference_visited, paper_visited,author_visited, paper_list.get(i));
				paperToAuthor(author_visited, paper_visited,author_visited, paper_list.get(i));
			}
		}
	}
	/**
	 * input:conference_visited,author_visited,paper_visited,author_id
	 * 通过author_id找到对应的conference
	 */
	public static void authorToConference(List<Integer> conference_visited,List<Integer> author_visited,List<Integer> paper_visited,int author_id) {
		List<Integer> conference_list = authorList.get(author_id).conference_id_list;
		int i,n;
		n = conference_list.size();
		for(i = 0;i < n;i++){
			if(!conference_visited.contains(conference_list.get(i))){
				System.out.println("authorToConference "+conferenceList.get(conference_list.get(i)));
				conference_visited.add(conference_list.get(i));
				conferenceToPaper(conference_visited, paper_visited, author_visited, conference_list.get(i));
				authorToPaper(author_visited, paper_visited, conference_visited, author_id);
			}
		}
	}
	/**
	 * input:conference_visited,author_visited,paper_visited,conference_id
	 * 通过conference_id找到对应的author
	 */
	public static void ConferenceToAuthor(List<Integer> conference_visited,List<Integer> author_visited,List<Integer> paper_visited,int conference_id) {
		List<Integer> author_list = conferenceList.get(conference_id).author_id_list;
		int i,n;
		n = author_list.size();
		for(i = 0;i < n;i++){
			if(!author_visited.contains(author_list.get(i))){
				System.out.println("ConferenceToAuthor "+authorList.get(author_list.get(i)));
				author_visited.add(author_list.get(i));
				conferenceToPaper(conference_visited, paper_visited, author_visited, conference_id);
				
			}
		}
	}
	/**
	 * input:path
	 * 创建paper层次矩阵(每行对应一层)
	 */
	public static List<List<Integer>> createPapaerList(String path) {
		List<List<Integer>> listArray = new ArrayList<>();
		int i,n;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				StringTokenizer sz = new StringTokenizer(line);
				List<Integer> list = new ArrayList<>();
				while(sz.hasMoreTokens()){
					list.add(Integer.parseInt(sz.nextToken()));
				}
				listArray.add(list);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listArray;
	}
	/**
	 * input:List<List<Integer>> list
	 * 通过list获取每层的信息
	 */
	public static void getLevelFromPaper(String dir,List<List<Integer>> list) {
		int i,n;
		n = list.size();
		String paper,author,conference;
		paper = dir + "paper" + ".txt";
		author = dir + "author" + ".txt";
		conference = dir + "conference" + ".txt";
		Tool.initWriter3(paper, author, conference);
		for(i = 0;i < n;i++){
			System.out.println("level " + (i+1) + ":");
			
			getLevelFromPaperIdSimplely(list.get(i));
			Tool.ps1.println();
			Tool.ps2.println();
			Tool.ps3.println();
		}
		Tool.closeWriter3();
	}
	/**
	 * input:List<Integer> paper_id_list
	 * 通过paper_id_list获取当前层的相邻节点的信息
	 */
	public static void getLevelFromPaperIdSimplely(List<Integer> paper_id_list) {
		int index_paper,index_author,n_paper,n_author;
		n_paper = paper_id_list.size();
		for(index_paper = 0;index_paper < n_paper;index_paper++){
			Tool.ps1.print(paperList.get(paper_id_list.get(index_paper)).id + " ");
			//System.out.println(paperList.get(paper_id_list.get(index_paper)));
			List<Integer> author_list = paperList.get(paper_id_list.get(index_paper)).author_id_list;
			n_author = author_list.size();
			for(index_author = 0;index_author < n_author;index_author++){
				Tool.ps2.print(authorList.get(author_list.get(index_author)).id + " ");
				//System.out.println(authorList.get(author_list.get(index_author)));
			}
			Tool.ps3.print(conferenceList.get(paperList.get(paper_id_list.get(index_paper)).conference_id).id + " ");
			//System.out.println(conferenceList.get(paperList.get(paper_id_list.get(index_paper)).conference_id));
		}
	}
	/**
	 * input:List<Integer> conference_id_list
	 * 通过conference_id_list获取当前层的相邻节点的信息
	 */
	public static void getLevelFromConferenceIdSimplely(List<Integer> conference_id_list) {
		int index_paper,index_author,index_conference,n_paper,n_author,n_conference;
		n_conference = conference_id_list.size();
		for(index_conference = 0;index_conference < n_conference;index_conference++){
			System.out.println(conferenceList.get(conference_id_list.get(index_conference)));
			List<Integer> paper_list = conferenceList.get(conference_id_list.get(index_conference)).paper_id_list;
			n_paper = paper_list.size();
			for(index_paper = 0;index_paper < n_paper;index_paper++){
				System.out.println(paperList.get(paper_list.get(index_paper)));
			}
			List<Integer> author_list = conferenceList.get(conference_id_list.get(index_conference)).author_id_list;
			n_author = author_list.size();
			for(index_author = 0;index_author < n_author;index_author++){
				System.out.println(authorList.get(author_list.get(index_author)));
			}
		}
	}
	/**
	 * input:List<Integer> conference_label_list
	 * 通过conference_label_list获取当前层的相邻节点的信息
	 */
	public static void getLevelFromConferenceLabelSimplely(List<Integer> conference_label_list) {
		int index_paper,index_author,index_conference,n_paper,n_author,n_conference;
		n_conference = conference_label_list.size();
		List<Integer> conference_id_list = new ArrayList<>();
		List<Integer> conference_label = new ArrayList<>();
		n_conference = conferenceList.size();
		for(index_conference = 0;index_conference < n_conference;index_conference++){
			conference_label.add(conferenceList.get(index_conference).label);
		}
		n_conference = conference_label.size();
		if(n_conference == 0){
			return;
		}
		for(index_conference = 0;index_conference < n_conference;index_conference++){
			conference_id_list.add(conference_label.indexOf(conference_label_list.get(index_conference)));
		}
		n_conference = conference_id_list.size();
		for(index_conference = 0;index_conference < n_conference;index_conference++){
			System.out.println(conferenceList.get(conference_id_list.get(index_conference)));
			List<Integer> paper_list = conferenceList.get(conference_id_list.get(index_conference)).paper_id_list;
			n_paper = paper_list.size();
			for(index_paper = 0;index_paper < n_paper;index_paper++){
				System.out.println(paperList.get(paper_list.get(index_paper)));
			}
			List<Integer> author_list = conferenceList.get(conference_id_list.get(index_conference)).author_id_list;
			n_author = author_list.size();
			for(index_author = 0;index_author < n_author;index_author++){
				System.out.println(authorList.get(author_list.get(index_author)));
			}
		}
	}
	
	public static void main(String[] args) {
		
		String dir,paperPath,authorPath,confencePath,list;
		dir = "data/work/rank/";
		paperPath = dir + "PaperDataSet.txt";
		authorPath = dir + "AuthorDataSet.txt";
		confencePath = dir + "ConferenceDataSet.txt";
		dir = "data/work/level/";
		list = dir + "leveldata.txt";
		init(paperPath, authorPath, confencePath);
		getLevelFromPaper(dir,createPapaerList(list));
	}

}
