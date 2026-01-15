package com.mohamedbob.estate.dto;

import java.time.LocalDateTime;

public class ChatMessageDTO {
    private Long id;
    private Long inquiryId; // <-- ajouté
    private Long senderId;
    private String senderName;
    private String content;
    private LocalDateTime created;
    private boolean isAdmin;

    public ChatMessageDTO() {}

    public ChatMessageDTO(Long id, Long inquiryId, Long senderId, String senderName,
                          String content, LocalDateTime created, boolean isAdmin) {
        this.id = id;
        this.inquiryId = inquiryId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.created = created;
        this.isAdmin = isAdmin;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInquiryId() { return inquiryId; } // <-- ajouté
    public void setInquiryId(Long inquiryId) { this.inquiryId = inquiryId; }

    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }
}
