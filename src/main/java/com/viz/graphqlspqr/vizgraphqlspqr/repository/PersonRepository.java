package com.viz.graphqlspqr.vizgraphqlspqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viz.graphqlspqr.vizgraphqlspqr.Exception.ServiceException;
import com.viz.graphqlspqr.vizgraphqlspqr.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	public Person findByName(String name) throws ServiceException;
}
