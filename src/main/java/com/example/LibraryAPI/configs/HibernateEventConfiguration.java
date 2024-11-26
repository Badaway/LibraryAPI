//package com.example.LibraryAPI.configs;
//
//import com.example.LibraryAPI.audit.InsertEventListener;
//import org.hibernate.SessionFactory;
//import org.hibernate.event.service.spi.EventListenerRegistry;
//import org.hibernate.event.spi.EventType;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HibernateEventConfiguration {
//
//    @Bean
//    public void registerEventListeners(SessionFactory sessionFactory) {
//        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(InsertEventListener.class);
//
//        // Register the custom event listener
//        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(new InsertEventListener());
//    }
//}
