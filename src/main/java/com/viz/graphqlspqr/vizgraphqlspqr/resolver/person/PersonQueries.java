package com.viz.graphqlspqr.vizgraphqlspqr.resolver.person;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viz.graphqlspqr.vizgraphqlspqr.Exception.ServiceException;
import com.viz.graphqlspqr.vizgraphqlspqr.entity.Person;
import com.viz.graphqlspqr.vizgraphqlspqr.entity.Phone;
import com.viz.graphqlspqr.vizgraphqlspqr.repository.PersonRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

@Service
public class PersonQueries  {
	
	@Autowired
	PersonRepository personRepository;

	@GraphQLQuery(name="persons")
	public List<Person> getPersons() throws ServiceException {
		return personRepository.findAll();
	}
	
	@GraphQLQuery
	public Person getPersonByName(@GraphQLArgument(name = "name") String name) throws ServiceException {
		return personRepository.findByName(name);
	}
	
	@GraphQLQuery
	public Person getPersonById(@GraphQLArgument(name = "id") Long id) throws ServiceException {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()) {
			return personRepository.findById(id).get();
		}else {
			throw new ServiceException(400,"Person Does not Exist");
		}
	}
	
	@GraphQLQuery
	public List<Phone> getConnectionsForPerson(@GraphQLArgument(name="id") Long id) throws ServiceException {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()) {
			return personRepository.findById(id).get().getPhones();
		}else {
			 throw new ServiceException(400,"No Connection for this Person");
		}
		
	}
}
