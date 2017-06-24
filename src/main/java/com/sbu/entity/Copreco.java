/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sbu.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "COPRECO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Copreco.findAll", query = "SELECT c FROM Copreco c"),
    @NamedQuery(name = "Copreco.findById", query = "SELECT c FROM Copreco c WHERE c.id = :id"),
    //***************NEW************************
    @NamedQuery(name = "Copreco.removeByCourse", query = "DELETE FROM Copreco c WHERE c.cid = :cid"),
    @NamedQuery(name = "Copreco.findByCourse", query = "SELECT c.precid FROM Copreco c WHERE c.cid = :cid")
})
public class Copreco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Course cid;
    @JoinColumn(name = "PRECID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Course precid;

    public Copreco() {
    }

    public Copreco(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCid() {
        return cid;
    }

    public void setCid(Course cid) {
        this.cid = cid;
    }

    public Course getPrecid() {
        return precid;
    }

    public void setPrecid(Course precid) {
        this.precid = precid;
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
        if (!(object instanceof Copreco)) {
            return false;
        }
        Copreco other = (Copreco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbu.entity.Copreco[ id=" + id + " ]";
    }
    
}
