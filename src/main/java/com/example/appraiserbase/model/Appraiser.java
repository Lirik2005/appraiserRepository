package com.example.appraiserbase.model;

import com.example.appraiserbase.model.conclusions.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "appraisers")
public class Appraiser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false, length = 100, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(length = 100)
    private String middleName;

    @Column(length = 100)
    private String placeOfWork;

    @Column(nullable = false, length = 100)
    private String phoneNumber;

    @Column(unique = true)
    private Integer businessCertificate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate businessCertificateValidity;

    @Column(unique = true)
    private Integer landCertificate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate landCertificateValidity;

    @Column(unique = true)
    private Integer propertyCertificate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate propertyCertificateValidity;

    @Column(unique = true)
    private Integer equipmentCertificate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate equipmentCertificateValidity;

    @Column(unique = true)
    private Integer intellectCertificate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate intellectCertificateValidity;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "appraisers_roles")
    private List<Role> roles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20) default 'ACTIVE'")
    private Status status;

    @OneToMany(mappedBy = "appraiser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<BusinessConclusion> businessConclusions = new ArrayList<>();

    @OneToMany(mappedBy = "appraiser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<LandConclusion> landConclusions = new ArrayList<>();

    @OneToMany(mappedBy = "appraiser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<PropertyConclusion> propertyConclusions = new ArrayList<>();

    @OneToMany(mappedBy = "appraiser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<EquipmentConclusion> equipmentConclusions = new ArrayList<>();

    @OneToMany(mappedBy = "appraiser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TransportConclusion> transportConclusions = new ArrayList<>();

    @OneToMany(mappedBy = "appraiser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<IntellectConclusions> intellectConclusions = new ArrayList<>();

    @Transient
    public String lastNameAndInitials() {
        String fio = null;
        if ((middleName != null) && (!middleName.isEmpty())) {
            fio = String.format("%s %s. %s.", lastName, firstName.charAt(0), middleName.charAt(0));
        } else {
            fio = String.format("%s %s.", lastName, firstName.charAt(0));
        }
        return fio;
    }

    @Override
    public String toString() {
        return lastNameAndInitials();
    }


    @Transient
    public String getRolesStr() {
        return roles.stream().map(Role::getName).collect(Collectors.joining(","));
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public Integer getBusinessCertificate() {
        return businessCertificate;
    }

    public void setBusinessCertificate(Integer businessCertificate) {
        this.businessCertificate = businessCertificate;
    }

    public LocalDate getBusinessCertificateValidity() {
        return businessCertificateValidity;
    }

    public void setBusinessCertificateValidity(LocalDate businessCertificateValidity) {
        this.businessCertificateValidity = businessCertificateValidity;
    }

    public Integer getLandCertificate() {
        return landCertificate;
    }

    public void setLandCertificate(Integer landCertificate) {
        this.landCertificate = landCertificate;
    }

    public LocalDate getLandCertificateValidity() {
        return landCertificateValidity;
    }

    public void setLandCertificateValidity(LocalDate landCertificateValidity) {
        this.landCertificateValidity = landCertificateValidity;
    }

    public Integer getPropertyCertificate() {
        return propertyCertificate;
    }

    public void setPropertyCertificate(Integer propertyCertificate) {
        this.propertyCertificate = propertyCertificate;
    }

    public LocalDate getPropertyCertificateValidity() {
        return propertyCertificateValidity;
    }

    public void setPropertyCertificateValidity(LocalDate propertyCertificateValidity) {
        this.propertyCertificateValidity = propertyCertificateValidity;
    }

    public Integer getEquipmentCertificate() {
        return equipmentCertificate;
    }

    public void setEquipmentCertificate(Integer equipmentCertificate) {
        this.equipmentCertificate = equipmentCertificate;
    }

    public LocalDate getEquipmentCertificateValidity() {
        return equipmentCertificateValidity;
    }

    public void setEquipmentCertificateValidity(LocalDate equipmentCertificateValidity) {
        this.equipmentCertificateValidity = equipmentCertificateValidity;
    }

    public Integer getIntellectCertificate() {
        return intellectCertificate;
    }

    public void setIntellectCertificate(Integer intellectCertificate) {
        this.intellectCertificate = intellectCertificate;
    }

    public LocalDate getIntellectCertificateValidity() {
        return intellectCertificateValidity;
    }

    public void setIntellectCertificateValidity(LocalDate intellectCertificateValidity) {
        this.intellectCertificateValidity = intellectCertificateValidity;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<BusinessConclusion> getBusinessConclusions() {
        return businessConclusions;
    }

    public void setBusinessConclusions(List<BusinessConclusion> businessConclusions) {
        this.businessConclusions = businessConclusions;
    }

    public List<LandConclusion> getLandConclusions() {
        return landConclusions;
    }

    public void setLandConclusions(List<LandConclusion> landConclusions) {
        this.landConclusions = landConclusions;
    }

    public List<PropertyConclusion> getPropertyConclusions() {
        return propertyConclusions;
    }

    public void setPropertyConclusions(List<PropertyConclusion> propertyConclusions) {
        this.propertyConclusions = propertyConclusions;
    }

    public List<EquipmentConclusion> getEquipmentConclusions() {
        return equipmentConclusions;
    }

    public void setEquipmentConclusions(List<EquipmentConclusion> equipmentConclusions) {
        this.equipmentConclusions = equipmentConclusions;
    }

    public List<TransportConclusion> getTransportConclusions() {
        return transportConclusions;
    }

    public void setTransportConclusions(List<TransportConclusion> transportConclusions) {
        this.transportConclusions = transportConclusions;
    }

    public List<IntellectConclusions> getIntellectConclusions() {
        return intellectConclusions;
    }

    public void setIntellectConclusions(List<IntellectConclusions> intellectConclusions) {
        this.intellectConclusions = intellectConclusions;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appraiser appraiser = (Appraiser) o;
        return pid.equals(appraiser.pid) && login.equals(appraiser.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, login);
    }



}
