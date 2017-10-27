package script;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import algorithm.Rank;
import entity.Author;
import entity.Conference;
import entity.Enty;
import entity.Paper;

public class OperateTable {
	
	public static List<Paper> paperList = new LinkedList<>();
	public static List<Author> authorList = new LinkedList<>();
	public static List<Conference> conferenceList = new LinkedList<>();
	
	public static FileReader fr=null;
	public static BufferedReader br=null;
	public static String line=null;
	
	/**
	 * input:paperPath,authorPath,confencePath
	 * 初始化table
	 */
	public static void initTable(String paperPath,String authorPath,String confencePath) {
		CreateTable.init(paperPath, authorPath, confencePath);
		paperList = CreateTable.paperList;
		authorList = CreateTable.authorList;
		conferenceList = CreateTable.conferenceList;
	}
	
	/**
	 * input:path
	 * 将文件生成list
	 */
	public static List<List<Integer>> readList(String path) {
		List<List<Integer>> listArray = new ArrayList<>();
		int id = 0;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				List<Integer> list = new ArrayList<>();
				StringTokenizer sz = new StringTokenizer(line);
				while(sz.hasMoreTokens()){
					id = Integer.parseInt(sz.nextToken());
					if(!list.contains(id)){
						list.add(id);
					}
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
	
	public static void test() {
		String dir,authorPath,paperPath,conferencePath;
		dir = "data/work/level/";
		authorPath = dir + "author.txt";
		paperPath = dir + "paper.txt";
		conferencePath = dir + "conference.txt";
		List<List<Integer>> authorlist = readList(authorPath);
		List<List<Integer>> paperlist = readList(paperPath);
		List<List<Integer>> conferencelist = readList(conferencePath);
		int i,n;
		n = authorlist.size();
		Rank rank = new Rank(paperList, authorList, conferenceList);
		for(i = 0;i < n;i++){
			System.out.println("level "+(i+1)+" :");
			rank.rank(authorlist.get(i), paperlist.get(i), conferencelist.get(i));
		}
		
	}

	public static void main(String[] args) {
		String dir,paperPath,authorPath,confencePath,list;
		dir = "data/work/rank/";
		
		paperPath = dir + "PaperDataSet.txt";
		authorPath = dir + "AuthorDataSet.txt";
		confencePath = dir + "ConferenceDataSet.txt";
		
		initTable(paperPath, authorPath, confencePath);
		test();
	}

}
