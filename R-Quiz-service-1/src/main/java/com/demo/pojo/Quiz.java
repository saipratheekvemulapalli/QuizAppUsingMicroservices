package com.demo.pojo;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	@ElementCollection
	private List<Integer> questionIds;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public List<Integer> getQuestionIds() {
		return questionIds;
	}



	public void setQuestionIds(List<Integer> questionIds) {
		this.questionIds = questionIds;
	}
	


	
	
	
	
}
