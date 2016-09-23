/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 31437974
 */
@Entity
@Table(name = "USERLIBINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userlibinfo.findAll", query = "SELECT u FROM Userlibinfo u"),
    @NamedQuery(name = "Userlibinfo.findByIdUserlibinfo", query = "SELECT u FROM Userlibinfo u WHERE u.idUserlibinfo = :idUserlibinfo"),
    @NamedQuery(name = "Userlibinfo.findByFirstname", query = "SELECT u FROM Userlibinfo u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Userlibinfo.findByLastname", query = "SELECT u FROM Userlibinfo u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Userlibinfo.findByEmail", query = "SELECT u FROM Userlibinfo u WHERE u.email = :email"),
    @NamedQuery(name = "Userlibinfo.findByBirthday", query = "SELECT u FROM Userlibinfo u WHERE u.birthday = :birthday")})
public class Userlibinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USERLIBINFO")
    private Long idUserlibinfo;
    @Size(max = 50)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 50)
    @Column(name = "LASTNAME")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Lob
    @Column(name = "PICTURE")
    private Serializable picture;
    @JoinColumn(name = "ID_USERLIBINFO", referencedColumnName = "ID_USERLIB", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Userlib userlib;

    public Userlibinfo() {
    }

    public Userlibinfo(Long idUserlibinfo) {
        this.idUserlibinfo = idUserlibinfo;
    }

    public Long getIdUserlibinfo() {
        return idUserlibinfo;
    }

    public void setIdUserlibinfo(Long idUserlibinfo) {
        this.idUserlibinfo = idUserlibinfo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Serializable getPicture() {
        return picture;
    }

    public void setPicture(Serializable picture) {
        this.picture = picture;
    }

    public Userlib getUserlib() {
        return userlib;
    }

    public void setUserlib(Userlib userlib) {
        this.userlib = userlib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserlibinfo != null ? idUserlibinfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userlibinfo)) {
            return false;
        }
        Userlibinfo other = (Userlibinfo) object;
        if ((this.idUserlibinfo == null && other.idUserlibinfo != null) || (this.idUserlibinfo != null && !this.idUserlibinfo.equals(other.idUserlibinfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.entities.Userlibinfo[ idUserlibinfo=" + idUserlibinfo + " ]";
    }
    
}
