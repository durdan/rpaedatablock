package com.edatablock.rpa.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OrganizationsDetails.
 */
@Entity
@Table(name = "organizations_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrganizationsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "description")
    private String description;

    @Column(name = "organisation_address")
    private String organisationAddress;

    @Column(name = "organisation_email")
    private String organisationEmail;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "email_server_host")
    private String emailServerHost;

    @Column(name = "email_server_port")
    private Integer emailServerPort;

    @Column(name = "email_server_user_id")
    private String emailServerUserId;

    @Column(name = "email_server_password")
    private String emailServerPassword;

    @Column(name = "is_email_server_access_allowed")
    private Integer isEmailServerAccessAllowed;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_date")
    private Instant updateDate;

    @Column(name = "updated_by")
    private String updatedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public OrganizationsDetails organizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDescription() {
        return description;
    }

    public OrganizationsDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganisationAddress() {
        return organisationAddress;
    }

    public OrganizationsDetails organisationAddress(String organisationAddress) {
        this.organisationAddress = organisationAddress;
        return this;
    }

    public void setOrganisationAddress(String organisationAddress) {
        this.organisationAddress = organisationAddress;
    }

    public String getOrganisationEmail() {
        return organisationEmail;
    }

    public OrganizationsDetails organisationEmail(String organisationEmail) {
        this.organisationEmail = organisationEmail;
        return this;
    }

    public void setOrganisationEmail(String organisationEmail) {
        this.organisationEmail = organisationEmail;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public OrganizationsDetails isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getEmailServerHost() {
        return emailServerHost;
    }

    public OrganizationsDetails emailServerHost(String emailServerHost) {
        this.emailServerHost = emailServerHost;
        return this;
    }

    public void setEmailServerHost(String emailServerHost) {
        this.emailServerHost = emailServerHost;
    }

    public Integer getEmailServerPort() {
        return emailServerPort;
    }

    public OrganizationsDetails emailServerPort(Integer emailServerPort) {
        this.emailServerPort = emailServerPort;
        return this;
    }

    public void setEmailServerPort(Integer emailServerPort) {
        this.emailServerPort = emailServerPort;
    }

    public String getEmailServerUserId() {
        return emailServerUserId;
    }

    public OrganizationsDetails emailServerUserId(String emailServerUserId) {
        this.emailServerUserId = emailServerUserId;
        return this;
    }

    public void setEmailServerUserId(String emailServerUserId) {
        this.emailServerUserId = emailServerUserId;
    }

    public String getEmailServerPassword() {
        return emailServerPassword;
    }

    public OrganizationsDetails emailServerPassword(String emailServerPassword) {
        this.emailServerPassword = emailServerPassword;
        return this;
    }

    public void setEmailServerPassword(String emailServerPassword) {
        this.emailServerPassword = emailServerPassword;
    }

    public Integer getIsEmailServerAccessAllowed() {
        return isEmailServerAccessAllowed;
    }

    public OrganizationsDetails isEmailServerAccessAllowed(Integer isEmailServerAccessAllowed) {
        this.isEmailServerAccessAllowed = isEmailServerAccessAllowed;
        return this;
    }

    public void setIsEmailServerAccessAllowed(Integer isEmailServerAccessAllowed) {
        this.isEmailServerAccessAllowed = isEmailServerAccessAllowed;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public OrganizationsDetails createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OrganizationsDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public OrganizationsDetails updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public OrganizationsDetails updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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
        OrganizationsDetails organizationsDetails = (OrganizationsDetails) o;
        if (organizationsDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organizationsDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganizationsDetails{" +
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
