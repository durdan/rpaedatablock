package com.edatablock.rpa.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OcrProcessingError entity.
 */
public class OcrProcessingErrorDTO implements Serializable {

    private Long id;

    private String errorMessage;

    private Instant createdDateTime;

    private String errorType;

    private Long transactionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OcrProcessingErrorDTO ocrProcessingErrorDTO = (OcrProcessingErrorDTO) o;
        if (ocrProcessingErrorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ocrProcessingErrorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OcrProcessingErrorDTO{" +
            "id=" + getId() +
            ", errorMessage='" + getErrorMessage() + "'" +
            ", createdDateTime='" + getCreatedDateTime() + "'" +
            ", errorType='" + getErrorType() + "'" +
            ", transaction=" + getTransactionId() +
            "}";
    }
}
