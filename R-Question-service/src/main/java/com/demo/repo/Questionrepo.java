package com.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.pojo.Question;
import com.demo.pojo.QuestionforDisplay;

import org.springframework.data.jpa.repository.Query;


@Repository
public interface Questionrepo extends JpaRepository<Question, Integer> {
	
	public List<Question> findByCategory(String category);
	
	public Question deleteById(int id);
	
	
	@Query(value="select q.id from Question q where q.category=:category",nativeQuery=true)
	public List<Integer> getQuestionsIdByCategory(String category) ;

	public void save(QuestionforDisplay qfd);

	
	
}
