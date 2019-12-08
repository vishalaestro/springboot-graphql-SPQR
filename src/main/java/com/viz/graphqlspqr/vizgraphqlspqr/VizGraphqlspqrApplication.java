package com.viz.graphqlspqr.vizgraphqlspqr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.viz.graphqlspqr.vizgraphqlspqr.resolver.person.PersonMutation;
import com.viz.graphqlspqr.vizgraphqlspqr.resolver.person.PersonQueries;
import com.viz.graphqlspqr.vizgraphqlspqr.resolver.phone.PhoneMutation;
import com.viz.graphqlspqr.vizgraphqlspqr.resolver.phone.PhoneQueries;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;

@SpringBootApplication
public class VizGraphqlspqrApplication {

	public static void main(String[] args) {
		SpringApplication.run(VizGraphqlspqrApplication.class, args);
	}


	@Bean
	public GraphQLSchema generateGraphQLSchema(PersonMutation personMutation, PersonQueries personQueries,
			PhoneMutation phoneMutation, PhoneQueries phoneQueries) {
		GraphQLSchema schema = new GraphQLSchemaGenerator().withBasePackages("com.viz.graphqlspqr.vizgraphqlspqr")
				.withOperationsFromSingleton(personMutation).withOperationsFromSingleton(personQueries)
				.withOperationsFromSingleton(phoneMutation).withOperationsFromSingleton(phoneQueries).generate();
		return schema;
	}

	@Bean
	public GraphQL createGrahQLInstance(GraphQLSchema schema) {
		GraphQL graphQL = new GraphQL.Builder(schema).build();
		return graphQL;
	}

}
