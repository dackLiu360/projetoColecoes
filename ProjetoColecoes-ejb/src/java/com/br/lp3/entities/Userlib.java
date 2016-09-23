/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 31437974
 */
@Entity
@Table(name = "USERLIB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userlib.findAll", query = "SELECT u FROM Userlib u"),
    @NamedQuery(name = "Userlib.findByIdUserlib", query = "SELECT u FROM Userlib u WHERE u.idUserlib = :idUserlib"),
    @NamedQuery(name = "Userlib.findByUsername", query = "SELECT u FROM Userlib u WHERE u.username = :username"),
    @NamedQuery(name = "Userlib.findByPassword", query = "SELECT u FROM Userlib u WHERE u.password = :password")})
public class Userlib implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USERLIB")
    private Long idUserlib;
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 32)
    @Column(name = "PASSWORD")
    private String password;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userlib")
    private Userlibinfo userlibinfo;

    public Userlib() {
    }

    public Userlib(Long idUserlib) {
        this.idUserlib = idUserlib;
    }

    public Long getIdUserlib() {
        return idUserlib;
    }

    public void setIdUserlib(Long idUserlib) {
        this.idUserlib = idUserlib;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Userlibinfo getUserlibinfo() {
        return userlibinfo;
    }

    public void setUserlibinfo(Userlibinfo userlibinfo) {
        this.userlibinfo = userlibinfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserlib != null ? idUserlib.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userlib)) {
            return false;
        }
        Userlib other = (Userlib) object;
        if ((this.idUserlib == null && other.idUserlib != null) || (this.idUserlib != null && !this.idUserlib.equals(other.idUserlib))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.entities.Userlib[ idUserlib=" + idUserlib + " ]";
    }
    
}
