package com.edatablock.rpa.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FileForOCRProcessing entity.
 */
public class FileForOCRProcessingDTO implements Serializable {

    private Long id;

    private String fileInputType;

    private String status;

    private String retry;

    private Instant createdDateTime;

    private String createdBy;

    private Instant updateTimeStamp;

    private Instant updateBy;

    private Long emailAttachmentId;

    private Long uploadFilesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileInputType() {
        return fileInputType;
    }

    public void setFileInputType(String fileInputType) {
        this.fileInputType = fileInputType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRetry() {
        return retry;
    }

    public void setRetry(String retry) {
        this.retry = retry;
    }

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(Instant updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public Instant getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Instant updateBy) {
        this.updateBy = updateBy;
    }

    public Long getEmailAttachmentId() {
        return emailAttachmentId;
    }

    public void setEmailAttachmentId(Long emailAttachmentId) {
        this.emailAttachmentId = emailAttachmentId;
    }

    public Long getUploadFilesId() {
        return uploadFilesId;
    }

    public void setUploadFilesId(Long uploadFilesId) {
        this.uploadFilesId = uploadFilesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileForOCRProcessingDTO fileForOCRProcessingDTO = (FileForOCRProcessingDTO) o;
        if (fileForOCRProcessingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileForOCRProcessingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileForOCRProcessingDTO{" +
            "id=" + getId() +
            ", fileInputType='" + getFileInputType() + "'" +
            ", status='" + getStatus() + "'" +
            ", retry='" + getRetry() + "'" +
            ", createdDateTime='" + getCreatedDateTime() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", emailAttachment=" + getEmailAttachmentId() +
            ", uploadFiles=" + getUploadFilesId() +
            "}";
    }
}
