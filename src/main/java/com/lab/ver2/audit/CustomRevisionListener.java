package com.lab.ver2.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lab.ver2.security.CustomUserPrincipal;

public class CustomRevisionListener implements RevisionListener {
    
    @Override
    public void newRevision(Object revisionEntity){
        RevisionInfo revision = (RevisionInfo) revisionEntity;

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof CustomUserPrincipal user) {
            revision.setUserId(user.getId());
            revision.setUsername(user.getUsername());
        }
    }
}
