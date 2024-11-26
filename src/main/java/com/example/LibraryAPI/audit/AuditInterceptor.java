//package com.example.LibraryAPI.audit;
//
//import lombok.RequiredArgsConstructor;
//import org.hibernate.EmptyInterceptor;
//import org.hibernate.type.Type;
//import org.springframework.data.domain.Auditable;
//
//import java.io.Serializable;
//import java.util.Iterator;
//
//@RequiredArgsConstructor
//public class AuditInterceptor extends EmptyInterceptor {
//
//    @Override
//    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//        // Audit the INSERT operation
//        if (entity instanceof Auditable) {
//            System.out.println("Insert: " + entity);
//            // Log the INSERT operation
//        }
//        return super.onSave(entity, id, state, propertyNames, types);
//    }
//
//    @Override
//    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
//        // Audit the UPDATE operation
//        if (entity instanceof Auditable) {
//            System.out.println("Update: " + entity);
//            // Log the UPDATE operation
//        }
//        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
//    }
//
//    @Override
//    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//        // Audit the DELETE operation
//        if (entity instanceof Auditable) {
//            System.out.println("Delete: " + entity);
//            // Log the DELETE operation
//        }
//    }
//}
//
