/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sbu.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "COURSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByUnit", query = "SELECT c FROM Course c WHERE c.unit = :unit"),
    @NamedQuery(name = "Course.findByCtype", query = "SELECT c FROM Course c WHERE c.ctype = :ctype"),
    @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name"),
    //***********NEW********TAKECOURSE***********
    @NamedQuery(name = "Course.findByAllowedConcentration", query = "SELECT c FROM Course c WHERE c.allowedConce like :name"),
    @NamedQuery(name = "Course.findByConcentration", query = "SELECT c FROM Course c WHERE c.conceid = :conceid"),
})
public class Course implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cid")
    private Collection<Copreco> coprecoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "precid")
    private Collection<Copreco> coprecoCollection1;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UNIT")
    private int unit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CTYPE")
    private String ctype;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ALLOWEDCONCE")
    private String allowedConce;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseid")
    private Collection<ProfCourseSem> profCourseSemCollection;
    @JoinColumn(name = "CONCEID", referencedColumnName = "ID")
    @ManyToOne
    private Concentration conceid;
    @JoinColumn(name = "MAJORID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Major majorid;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, int unit, String ctype, String name) {
        this.id = id;
        this.unit = unit;
        this.ctype = ctype;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAllowedConce() {
        return allowedConce.split("&");
    }

    public void setAllowedConce(String allowedConce) {
        this.allowedConce = allowedConce;
    }

    @XmlTransient
    public Collection<ProfCourseSem> getProfCourseSemCollection() {
        return profCourseSemCollection;
    }

    public void setProfCourseSemCollection(Collection<ProfCourseSem> profCourseSemCollection) {
        this.profCourseSemCollection = profCourseSemCollection;
    }

    public Concentration getConceid() {
        return conceid;
    }

    public void setConceid(Concentration conceid) {
        this.conceid = conceid;
    }

    public Major getMajorid() {
        return majorid;
    }

    public void setMajorid(Major majorid) {
        this.majorid = majorid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbu.entity.Course[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Copreco> getCoprecoCollection() {
        return coprecoCollection;
    }

    public void setCoprecoCollection(Collection<Copreco> coprecoCollection) {
        this.coprecoCollection = coprecoCollection;
    }

    @XmlTransient
    public Collection<Copreco> getCoprecoCollection1() {
        return coprecoCollection1;
    }

    public void setCoprecoCollection1(Collection<Copreco> coprecoCollection1) {
        this.coprecoCollection1 = coprecoCollection1;
    }
    
}
