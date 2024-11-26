//package com.example.LibraryAPI.audit;
//
//
//import com.example.LibraryAPI.model.AuditLog;
//import com.example.LibraryAPI.repository.AuditLogRepository;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Aspect
//@Component
//public class AuditTrailAspect {
//
//    @Autowired
//    private AuditLogRepository auditLogRepository;
//
////    @Pointcut("execution(* org.springframework.data.repository.CrudRepository+.save*(..))")
////    public void serviceMethods() {}
//
//    @Pointcut("execution(* org.springframework.data.repository.CrudRepository+.save*(..))")
//    public void saveMethod() {}
//
//    @Pointcut("execution(* org.springframework.data.repository.CrudRepository+.delete*(..))")
//    public void deleteMethod() {}
//
//    @Pointcut("execution(* org.springframework.data.repository.CrudRepository+.saveAndFlush(..))")
//    public void saveAndFlushMethod() {}
//
//    @Pointcut("execution(* org.springframework.data.repository.CrudRepository+.deleteById(..))")
//    public void deleteByIdMethod() {}
//
////    @Before("saveMethod()")
////    public void logAction(JoinPoint joinPoint) {
////
////
////        String methodName = joinPoint.getSignature().getName();
////        String className = joinPoint.getTarget().getClass().getSimpleName();
////
////
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String username = authentication != null ? authentication.getName() : "anonymous";
////
////
////        Object[] args = joinPoint.getArgs();
////        StringBuilder params = new StringBuilder();
////        for (Object arg : args) {
////            params.append(arg.toString()).append(", ");
////        }
////
////        AuditLog auditLog = new AuditLog();
////        auditLog.setAction(methodName);
////        auditLog.setEntity(className);
////        auditLog.setUsername(username);
////        auditLog.setTimestamp(new Date());
////        auditLog.setParams(params.toString());
////        auditLog.setOldValue("old");
////        auditLog.setNewValue("new");
////
////
////        auditLogRepository.save(auditLog);
////    }
//
//    @Before("saveMethod() || saveAndFlushMethod()")
//    public void logInsertOrUpdate(JoinPoint joinPoint) {
//        Object entity = joinPoint.getArgs()[0];  // The entity being saved
//        if (entity != null) {
//            // You can add logic to capture both insert and update based on entity state
//            System.out.println("Audit Trail: Entity Inserted/Updated - " + entity);
//            // Logic for inserting or updating the audit trail (e.g., logging, saving to database)
//
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication != null ? authentication.getName() : "anonymous";
//
//
//
//
//        AuditLog auditLog = new AuditLog();
//        auditLog.setAction("insert");
//        auditLog.setEntity(entity.getClass().getName());
//        auditLog.setUsername(username);
//        auditLog.setTimestamp(new Date());
//        auditLog.setParams("NN");
//        auditLog.setOldValue("old");
//        auditLog.setNewValue("new");
//
//
//        auditLogRepository.save(auditLog);
//
//        }
//    }
//
//}
