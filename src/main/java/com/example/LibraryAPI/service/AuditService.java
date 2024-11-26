package com.example.LibraryAPI.service;


import com.example.LibraryAPI.model.AuditLog;
import com.example.LibraryAPI.repository.AuditLogRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public static final String queueName = "Audit";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public AuditLog createLog(AuditLog auditLog){

        auditLog.setTimestamp(new Date())
                .setUsername(getUsername());
        rabbitTemplate.convertAndSend(queueName, auditLog.toString());
        return auditLogRepository.save(auditLog);
    }

    public static String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : "anonymous";
    }


}
