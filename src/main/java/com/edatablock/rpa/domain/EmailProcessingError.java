package com.edatablock.rpa.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A EmailProcessingError.
 */
@Entity
@Table(name = "email_processing_error")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmailProcessingError implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "message_id")
    private String messageID;

    @Column(name = "receive_from")
    private String receiveFrom;

    @Column(name = "received_time")
    private Instant receivedTime;

    @OneToOne
    @JoinColumn(unique = true)
    private EmailProcessing emailProcessing;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public EmailProcessingError errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessageID() {
        return messageID;
    }

    public EmailProcessingError messageID(String messageID) {
        this.messageID = messageID;
        return this;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getReceiveFrom() {
        return receiveFrom;
    }

    public EmailProcessingError receiveFrom(String receiveFrom) {
        this.receiveFrom = receiveFrom;
        return this;
    }

    public void setReceiveFrom(String receiveFrom) {
        this.receiveFrom = receiveFrom;
    }

    public Instant getReceivedTime() {
        return receivedTime;
    }

    public EmailProcessingError receivedTime(Instant receivedTime) {
        this.receivedTime = receivedTime;
        return this;
    }

    public void setReceivedTime(Instant receivedTime) {
        this.receivedTime = receivedTime;
    }

    public EmailProcessing getEmailProcessing() {
        return emailProcessing;
    }

    public EmailProcessingError emailProcessing(EmailProcessing emailProcessing) {
        this.emailProcessing = emailProcessing;
        return this;
    }

    public void setEmailProcessing(EmailProcessing emailProcessing) {
        this.emailProcessing = emailProcessing;
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
        EmailProcessingError emailProcessingError = (EmailProcessingError) o;
        if (emailProcessingError.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emailProcessingError.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmailProcessingError{" +
            "id=" + getId() +
            ", errorMessage='" + getErrorMessage() + "'" +
            ", messageID='" + getMessageID() + "'" +
            ", receiveFrom='" + getReceiveFrom() + "'" +
            ", receivedTime='" + getReceivedTime() + "'" +
            "}";
    }
}
