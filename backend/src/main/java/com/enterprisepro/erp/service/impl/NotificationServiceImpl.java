package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.NotificationDto;
import com.enterprisepro.erp.entity.Notification;
import com.enterprisepro.erp.entity.User;
import com.enterprisepro.erp.repository.NotificationRepository;
import com.enterprisepro.erp.repository.UserRepository;
import com.enterprisepro.erp.security.UserPrincipal;
import com.enterprisepro.erp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal principal) {
            return userRepository.findById(principal.getId()).orElse(null);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NotificationDto> getUserNotifications(Pageable pageable) {
        User user = getAuthenticatedUser();
        if (user != null) {
            return notificationRepository.findByRecipientOrderByCreatedAtDesc(user, pageable).map(this::mapToDto);
        }
        return notificationRepository.findAllByOrderByCreatedAtDesc(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> getUnreadNotifications() {
        User user = getAuthenticatedUser();
        return notificationRepository.findByRecipientAndIsReadFalseOrderByCreatedAtDesc(user).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long getUnreadCount() {
        User user = getAuthenticatedUser();
        return notificationRepository.countUnreadNotifications(user);
    }

    @Override
    @Transactional
    public void markAsRead(Long id) {
        notificationRepository.findById(id).ifPresent(n -> {
            n.setRead(true);
            notificationRepository.save(n);
        });
    }

    @Override
    @Transactional
    public void markAllAsRead() {
        User user = getAuthenticatedUser();
        List<Notification> unread = notificationRepository.findByRecipientAndIsReadFalseOrderByCreatedAtDesc(user);
        unread.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(unread);
    }

    @Override
    @Transactional
    public NotificationDto sendNotification(NotificationDto dto) {
        Notification n = new Notification();
        n.setTitle(dto.getTitle());
        n.setMessage(dto.getMessage());
        n.setType(dto.getType() != null ? dto.getType() : "INFO");
        n.setCategory(dto.getCategory() != null ? dto.getCategory() : "SYSTEM");
        n.setLink(dto.getLink());
        n.setRead(false);

        User user = getAuthenticatedUser();
        n.setRecipient(user);

        Notification saved = notificationRepository.save(n);
        return mapToDto(saved);
    }

    private NotificationDto mapToDto(Notification n) {
        NotificationDto dto = new NotificationDto();
        dto.setId(n.getId());
        dto.setTitle(n.getTitle());
        dto.setMessage(n.getMessage());
        dto.setType(n.getType());
        dto.setCategory(n.getCategory());
        dto.setLink(n.getLink());
        dto.setRead(n.isRead());
        dto.setCreatedAt(n.getCreatedAt());
        return dto;
    }
}
