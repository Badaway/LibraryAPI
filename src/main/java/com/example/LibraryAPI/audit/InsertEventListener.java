//package com.example.LibraryAPI.audit;
//
//import org.hibernate.event.spi.PostInsertEvent;
//import org.hibernate.event.spi.PostInsertEventListener;
//import org.hibernate.persister.entity.EntityPersister;
//
//public class InsertEventListener implements PostInsertEventListener {
//    @Override
//    public void onPostInsert(PostInsertEvent event) {
//
//        System.out.println("Entity inserted: " + event.getEntity()+event.getClass()+event.getId());
//    }
//
//    @Override
//    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
//        return false;
//    }
//}
