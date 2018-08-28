package com.edatablock.rpa.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EmailAttachment entity.
 */
public class EmailAttachmentDTO implements Serializable {

    private Long id;

    private String fileName;

    private String fileExtension;

    private String fileLocation;

    private Long emailProcessingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Long getEmailProcessingId() {
        return emailProcessingId;
    }

    public void setEmailProcessingId(Long emailProcessingId) {
        this.emailProcessingId = emailProcessingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmailAttachmentDTO emailAttachmentDTO = (EmailAttachmentDTO) o;
        if (emailAttachmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emailAttachmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmailAttachmentDTO{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", fileExtension='" + getFileExtension() + "'" +
            ", fileLocation='" + getFileLocation() + "'" +
            ", emailProcessing=" + getEmailProcessingId() +
            "}";
    }
}
