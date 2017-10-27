package algorithm;

import java.util.LinkedList;
import java.util.List;

import entity.Author;
import entity.Conference;
import entity.Paper;
import util.Tool;

public class Rank {
	
	public static final double W = 0.5;
	public static final double AREA = 0.1;
	public static final int IterCount = 50;
	
	public List<Paper> paperList = new LinkedList<>();
	public List<Author> authorList = new LinkedList<>();
	public List<Conference> conferenceList = new LinkedList<>();
	
	double[] paperScore;
	double[] authorScore;
	double[] conferenceScore;
	
	public Rank(List<Paper> paperlist,List<Author> authorlist,List<Conference> conferencelist) {
		this.paperList = paperlist;
		this.authorList = authorlist;
		this.conferenceList = conferencelist;
	}
	
	public List<Paper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

	public List<Conference> getConferenceList() {
		return conferenceList;
	}

	public void setConferenceList(List<Conference> conferenceList) {
		this.conferenceList = conferenceList;
	}

	public double[] getPaperScore() {
		return paperScore;
	}

	public void setPaperScore(double[] paperScore) {
		int n = paperScore.length;
		this.paperScore = new double[n];
		this.paperScore = paperScore;
	}

	public double[] getAuthorScore() {
		return authorScore;
	}

	public void setAuthorScore(double[] authorScore) {
		int n = authorScore.length;
		this.authorScore = new double[n];
		this.authorScore = authorScore;
	}

	public double[] getConferenceScore() {
		return conferenceScore;
	}

	public void setConferenceScore(double[] conferenceScore) {
		int n = conferenceScore.length;
		this.conferenceScore = new double[n];
		this.conferenceScore = conferenceScore;
	}
	
