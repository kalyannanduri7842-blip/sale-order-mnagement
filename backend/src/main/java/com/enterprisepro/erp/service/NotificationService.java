package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    Page<NotificationDto> getUserNotifications(Pageable pageable);
    List<NotificationDto> getUnreadNotifications();
    long getUnreadCount();
    void markAsRead(Long id);
    void markAllAsRead();
    NotificationDto sendNotification(NotificationDto notificationDto);
}
