package com.edatablock.rpa.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TemplateFields entity.
 */
public class TemplateFieldsDTO implements Serializable {

    private Long id;

    private String fieldName;

    private Double fieldZoneMinX;

    private Double fieldZoneMinY;

    private Double fieldZoneMaxX;

    private Double fieldZoneMaxY;

    private Integer fieldValidationRequire;

    private String fieldValidationRule;

    private Integer fieldLocation;

    private Long templateDetailsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Double getFieldZoneMinX() {
        return fieldZoneMinX;
    }

    public void setFieldZoneMinX(Double fieldZoneMinX) {
        this.fieldZoneMinX = fieldZoneMinX;
    }

    public Double getFieldZoneMinY() {
        return fieldZoneMinY;
    }

    public void setFieldZoneMinY(Double fieldZoneMinY) {
        this.fieldZoneMinY = fieldZoneMinY;
    }

    public Double getFieldZoneMaxX() {
        return fieldZoneMaxX;
    }

    public void setFieldZoneMaxX(Double fieldZoneMaxX) {
        this.fieldZoneMaxX = fieldZoneMaxX;
    }

    public Double getFieldZoneMaxY() {
        return fieldZoneMaxY;
    }

    public void setFieldZoneMaxY(Double fieldZoneMaxY) {
        this.fieldZoneMaxY = fieldZoneMaxY;
    }

    public Integer getFieldValidationRequire() {
        return fieldValidationRequire;
    }

    public void setFieldValidationRequire(Integer fieldValidationRequire) {
        this.fieldValidationRequire = fieldValidationRequire;
    }

    public String getFieldValidationRule() {
        return fieldValidationRule;
    }

    public void setFieldValidationRule(String fieldValidationRule) {
        this.fieldValidationRule = fieldValidationRule;
    }

    public Integer getFieldLocation() {
        return fieldLocation;
    }

    public void setFieldLocation(Integer fieldLocation) {
        this.fieldLocation = fieldLocation;
    }

    public Long getTemplateDetailsId() {
        return templateDetailsId;
    }

    public void setTemplateDetailsId(Long templateDetailsId) {
        this.templateDetailsId = templateDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TemplateFieldsDTO templateFieldsDTO = (TemplateFieldsDTO) o;
        if (templateFieldsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), templateFieldsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TemplateFieldsDTO{" +
            "id=" + getId() +
            ", fieldName='" + getFieldName() + "'" +
            ", fieldZoneMinX=" + getFieldZoneMinX() +
            ", fieldZoneMinY=" + getFieldZoneMinY() +
            ", fieldZoneMaxX=" + getFieldZoneMaxX() +
            ", fieldZoneMaxY=" + getFieldZoneMaxY() +
            ", fieldValidationRequire=" + getFieldValidationRequire() +
            ", fieldValidationRule='" + getFieldValidationRule() + "'" +
            ", fieldLocation=" + getFieldLocation() +
            ", templateDetails=" + getTemplateDetailsId() +
            "}";
    }
}
