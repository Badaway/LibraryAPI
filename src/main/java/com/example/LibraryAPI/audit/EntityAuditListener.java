//package com.example.LibraryAPI.audit;
//
//import com.example.LibraryAPI.model.AuditLog;
//import com.example.LibraryAPI.service.AuditService;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreRemove;
//import jakarta.persistence.PreUpdate;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.hibernate.event.spi.PostInsertEvent;
//import org.hibernate.event.spi.PostInsertEventListener;
//import org.hibernate.event.spi.PreInsertEvent;
//import org.hibernate.event.spi.PreInsertEventListener;
//import org.hibernate.persister.entity.EntityPersister;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Component
//public class EntityAuditListener  implements PostInsertEventListener {
//
//    @Autowired
//    private AuditService auditService;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//
//    @PrePersist
//    public void prePersist(Object entity) throws NoSuchFieldException {
//        var id = entity.getClass().getFields();
//        var log= new AuditLog()
//                .setEntityId(id.toString())
//                .setEntity(entity.getClass().getName())
//                .setAction("insert")
//                .setUsername(getUsername())
//                .setTimestamp(new Date())
//                .setParams("NN")
//                .setOldValue("old")
//                .setNewValue("new");
//        //auditService.createLog(log);
//
//
//        System.out.println("Before insert: " + entity);
//    }
//
//    @PreUpdate
//    public void preUpdate(Object entity) throws NoSuchFieldException {
//        var id = entity.getClass().getFields();
//        var log= new AuditLog()
//                .setEntityId(id.toString())
//                .setEntity(entity.getClass().getName())
//                .setAction("update")
//                .setUsername(getUsername())
//                .setTimestamp(new Date())
//                .setParams("NN")
//                .setOldValue("old")
//                .setNewValue("new");
//      // auditService.createLog(log);
//        System.out.println("Before update: " + entity);
//    }
//
//    @PreRemove
//    public void preDelete(Object entity) throws NoSuchFieldException {
//        var id = entity.getClass().getFields();
//        var log= new AuditLog()
//                .setEntityId(id.toString())
//                .setEntity(entity.getClass().getName())
//                .setAction("delete")
//                .setUsername(getUsername())
//                .setTimestamp(new Date())
//                .setParams("NN")
//                .setOldValue("old")
//                .setNewValue("new");
//      // auditService.createLog(log);
//        System.out.println("Before update: " + entity);
//    }
//
//    public  String getUsername(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication != null ? authentication.getName() : "anonymous";
//    }
//
//
//
//    @Override
//    public void onPostInsert(PostInsertEvent entity) {
//        var id = entity.getClass().getFields();
//        var log= new AuditLog()
//                .setEntityId(id.toString())
//                .setEntity(entity.getClass().getName())
//                .setAction("delete")
//                .setUsername(getUsername())
//                .setTimestamp(new Date())
//                .setParams("NN")
//                .setOldValue("old")
//                .setNewValue("new");
//        auditService.createLog(log);
//        System.out.println("Before update: " + entity);
//
//    }
//
//    @Override
//    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
//        return true;
//    }
//}
