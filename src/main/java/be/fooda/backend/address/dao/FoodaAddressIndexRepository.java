package be.fooda.backend.address.dao;


import be.fooda.backend.address.model.entity.FoodaAddress;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Repository
public class FoodaAddressIndexRepository {

    private final EntityManager entityManager;
    final int RESULTS_PER_PAGE = 25;

    private FullTextEntityManager entityManager() {
        return Search.getFullTextEntityManager(entityManager);
    }

    public FoodaAddress filterByGeolocation(String latitude, String longitude) {
        return null;
    }


    @Transactional
    public List<FoodaAddress> simple(Set<String> keywords, Pageable pageable) {

        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder
                    Query query = qb
                            .keyword()
                            .onField("address.house")
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaAddress.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaAddress>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FoodaAddress> fuzzy(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder with fuzzyQuery
                    Query query = qb
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("street")
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaAddress.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaAddress>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FoodaAddress> range(Long start, Long end, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        //Generate a Lucene query using the builder with fuzzyQuery
        //Generate a Lucene range query using the builder
        // Range queries search for a value in between given boundaries.
        // This can be applied to numbers, dates, timestamps, and strings.
        Query query = qb
                .range()
                .onField("id")
                .from(start)
                .to(end)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaAddress.class);

        fullTextQuery.setSort(qb.sort()
                .byField("name")
                .desc()
                .andByScore()
                .createSort());
        fullTextQuery.setMaxResults(pageable.getPageSize());
        fullTextQuery.setFirstResult(pageable.getPageNumber());

        //returns JPA managed entities
        return (List<FoodaAddress>) fullTextQuery.getResultList();
    }

    @Transactional
    public List<FoodaAddress> wildcard(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene wildcard query using the builder
                    Query query = qb
                            .keyword()
                            .wildcard()
                            .onField("street")
                            .matching("1*")
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaAddress.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaAddress>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FoodaAddress> phrase(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene phrase query using the builder
                    Query query = qb
                            .phrase()
                            .withSlop(1)
                            .onField("street")
                            .sentence(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaAddress.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaAddress>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FoodaAddress> structured(String queryString, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager fullTextEntityManager = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //The following query types are supported:
        //boolean (AND using “+”, OR using “|”, NOT using “-“)
        //prefix (prefix*)
        //phrase (“some phrase”)
        //precedence (using parentheses)
        //fuzzy (fuzy~2)
        //near operator for phrase queries (“some phrase”~3)
        Query query = qb
                .simpleQueryString()
                .onField("street")
                .matching(queryString)
                .createQuery();


        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaAddress.class);

        fullTextQuery.setSort(qb.sort()
                .byField("name")
                .desc()
                .andByScore()
                .createSort());
        fullTextQuery.setMaxResults(pageable.getPageSize());
        fullTextQuery.setFirstResult(pageable.getPageNumber());

        //returns JPA managed entities
        return (List<FoodaAddress>) fullTextQuery.getResultList();
    }

    @Transactional
    public List<FoodaAddress> subclass(Set<String> keywords, Pageable pageable) {

        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder
                    Query query = qb
                            .keyword()
                            .onFields(
                                    "municipality.name",
                                    "municipality.postcode",
                                    "city."
                            )
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaAddress.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("name")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaAddress>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private QueryBuilder qBuilder(FullTextEntityManager fullTextEntityManager) {
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(FoodaAddress.class)
                // .overridesForField("ngram","stem_analyzer_definition")
                .get();
    }

    public List<FoodaAddress> searchCombined(Set<String> keywords) {
        // get the full text entity manager
        FullTextEntityManager fullTextEntityManager = Search.
                getFullTextEntityManager(entityManager);

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(FoodaAddress.class).get();

        BooleanJunction<BooleanJunction> combinedJunction = queryBuilder.bool();
        List<Query> queries = keywords.stream()
                .map(keyword -> {
                    Query postcodeQuery = queryBuilder
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("productName")
                            .matching(keyword)
                            .createQuery();

                    Query descriptionQuery = queryBuilder
                            .phrase()
                            .withSlop(1)
                            .onField("description")
                            .sentence(keyword)
                            .createQuery();


                    // a combined query from all sub queries for each keyword..
                    return queryBuilder
                            .bool()
                            .should(postcodeQuery)

                            .createQuery();
                })
                .collect(Collectors.toList());

        for (Query query : queries) {
            combinedJunction.should(query);
        }

        Query combinedQuery = combinedJunction.createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(combinedQuery, FoodaAddress.class);
        jpaQuery.setMaxResults(RESULTS_PER_PAGE);
        jpaQuery.setFirstResult(1);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<FoodaAddress> results = jpaQuery.getResultList();
        return results;
    }

}
