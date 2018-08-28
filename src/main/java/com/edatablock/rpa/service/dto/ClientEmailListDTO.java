package com.edatablock.rpa.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ClientEmailList entity.
 */
public class ClientEmailListDTO implements Serializable {

    private Long id;

    private String emailAddress;

    private String description;

    private Integer isActive;

    private Long clientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientEmailListDTO clientEmailListDTO = (ClientEmailListDTO) o;
        if (clientEmailListDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clientEmailListDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClientEmailListDTO{" +
            "id=" + getId() +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", description='" + getDescription() + "'" +
            ", isActive=" + getIsActive() +
            ", client=" + getClientId() +
            "}";
    }
}
