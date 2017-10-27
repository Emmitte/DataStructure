package entity;

import java.util.Arrays;
import java.util.List;

public class Author {
	public int id;
	public String name;
	public List<Integer> paper_id_list;
	public List<String> title_list;
	public List<Integer> orderInPaper_list;
	public List<String> conference_name_list;
	public List<Integer> conference_id_list;
	public List<Integer> conference_label_list;
	public int[] classCount;
	public int predict;
	public final int N_CONFERENCE_LABEL = 20;
	public Author(int id,String name,List<Integer> paper_id_list,List<String> title_list,List<Integer> orderInPaper_list,List<String> conference_name_list,List<Integer> conference_id_list,List<Integer> conference_label_list) {
		this.id = id;
		this.name = name;
		this.paper_id_list = paper_id_list;
		this.title_list = title_list;
		this.orderInPaper_list = orderInPaper_list;
		this.conference_name_list = conference_name_list;
		this.conference_id_list = conference_id_list;
		this.conference_label_list = conference_label_list;
		this.classCount = new int[N_CONFERENCE_LABEL];
		this.predict = 0;
		int i;
		for(i = 0;i < N_CONFERENCE_LABEL;i++){
			classCount[i] = 0;
		}
		calClassCount();
		this.predict = getPredict();
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
	public List<Integer> getPaper_id_list() {
		return paper_id_list;
	}
	public void setPaper_id_list(List<Integer> paper_id_list) {
		this.paper_id_list = paper_id_list;
	}
	public List<String> getTitle_list() {
		return title_list;
	}
	public void setTitle_list(List<String> title_list) {
		this.title_list = title_list;
	}
	public List<Integer> getOrderInPaper_list() {
		return orderInPaper_list;
	}
	public void setOrderInPaper_list(List<Integer> orderInPaper_list) {
		this.orderInPaper_list = orderInPaper_list;
	}
	public List<String> getConference_name_list() {
		return conference_name_list;
	}
	public void setConference_name_list(List<String> conference_name_list) {
		this.conference_name_list = conference_name_list;
	}
	public List<Integer> getConference_id_list() {
		return conference_id_list;
	}
	public void setConference_id_list(List<Integer> conference_id_list) {
		this.conference_id_list = conference_id_list;
	}
	public List<Integer> getConference_label_list() {
		return conference_label_list;
	}
	public void setConference_label_list(List<Integer> conference_label_list) {
		this.conference_label_list = conference_label_list;
	}
	public int[] getClassCount() {
		return classCount;
	}
	public void calClassCount() {
		int i,n;
		n = conference_label_list.size();
		for(i = 0;i < n;i++){
			classCount[conference_label_list.get(i)]++;
		}
	}
	public int getPredict() {
		int i,max,loc;
		loc = 0;
		max = classCount[0];
		for(i = 1;i < N_CONFERENCE_LABEL;i++){
			if(max < classCount[i]){
				max = classCount[i];
				loc = i;
			}
		}
		this.predict = loc;
		return predict;
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", paper_id_list=" + paper_id_list + ", title_list=" + title_list
				+ ", orderInPaper_list=" + orderInPaper_list + ", conference_name_list=" + conference_name_list
				+ ", conference_id_list=" + conference_id_list + ", conference_label_list=" + conference_label_list
				+ ", classCount=" + Arrays.toString(classCount) + ", predict=" + predict + "]";
	}
	
}
