//package com.example.LibraryAPI.configs;
//
//import com.example.LibraryAPI.audit.AuditInterceptor;
//import jakarta.persistence.EntityManagerFactory;
//import org.hibernate.SessionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class HibernateConfiguration {
//    @Bean
//    public AuditInterceptor auditInterceptor() {
//        return new AuditInterceptor();
//    }
//
//    @Bean
//    public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory) {
//        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
//        sessionFactory.getCurrentSession().getSessionFactory().withOptions().interceptor(auditInterceptor());
//        return sessionFactory;
//    }
//}
