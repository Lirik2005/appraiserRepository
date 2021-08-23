package com.example.appraiserbase.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {


    @Id
    private String pid;

    @Column
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<Appraiser> appraisers = new ArrayList<>();

    public Role() {
    }

    public Role(final String name, final String description) {
        this.pid = name;
        this.description = description;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Appraiser> getAppraisers() {
        return appraisers;
    }

    public void setAppraisers(List<Appraiser> appraisers) {
        this.appraisers = appraisers;
    }

    @Transient
    public String getName() {
        return pid;
    }

    @Transient
    public String getAuthority() {
        return pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return pid.equals(role.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }
}
