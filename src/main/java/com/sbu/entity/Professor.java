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
@Table(name = "PROFESSOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p"),
    @NamedQuery(name = "Professor.findById", query = "SELECT p FROM Professor p WHERE p.id = :id"),
    @NamedQuery(name = "Professor.findByName", query = "SELECT p FROM Professor p WHERE p.name = :name"),
    @NamedQuery(name = "Professor.findByFamily", query = "SELECT p FROM Professor p WHERE p.family = :family"),
    @NamedQuery(name = "Professor.findByMobile", query = "SELECT p FROM Professor p WHERE p.mobile = :mobile"),
    @NamedQuery(name = "Professor.findByEmail", query = "SELECT p FROM Professor p WHERE p.email = :email"),
    @NamedQuery(name = "Professor.findByPpassword", query = "SELECT p FROM Professor p WHERE p.ppassword = :ppassword"),
    //***********NEW***********************
    @NamedQuery(name = "Professor.findByConce", query = "SELECT p FROM Professor p WHERE p.conceid = :conceid"),
    @NamedQuery(name = "Professor.findByRole", query = "SELECT p FROM Professor p WHERE p.role = :role")})
public class Professor implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FAMILY")
    private String family;
    @Column(name = "MOBILE")
    private Integer mobile;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PPASSWORD")
    private String ppassword;
    @Column(name = "ROLE")
    private Integer role;
    @JoinColumn(name = "CONCEID", referencedColumnName = "ID")
    @ManyToOne
    private Concentration conceid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profid")
    private Collection<ProfCourseSem> profCourseSemCollection;

    public Professor() {
    }

    public Professor(Integer id) {
        this.id = id;
    }

    public Professor(Integer id, String name, String family, String ppassword) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.ppassword = ppassword;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPpassword() {
        return ppassword;
    }

    public void setPpassword(String ppassword) {
        this.ppassword = ppassword;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Concentration getConceid() {
        return conceid;
    }

    public void setConceid(Concentration conceid) {
        this.conceid = conceid;
    }

    @XmlTransient
    public Collection<ProfCourseSem> getProfCourseSemCollection() {
        return profCourseSemCollection;
    }

    public void setProfCourseSemCollection(Collection<ProfCourseSem> profCourseSemCollection) {
        this.profCourseSemCollection = profCourseSemCollection;
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
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbu.entity.Professor[ id=" + id + " ]";
    }
    
}
