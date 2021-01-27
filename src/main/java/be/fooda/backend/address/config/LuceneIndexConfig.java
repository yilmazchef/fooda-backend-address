package be.fooda.backend.address.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class LuceneIndexConfig {

    @Bean
    public LuceneIndexServiceBean luceneIndexServiceBean(EntityManagerFactory entityManagerFactory) {
        LuceneIndexServiceBean luceneIndexServiceBean = new LuceneIndexServiceBean(entityManagerFactory);
        luceneIndexServiceBean.triggerIndexing();
        return luceneIndexServiceBean;
    }
}
