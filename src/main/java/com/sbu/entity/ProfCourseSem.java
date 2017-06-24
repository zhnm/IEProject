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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "PROF_COURSE_SEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfCourseSem.findAll", query = "SELECT p FROM ProfCourseSem p"),
    @NamedQuery(name = "ProfCourseSem.findById", query = "SELECT p FROM ProfCourseSem p WHERE p.id = :id"),
    @NamedQuery(name = "ProfCourseSem.findBySemester", query = "SELECT p FROM ProfCourseSem p WHERE p.semid = :sid"),
    //*************NEW*****VIEW STUDENT LIST***********
    @NamedQuery(name = "ProfCourseSem.findByProfessor", query = "SELECT p FROM ProfCourseSem p WHERE p.profid = :pid "),
    @NamedQuery(name = "ProfCourseSem.findByProfessorAndSemester", query = "SELECT p FROM ProfCourseSem p WHERE p.profid = :pid and p.semid=:sid"),
    @NamedQuery(name = "ProfCourseSem.findByCourseAndSemester", query = "SELECT p FROM ProfCourseSem p WHERE p.courseid = :cid and p.semid= :sid"),
    @NamedQuery(name = "ProfCourseSem.findByPtime", query = "SELECT p FROM ProfCourseSem p WHERE p.ptime = :ptime")})
public class ProfCourseSem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 200)
    @Column(name = "PTIME")
    private String ptime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pcsid")
    private Collection<StudentCourse> studentCourseCollection;
    @JoinColumn(name = "COURSEID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Course courseid;
    @JoinColumn(name = "PROFID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Professor profid;
    @JoinColumn(name = "SEMID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Semester semid;

    public ProfCourseSem() {
    }

    public ProfCourseSem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    @XmlTransient
    public Collection<StudentCourse> getStudentCourseCollection() {
        return studentCourseCollection;
    }

    public void setStudentCourseCollection(Collection<StudentCourse> studentCourseCollection) {
        this.studentCourseCollection = studentCourseCollection;
    }

    public Course getCourseid() {
        return courseid;
    }

    public void setCourseid(Course courseid) {
        this.courseid = courseid;
    }

    public Professor getProfid() {
        return profid;
    }

    public void setProfid(Professor profid) {
        this.profid = profid;
    }

    public Semester getSemid() {
        return semid;
    }

    public void setSemid(Semester semid) {
        this.semid = semid;
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
        if (!(object instanceof ProfCourseSem)) {
            return false;
        }
        ProfCourseSem other = (ProfCourseSem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbu.entity.ProfCourseSem[ id=" + id + " ]";
    }
    
}
