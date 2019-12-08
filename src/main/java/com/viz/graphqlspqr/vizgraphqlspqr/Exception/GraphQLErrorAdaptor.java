package com.viz.graphqlspqr.vizgraphqlspqr.Exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class GraphQLErrorAdaptor implements GraphQLError {
	
	private static final long serialVersionUID = 1L;
	private final GraphQLError graphQLError;

    public GraphQLErrorAdaptor(GraphQLError graphQLError) {
        this.graphQLError = graphQLError;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return graphQLError.getLocations();
    }

    @Override
    public ErrorClassification getErrorType() {
        return graphQLError.getErrorType();
    }

    @Override
    public String getMessage() {
        return graphQLError.getMessage();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return graphQLError.getExtensions();
    }

}
