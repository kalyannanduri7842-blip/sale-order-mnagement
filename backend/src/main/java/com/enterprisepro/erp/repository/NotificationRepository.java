package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Notification;
import com.enterprisepro.erp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Page<Notification> findByRecipientOrderByCreatedAtDesc(User recipient, Pageable pageable);
    List<Notification> findByRecipientOrderByCreatedAtDesc(User recipient);
    List<Notification> findByRecipientAndIsReadFalseOrderByCreatedAtDesc(User recipient);
    
    @Query("SELECT COUNT(n) FROM Notification n WHERE (n.recipient = :recipient OR n.recipient IS NULL) AND n.isRead = false")
    long countUnreadNotifications(@Param("recipient") User recipient);

    Page<Notification> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
