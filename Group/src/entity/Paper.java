package entity;

import java.util.List;

public class Paper {
	public int id;
	public String title;
	public int conference_id;
	public String conference_name;
	public int conference_label;
	public int year;
	public List<String> author_name_list;
	public List<Integer> author_id_list;
	public final int N_CONFERENCE_LABEL = 20;
	public Paper(int id,String title,int conference_id,String conference_name,int conference_label,int year,List<String> author_name_list,List<Integer> author_id_list) {
		this.id = id;
		this.title = title;
		this.conference_id = conference_id;
		this.conference_name = conference_name;
		this.conference_label = conference_label;
		this.year = year;
		this.author_name_list = author_name_list;
		this.author_id_list = author_id_list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getConference_id() {
		return conference_id;
	}
	public void setConference_id(int conference_id) {
		this.conference_id = conference_id;
	}
	public String getConference_name() {
		return conference_name;
	}
	public void setConference_name(String conference_name) {
		this.conference_name = conference_name;
	}
	public int getConference_label() {
		return conference_label;
	}
	public void setConference_label(int conference_label) {
		this.conference_label = conference_label;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<String> getAuthor_list() {
		return author_name_list;
	}
	public void setAuthor_list(List<String> author_name_list) {
		this.author_name_list = author_name_list;
	}
	public void addAuthor(String author) {
		this.author_name_list.add(author);
	}
	public void deleteAuthor(String author) {
		int i,n;
		n = author_name_list.size();
		for(i = 0;i < n;i++){
			if(author_name_list.get(i).equals(author)){
				author_name_list.remove(i);
				break;
			}
		}
	}
	public List<Integer> getAuthor_id_list() {
		return author_id_list;
	}
	public void setAuthor_id_list(List<Integer> author_id_list) {
		this.author_id_list = author_id_list;
	}
	public void addAuthorId(int id) {
		this.author_id_list.add(id);
	}
	public void deleteAuthorId(int id) {
		int i,n;
		n = author_id_list.size();
		for(i = 0;i < n;i++){
			if(author_id_list.get(i) == id){
				author_id_list.remove(i);
				break;
			}
		}
	}
	@Override
	public String toString() {
		return "Paper [id=" + id + ", title=" + title + ", conference_id=" + conference_id + ", conference_name=" + conference_name + ", conference_label="
				+ conference_label + ", year=" + year + ", author_name_list=" + author_name_list + ", author_id_list="
				+ author_id_list + "]";
	}
	
}
