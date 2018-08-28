package com.edatablock.rpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A EmailProcessing.
 */
@Entity
@Table(name = "email_processing")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmailProcessing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "receive_from")
    private String receiveFrom;

    @Column(name = "received_time")
    private Instant receivedTime;

    @Column(name = "number_of_attachments")
    private String numberOfAttachments;

    @OneToOne
    @JoinColumn(unique = true)
    private ClientEmailList clientEmailList;

    @OneToOne(mappedBy = "emailProcessing")
    @JsonIgnore
    private EmailProcessingError emailProcessingError;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public EmailProcessing messageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getReceiveFrom() {
        return receiveFrom;
    }

    public EmailProcessing receiveFrom(String receiveFrom) {
        this.receiveFrom = receiveFrom;
        return this;
    }

    public void setReceiveFrom(String receiveFrom) {
        this.receiveFrom = receiveFrom;
    }

    public Instant getReceivedTime() {
        return receivedTime;
    }

    public EmailProcessing receivedTime(Instant receivedTime) {
        this.receivedTime = receivedTime;
        return this;
    }

    public void setReceivedTime(Instant receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getNumberOfAttachments() {
        return numberOfAttachments;
    }

    public EmailProcessing numberOfAttachments(String numberOfAttachments) {
        this.numberOfAttachments = numberOfAttachments;
        return this;
    }

    public void setNumberOfAttachments(String numberOfAttachments) {
        this.numberOfAttachments = numberOfAttachments;
    }

    public ClientEmailList getClientEmailList() {
        return clientEmailList;
    }

    public EmailProcessing clientEmailList(ClientEmailList clientEmailList) {
        this.clientEmailList = clientEmailList;
        return this;
    }

    public void setClientEmailList(ClientEmailList clientEmailList) {
        this.clientEmailList = clientEmailList;
    }

    public EmailProcessingError getEmailProcessingError() {
        return emailProcessingError;
    }

    public EmailProcessing emailProcessingError(EmailProcessingError emailProcessingError) {
        this.emailProcessingError = emailProcessingError;
        return this;
    }

    public void setEmailProcessingError(EmailProcessingError emailProcessingError) {
        this.emailProcessingError = emailProcessingError;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmailProcessing emailProcessing = (EmailProcessing) o;
        if (emailProcessing.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emailProcessing.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmailProcessing{" +
            "id=" + getId() +
            ", messageId='" + getMessageId() + "'" +
            ", receiveFrom='" + getReceiveFrom() + "'" +
            ", receivedTime='" + getReceivedTime() + "'" +
            ", numberOfAttachments='" + getNumberOfAttachments() + "'" +
            "}";
    }
}
