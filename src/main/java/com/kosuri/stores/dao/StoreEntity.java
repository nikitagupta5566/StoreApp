package com.kosuri.stores.dao;
//
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "store_info")
public class StoreEntity {
    @NotNull
    private @Column(name = "Store_Category") String type;
    @Id
    @NotNull
    private @Column(name = "Store_ID") String id;
    @NotNull
    private @Column(name = "Store_Name") String name;
    @NotNull
    private @Column(name = "Pincode") String pincode;
    @NotNull
    private @Column(name = "Village") String district;
    @NotNull
    private @Column(name = "State") String state;
    @NotNull
    private @Column(name = "Location") String location;
    @NotNull
    private @Column(name = "Contact_Person_Name") String owner;
    @NotNull
    private @Column(name = "Phone_Number") String ownerContact;
    @NotNull
    private @Column(name = "Secondary_Phone") String secondaryContact;
    @NotNull
    private @Column(name = "Emailid") String ownerEmail;
    @NotNull
    private @Column(name = "Reg_Date") String registrationDate;
    @NotNull
    private @Column(name = "Time_Stamp") String creationTimeStamp;
    @NotNull
    private @Column(name = "Role") String role;
    @NotNull
    private @Column(name = "Added_By") String addedBy;
    @NotNull
    private @Column(name = "Modified_By") String modifiedBy;
    @NotNull
    private @Column(name = "Modified_Date") String modifiedDate;
    @NotNull
    private @Column(name = "Modified_Time_Stamp") String modifiedTimeStamp;
    @NotNull
    private @Column(name = "Status") String status;
    @NotNull
    private @Column(name = "Password") String password;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getSecondaryContact() {
        return secondaryContact;
    }

    public void setSecondaryContact(String secondaryContact) {
        this.secondaryContact = secondaryContact;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(String creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedTimeStamp() {
        return modifiedTimeStamp;
    }

    public void setModifiedTimeStamp(String modifiedTimeStamp) {
        this.modifiedTimeStamp = modifiedTimeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
