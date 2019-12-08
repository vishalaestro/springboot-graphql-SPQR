package com.viz.graphqlspqr.vizgraphqlspqr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.viz.graphqlspqr.vizgraphqlspqr.Exception.CustomGraphQLErrorHandler;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;


@RestController
public class GraphQLController {
	
	@Autowired
	GraphQL graphQL;
	
	    @PostMapping(
	            value = "/graphQL"
	    )
	    public Object executeGraphQLPost(@RequestBody String queryBody,
	                                   GraphQLRequest graphQLRequest) {
	        String query = graphQLRequest.getQuery() == null ? queryBody : graphQLRequest.getQuery();
	        ExecutionResult executionResult =   graphQL.execute(query);
	        if(!executionResult.getErrors().isEmpty()) {
	        	List<GraphQLError> graphQLErrors = new CustomGraphQLErrorHandler().processErrors(executionResult.getErrors());
	        	ExecutionResult executionResultNew = new ExecutionResult() {
					
					@Override
					public Map<String, Object> toSpecification() {
						return executionResult.toSpecification();
					}
					
					@Override
					public boolean isDataPresent() {
						return executionResult.isDataPresent();
					}
					
					@Override
					public Map<Object, Object> getExtensions() {
						return executionResult.getExtensions();
					}
					
					@Override
					public List<GraphQLError> getErrors() {
						return graphQLErrors;
					}
					
					@Override
					public <T> T getData() {
						return executionResult.getData();
					}
				};
				return executionResultNew;
	        }else {
	        	return executionResult.toSpecification();
	        }
	        
	    }

}
