package com.viz.graphqlspqr.vizgraphqlspqr.resolver.phone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viz.graphqlspqr.vizgraphqlspqr.Exception.ServiceException;
import com.viz.graphqlspqr.vizgraphqlspqr.entity.Phone;
import com.viz.graphqlspqr.vizgraphqlspqr.repository.PhoneRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

@Service
public class PhoneQueries {

	@Autowired
	PhoneRepository phoneRepository;
	
	@GraphQLQuery
	public List<Phone> getAllConnections() throws ServiceException {
		return phoneRepository.findAll();
	}
	
	@GraphQLQuery
	public Phone getConnectionDetails(@GraphQLArgument(name = "phoneNumber") String number) throws ServiceException {
		if(number==null || number.isEmpty()) {
			throw new ServiceException (400,"Invalid Number");
		}
		return phoneRepository.findByNumber(number);
	}
}