	/**
	 * input:arr(double[][])
	 * 打印二维数组
	 */
	public void printArray(double[][] arr) {
		int i,j,n1,n2;
		n1 = arr.length;
		n2 = arr[0].length;
		for(i = 0;i < n1;i++){
			for(j = 0;j < n2;j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	/**
	 * input:arr(double[])
	 * 打印一维数组
	 */
	public void printArray(double[] arr) {
		int i,n;
		n = arr.length;
		for(i = 0;i < n;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	/**
	 * input:authorlist
	 * 生成Maa矩阵
	 */
	public double[][] makeMaa(List<Integer> authorlist) {
		int i,j,k,n,n_paper;
		n = authorlist.size();
		double[][] arr = new double[n][n];
		for(i = 0;i < n;i++){
			List<Integer> paperlist = authorList.get(authorlist.get(i)).paper_id_list;
			n_paper = paperlist.size();
			for(j = 0;j < n_paper;j++){
				List<Integer> temp = paperList.get(paperlist.get(j)).author_id_list;
				for(k = 0;k < temp.size();k++){
					if(authorlist.contains(temp.get(k))){
						arr[i][authorlist.indexOf(temp.get(k))]++;
					}
				}
			}
		}
		return arr;
	}
	/**
	 * input:paperlist
	 * 生成Mpp矩阵
	 */
	public double[][] makeMpp(List<Integer> paperlist) {
		int i,j,k,n,n_author;
		n = paperlist.size();
		double[][] arr = new double[n][n];
		for(i = 0;i < n;i++){
			List<Integer> authorlist = paperList.get(paperlist.get(i)).author_id_list;
			n_author = authorlist.size();
			for(j = 0;j < n_author;j++){
				List<Integer> temp = authorList.get(authorlist.get(j)).paper_id_list;
				for(k = 0;k < temp.size();k++){
					if(paperlist.contains(temp.get(k))){
						arr[i][paperlist.indexOf(temp.get(k))]++;
					}
				}
			}
		}
		return arr;
	}
	/**
	 * input:conferencelist
	 * 生成Mcc矩阵
	 */
	public double[][] makeMcc(List<Integer> conferencelist) {
		int i,j,k,n,n_paper;
		n = conferencelist.size();
		double[][] arr = new double[n][n];
		for(i = 0;i < n;i++){
			List<Integer> paperlist = conferenceList.get(conferencelist.get(i)).paper_id_list;
			n_paper = paperlist.size();
			for(j = 0;j < n_paper;j++){
				int temp = paperList.get(paperlist.get(j)).conference_id;
				if(conferencelist.contains(temp)){
					arr[i][conferencelist.indexOf(temp)]++;
				}
			}
		}
		return arr;
	}
	/**
	 * input:authorlist,paperlist
	 * 生成Map矩阵
	 */
	public double[][] makeMap(List<Integer> authorlist,List<Integer> paperlist) {
		int i,j,n_author,n_paper;
		n_author = authorlist.size();
		n_paper = paperlist.size();
		double[][] arr = new double[n_author][n_paper];
		for(i = 0;i < n_author;i++){
			for(j = 0;j < n_paper;j++){
				if(paperList.get(paperlist.get(j)).author_id_list.contains(authorlist.get(i))){
					arr[i][j]++;
				}
			}
		}
		return arr;
	}
	/**
	 * input:conferencelist,paperlist
	 * 生成Mcp矩阵
	 */
	public double[][] makeMcp(List<Integer> conferencelist,List<Integer> paperlist) {
		int i,j,n_conference,n_paper;
		n_conference = conferencelist.size();
		n_paper = paperlist.size();
		double[][] arr = new double[n_conference][n_paper];
		for(i = 0;i < n_conference;i++){
			for(j = 0;j < n_paper;j++){
				if(conferenceList.get(conferencelist.get(i)).paper_id_list.contains(paperlist.get(j))){
					arr[i][j]++;
				}
			}
		}
		return arr;
	}
	/**
	 * input:arr(double[][])
	 * 矩阵转置
	 */
	public double[][] reverseArray(double[][] arr) {
		int i,j,n1,n2;
		n1 = arr.length;
		n2 = arr[0].length;
		double[][] temp = new double[n2][n1];
		for(i = 0;i < n1;i++){
			for(j = 0;j < n2;j++){
				temp[j][i] = arr[i][j];
			}
		}
		return temp;
	}

	/**
	 * input:authorlist,paperlist,conferencelist
	 * 排序算法
	 */
	public void rank(List<Integer> authorlist,List<Integer> paperlist,List<Integer> conferencelist) {
		double[][] Maa = makeMaa(authorlist);
		double[][] Mpp = makeMpp(paperlist);
		double[][] Mcc = makeMcc(conferencelist);
		double[][] Map = makeMap(authorlist, paperlist);
		double[][] Mpa = reverseArray(Map);
		double[][] Mcp = makeMcp(conferencelist, paperlist);
		double[][] Mpc = reverseArray(Mcp);
		
		int i,n1,n2,n3,iter;
		double diff;
		n1 = authorlist.size();
		n2 = paperlist.size();
		n3 = conferencelist.size();
		double[] author1 = new double[n1];
		double[] author2 = new double[n1];
		double[] paper1 = new double[n2];
		double[] paper2 = new double[n2];
		double[] conference1 = new double[n3];
		double[] conference2 = new double[n3];
		
		// author rank: a-p-a
		iter = 0;
		diff = 0.0;
		for(i = 0;i < n1;i++){
			author1[i] = 1;
		}
		for(i = 0;i < n2;i++){
			paper1[i] = 1;
		}
		do {
			author2 = rankAuthorToPaper(author1, paper1, Maa, Map, Mpa,"author");
			diff = iterate(author1, author2);
			iter++;
		} while (iter < IterCount && diff > AREA);
		
		//author rank ret:
		Tool.output("author", authorlist, author2);
		//middle paper rank ret:
		Tool.output("middle paper", paperlist, paper1);

		// paper rank: p-a-p
		iter = 0;
		diff = 0.0;
		for (i = 0; i < n2; i++) {
			paper1[i] = 1;
		}
		do {
			paper2 = rankAuthorToPaper(paper1, author2, Mpp, Mpa, Map,"paper");
			diff = iterate(paper1, paper2);
			iter++;
		} while (iter < IterCount && diff > AREA);
		// paper rank ret:
		Tool.output("paper", paperlist, paper2);
		// middle author rank ret:
		Tool.output("middle author ", authorlist, author2);

		// conference rank: c-p-c
		iter = 0;
		diff = 0.0;
		for (i = 0; i < n3; i++) {
			conference1[i] = 1;
		}
		do {
			conference2 = rankAuthorToPaper(conference1, paper2, Mcc, Mpc, Mcp,"conference");
			diff = iterate(conference1, conference2);
			iter++;
		} while (iter < IterCount && diff > AREA);
		// conference rank ret:
		Tool.output("conference", conferencelist, conference2);
		// middle paper rank ret:
		Tool.output("middle paper", paperlist, paper2);
	}
	/**
	 * input:RankAuthor,RankPaper,Maa,Map,Mpa,aim
	 * 排序规则:a-p-a或p-a-p或c-p-c
	 */
	public double[] rankAuthorToPaper(double[] RankAuthor, double[] RankPaper, double[][] Maa, double[][] Map,
			double[][] Mpa,String aim) {
		int i, j, n_author, n_paper, temp;
		n_author = RankAuthor.length;
		n_paper = Mpa.length;
		double[] author = new double[n_author];
		double[] paper = new double[n_paper];
		
		// 计算Rule 1
		for (i = 0; i < n_author; i++) {
			temp = 0;
			for (j = 0; j < n_author; j++) {
				temp += (Maa[i][j] * RankAuthor[j] * W);
			}
			author[i] += (temp + RankAuthor[i] * (1 - W));
		}
		
		if(aim.endsWith("conference")){
			paper = RankPaper;
		}else {
			// 计算Rule 2
			for (i = 0; i < n_paper; i++) {
				temp = 0;
				for (j = 0; j < n_author; j++) {
					//System.out.println(":"+n_author);
					//System.out.println(i+","+Mpa.length+" "+Mpa[i].length);
					//System.out.println("i="+i+" j="+j+" mpa.l="+Mpa.length+" mpa[i].l= author="+author.length);
					temp += (Mpa[i][j] * author[j]);
				}
				paper[i] += temp;
				RankPaper[i] = paper[i];
			}
		}
		
		// 计算Rule 3
		for (i = 0; i < n_author; i++) {
			temp = 0;
			for (j = 0; j < n_paper; j++) {
				temp += (Map[i][j] * paper[j] * W);
			}
			author[i] += (temp + author[i] * (1 - W));
		}
		return author;
	}
	/**
	 * input:arr1,arr2
	 * 迭代
	 */
	public double iterate(double[] arr1,double[] arr2) {
		int i,n;
		double difference = 0.0;
		n = arr1.length;
		for(i = 0;i < n;i++){
			difference += (arr2[i] - arr1[i]);
		}
		difference /= n;
		difference = Math.abs(difference);
		return difference;
	}
}
