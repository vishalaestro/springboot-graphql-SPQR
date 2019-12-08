package com.viz.graphqlspqr.vizgraphqlspqr.resolver.person;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viz.graphqlspqr.vizgraphqlspqr.Exception.ServiceException;
import com.viz.graphqlspqr.vizgraphqlspqr.entity.Person;
import com.viz.graphqlspqr.vizgraphqlspqr.entity.Phone;
import com.viz.graphqlspqr.vizgraphqlspqr.repository.PersonRepository;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;

@Service
public class PersonMutation  {

	@Autowired
	PersonRepository personRepository;
	
	@GraphQLMutation
	public Person addConnection (@GraphQLNonNull Person person) throws ServiceException {
		validatePersonObject(person);
		return personRepository.save(person);
	}
	
	
	private void validatePersonObject(Person person) throws ServiceException {
		if(person.getAddress()==null) {
			throw new ServiceException(400,"Address is mandatory");
		}
		if(person.getName()==null) {
			throw new ServiceException(400,"Name is mandatory");
		}
		
		if(person.getPhones()==null) {
			throw new ServiceException(400,"Connection Details are mandatory");
		}
		
		StringJoiner joiner = new StringJoiner("\n");
		List<Phone> connections = person.getPhones();
		for(Phone phone : connections) {
			if(phone.getNumber()==null) {
				joiner.add("Number is mandatory for "+phone.getType());
			}
		}
		if(!joiner.toString().isEmpty()) {
			throw new ServiceException(400,joiner.toString());
		}
	}
}
