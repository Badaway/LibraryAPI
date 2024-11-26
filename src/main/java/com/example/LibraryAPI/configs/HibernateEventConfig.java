//package com.example.LibraryAPI.configs;
//
//
//import com.example.LibraryAPI.audit.EntityAuditListener;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.PersistenceUnit;
//import org.hibernate.event.service.spi.EventListenerRegistry;
//import org.hibernate.event.spi.*;
//import org.hibernate.internal.SessionFactoryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//
//@Configuration
//@Component
//public class HibernateEventConfig {
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//    @Autowired
//    private EntityAuditListener entityAuditListener;
//
//
//    @Bean
//    public EventListenerRegistry registerListener() {
//        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
//        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory)
//                .getServiceRegistry().getService(EventListenerRegistry.class);
//        assert registry != null;
//        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener((PostInsertEventListener) entityAuditListener);
////        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener((PreUpdateEventListener) entityAuditListener);
////        registry.getEventListenerGroup(EventType.PRE_DELETE).appendListener((PreDeleteEventListener) entityAuditListener);
//
//        return registry;
//
//    }
//}
