package be.fooda.backend.address.config;

import lombok.SneakyThrows;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManagerFactory;

public class LuceneIndexServiceBean {

    private final FullTextEntityManager fullTextEntityManager;

    public LuceneIndexServiceBean(EntityManagerFactory entityManagerFactory){
        fullTextEntityManager = Search.getFullTextEntityManager(entityManagerFactory.createEntityManager());
    }

    @SneakyThrows
    public void triggerIndexing(){
        fullTextEntityManager.createIndexer().startAndWait();
    }

}
