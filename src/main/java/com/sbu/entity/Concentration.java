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
@Table(name = "CONCENTRATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concentration.findAll", query = "SELECT c FROM Concentration c"),
    @NamedQuery(name = "Concentration.findById", query = "SELECT c FROM Concentration c WHERE c.id = :id"),
    //**********************NEW************************************
    @NamedQuery(name = "Concentration.findByMajor", query = "SELECT c FROM Concentration c WHERE c.majorid = :mid"),
    @NamedQuery(name = "Concentration.findByName", query = "SELECT c FROM Concentration c WHERE c.name = :name")})
public class Concentration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concenid")
    private Collection<Student> studentCollection;
    @JoinColumn(name = "MAJORID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Major majorid;
    @OneToMany(mappedBy = "conceid")
    private Collection<Professor> professorCollection;
    @OneToMany(mappedBy = "conceid")
    private Collection<Course> courseCollection;

    public Concentration() {
    }

    public Concentration(Integer id) {
        this.id = id;
    }

    public Concentration(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    public Major getMajorid() {
        return majorid;
    }

    public void setMajorid(Major majorid) {
        this.majorid = majorid;
    }

    @XmlTransient
    public Collection<Professor> getProfessorCollection() {
        return professorCollection;
    }

    public void setProfessorCollection(Collection<Professor> professorCollection) {
        this.professorCollection = professorCollection;
    }

    @XmlTransient
    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
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
        if (!(object instanceof Concentration)) {
            return false;
        }
        Concentration other = (Concentration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbu.entity.Concentration[ id=" + id + " ]";
    }
    
}
