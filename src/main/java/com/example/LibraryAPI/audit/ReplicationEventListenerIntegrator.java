//package com.example.LibraryAPI.audit;
//
//import org.hibernate.boot.Metadata;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
//import org.hibernate.event.service.spi.EventListenerRegistry;
//import org.hibernate.event.spi.EventType;
//import org.hibernate.integrator.spi.Integrator;
//import org.hibernate.service.spi.SessionFactoryServiceRegistry;
//import org.jetbrains.annotations.NotNull;
//
//public class ReplicationEventListenerIntegrator implements Integrator {
//
//    public static final ReplicationEventListenerIntegrator INSTANCE =
//            new ReplicationEventListenerIntegrator();
//
//    @Override
//    public void integrate(
//            Metadata metadata,
//            SessionFactoryImplementor sessionFactory,
//            SessionFactoryServiceRegistry serviceRegistry) {
//
//        final EventListenerRegistry eventListenerRegistry =
//                serviceRegistry.getService(EventListenerRegistry.class);
//
//        assert eventListenerRegistry != null;
//        eventListenerRegistry.appendListeners(
//                EventType.POST_INSERT,
//                InsertEventListener.class
//        );
//
//
//    }
//
//    @Override
//    public void disintegrate(
//            @NotNull SessionFactoryImplementor sessionFactory,
//            @NotNull SessionFactoryServiceRegistry serviceRegistry) {
//
//    }
//}