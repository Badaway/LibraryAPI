//package com.example.LibraryAPI.audit;
//
//import com.example.LibraryAPI.model.AuditLog;
//import com.example.LibraryAPI.service.AuditService;
//import org.hibernate.event.spi.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Date;
//
//public class HibernateEventListener implements PreInsertEventListener, PreUpdateEventListener, PreDeleteEventListener {
//
//    @Autowired
//    private AuditService auditService;
//    @Override
//    public boolean onPreDelete(PreDeleteEvent preDeleteEvent) {
//        Object entity = preDeleteEvent.getEntity();
//        var id = entity.getClass().getFields();
//        var log= new AuditLog()
//                .setEntityId(id.toString())
//                .setEntity(entity.getClass().getName())
//                .setAction("Delete")
//                .setUsername(getUsername())
//                .setTimestamp(new Date())
//                .setParams("NN")
//                .setOldValue("old")
//                .setNewValue("new");
//        auditService.createLog(log);
//        System.out.println("Delete event detected: " + entity);
//        return false;
//    }
//
//    @Override
//    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
//        Object entity = preInsertEvent.getEntity();
//        var id = entity.getClass().getFields();
//        var log= new AuditLog()
//                .setEntityId(id.toString())
//                .setEntity(entity.getClass().getName())
//                .setAction("Insert")
//                .setUsername(getUsername())
//                .setTimestamp(new Date())
//                .setParams("NN")
//                .setOldValue("old")
//                .setNewValue("new");
//        auditService.createLog(log);
//        System.out.println("Insert event detected: " + entity);
//        return false;
//    }
//
//    @Override
//    public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {
//
//        Object entity = preUpdateEvent.getEntity();
//        var id = entity.getClass().getFields();
//        var log= new AuditLog()
//                .setEntityId(id.toString())
//                .setEntity(entity.getClass().getName())
//                .setAction("Insert")
//                .setUsername(getUsername())
//                .setTimestamp(new Date())
//                .setParams("NN")
//                .setOldValue("old")
//                .setNewValue("new");
//        auditService.createLog(log);
//        System.out.println("Update event detected: " + entity);
//        return false;
//    }
//
//    public  String getUsername(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication != null ? authentication.getName() : "anonymous";
//    }
//}
