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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "STUDENT_COURSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentCourse.findAll", query = "SELECT s FROM StudentCourse s"),
    @NamedQuery(name = "StudentCourse.findById", query = "SELECT s FROM StudentCourse s WHERE s.id = :id"),
    @NamedQuery(name = "StudentCourse.findByGrade", query = "SELECT s FROM StudentCourse s WHERE s.grade = :grade"),
    @NamedQuery(name = "StudentCourse.findByStudent", query = "SELECT s FROM StudentCourse s WHERE s.stid = :sid"),
    //******NEW***********
    @NamedQuery(name = "StudentCourse.findByStudentAndPCS", query = "SELECT s FROM StudentCourse s WHERE s.stid = :sid and s.pcsid=:pcsid"),
    @NamedQuery(name = "StudentCourse.findByPCS", query = "SELECT s FROM StudentCourse s WHERE s.pcsid=:pcsid"),
    @NamedQuery(name = "StudentCourse.findByConfirmed", query = "SELECT s FROM StudentCourse s WHERE s.confirmed = :confirmed")})
public class StudentCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "GRADE")
    private Float grade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONFIRMED")
    private Boolean confirmed;
    @JoinColumn(name = "PCSID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProfCourseSem pcsid;
    @JoinColumn(name = "STID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Student stid;

    public StudentCourse() {
    }

    public StudentCourse(Integer id) {
        this.id = id;
    }

    public StudentCourse(Integer id, Boolean confirmed) {
        this.id = id;
        this.confirmed = confirmed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public ProfCourseSem getPcsid() {
        return pcsid;
    }

    public void setPcsid(ProfCourseSem pcsid) {
        this.pcsid = pcsid;
    }

    public Student getStid() {
        return stid;
    }

    public void setStid(Student stid) {
        this.stid = stid;
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
        if (!(object instanceof StudentCourse)) {
            return false;
        }
        StudentCourse other = (StudentCourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbu.entity.StudentCourse[ id=" + id + " ]";
    }
    
}
