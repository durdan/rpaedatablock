package com.edatablock.rpa.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrganizationsDetails entity.
 */
public class OrganizationsDetailsDTO implements Serializable {

    private Long id;

    private String organizationName;

    private String description;

    private String organisationAddress;

    private String organisationEmail;

    private Integer isActive;

    private String emailServerHost;

    private Integer emailServerPort;

    private String emailServerUserId;

    private String emailServerPassword;

    private Integer isEmailServerAccessAllowed;

    private Instant createDate;

    private String createdBy;

    private Instant updateDate;

    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganisationAddress() {
        return organisationAddress;
    }

    public void setOrganisationAddress(String organisationAddress) {
        this.organisationAddress = organisationAddress;
    }

    public String getOrganisationEmail() {
        return organisationEmail;
    }

    public void setOrganisationEmail(String organisationEmail) {
        this.organisationEmail = organisationEmail;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getEmailServerHost() {
        return emailServerHost;
    }

    public void setEmailServerHost(String emailServerHost) {
        this.emailServerHost = emailServerHost;
    }

    public Integer getEmailServerPort() {
        return emailServerPort;
    }

    public void setEmailServerPort(Integer emailServerPort) {
        this.emailServerPort = emailServerPort;
    }

    public String getEmailServerUserId() {
        return emailServerUserId;
    }

    public void setEmailServerUserId(String emailServerUserId) {
        this.emailServerUserId = emailServerUserId;
    }

    public String getEmailServerPassword() {
        return emailServerPassword;
    }

    public void setEmailServerPassword(String emailServerPassword) {
        this.emailServerPassword = emailServerPassword;
    }

    public Integer getIsEmailServerAccessAllowed() {
        return isEmailServerAccessAllowed;
    }

    public void setIsEmailServerAccessAllowed(Integer isEmailServerAccessAllowed) {
        this.isEmailServerAccessAllowed = isEmailServerAccessAllowed;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganizationsDetailsDTO organizationsDetailsDTO = (OrganizationsDetailsDTO) o;
        if (organizationsDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organizationsDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganizationsDetailsDTO{" +
            "id=" + getId() +
            ", organizationName='" + getOrganizationName() + "'" +
            ", description='" + getDescription() + "'" +
            ", organisationAddress='" + getOrganisationAddress() + "'" +
            ", organisationEmail='" + getOrganisationEmail() + "'" +
            ", isActive=" + getIsActive() +
            ", emailServerHost='" + getEmailServerHost() + "'" +
            ", emailServerPort=" + getEmailServerPort() +
            ", emailServerUserId='" + getEmailServerUserId() + "'" +
            ", emailServerPassword='" + getEmailServerPassword() + "'" +
            ", isEmailServerAccessAllowed=" + getIsEmailServerAccessAllowed() +
            ", createDate='" + getCreateDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
