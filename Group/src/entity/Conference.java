package entity;

import java.util.List;

public class Conference {
	public int id;
	public String name;
	public int label;
	public List<Integer> paper_id_list;
	public List<Integer> author_id_list;
	public final int N_CONFERENCE_LABEL = 20;
	public Conference(int id,String name,int label,List<Integer> paper_id_list,List<Integer> author_id_list) {
		this.id = id;
		this.name = name;
		this.label = label;
		this.paper_id_list = paper_id_list;
		this.author_id_list = author_id_list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public List<Integer> getPaper_id_list() {
		return paper_id_list;
	}
	public void setPaper_id_list(List<Integer> paper_id_list) {
		this.paper_id_list = paper_id_list;
	}
	public void insertPaperId(int id) {
		this.paper_id_list.add(id);
	}
	public void deletePaperId(int id) {
		int i,n;
		n = paper_id_list.size();
		for(i = 0;i < n;i++){
			if(paper_id_list.get(i) == id){
				paper_id_list.remove(i);
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
	public void insertAuthorId(int id) {
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
		return "Conference [id=" + id + ", name=" + name + ", label=" + label + ", paper_id_list="
				+ paper_id_list + ", author_id_list=" + author_id_list + "]";
	}
	
}
