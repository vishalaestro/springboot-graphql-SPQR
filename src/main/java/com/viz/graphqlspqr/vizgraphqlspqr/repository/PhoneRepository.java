package com.viz.graphqlspqr.vizgraphqlspqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viz.graphqlspqr.vizgraphqlspqr.Exception.ServiceException;
import com.viz.graphqlspqr.vizgraphqlspqr.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{
	public Phone findByNumber(String number) throws ServiceException;
}
