package com.edatablock.rpa.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EmailProcessing entity.
 */
public class EmailProcessingDTO implements Serializable {

    private Long id;

    private String messageId;

    private String receiveFrom;

    private Instant receivedTime;

    private String numberOfAttachments;

    private Long clientEmailListId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getReceiveFrom() {
        return receiveFrom;
    }

    public void setReceiveFrom(String receiveFrom) {
        this.receiveFrom = receiveFrom;
    }

    public Instant getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Instant receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getNumberOfAttachments() {
        return numberOfAttachments;
    }

    public void setNumberOfAttachments(String numberOfAttachments) {
        this.numberOfAttachments = numberOfAttachments;
    }

    public Long getClientEmailListId() {
        return clientEmailListId;
    }

    public void setClientEmailListId(Long clientEmailListId) {
        this.clientEmailListId = clientEmailListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmailProcessingDTO emailProcessingDTO = (EmailProcessingDTO) o;
        if (emailProcessingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emailProcessingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmailProcessingDTO{" +
            "id=" + getId() +
            ", messageId='" + getMessageId() + "'" +
            ", receiveFrom='" + getReceiveFrom() + "'" +
            ", receivedTime='" + getReceivedTime() + "'" +
            ", numberOfAttachments='" + getNumberOfAttachments() + "'" +
            ", clientEmailList=" + getClientEmailListId() +
            "}";
    }
}
