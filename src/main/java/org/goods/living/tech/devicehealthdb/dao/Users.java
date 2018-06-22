/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.goods.living.tech.devicehealthdb.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ernestmurimi
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
    , @NamedQuery(name = "Users.findByChvId", query = "SELECT u FROM Users u WHERE u.chvId = :chvId")
    , @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone")
    , @NamedQuery(name = "Users.findByAndroidId", query = "SELECT u FROM Users u WHERE u.androidId = :androidId")
    , @NamedQuery(name = "Users.findByUpdateInterval", query = "SELECT u FROM Users u WHERE u.updateInterval = :updateInterval")
    , @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt")
    , @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt")
    , @NamedQuery(name = "Users.findByVersionCode", query = "SELECT u FROM Users u WHERE u.versionCode = :versionCode")
    , @NamedQuery(name = "Users.findByVersionName", query = "SELECT u FROM Users u WHERE u.versionName = :versionName")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByRecordedAt", query = "SELECT u FROM Users u WHERE u.recordedAt = :recordedAt")
    , @NamedQuery(name = "Users.findByDisableSync", query = "SELECT u FROM Users u WHERE u.disableSync = :disableSync")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "chv_id")
    private String chvId;
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "android_id")
    private String androidId;
    @Basic(optional = false)
    @Column(name = "update_interval")
    private int updateInterval;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "version_code")
    private int versionCode;
    @Column(name = "version_name")
    private String versionName;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "recorded_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedAt;
    @Basic(optional = false)
    @Column(name = "disable_sync")
    private boolean disableSync;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Stats> statsCollection;

    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    public Users(Long id, String androidId, int updateInterval, Date createdAt, int versionCode, String username, boolean disableSync) {
        this.id = id;
        this.androidId = androidId;
        this.updateInterval = updateInterval;
        this.createdAt = createdAt;
        this.versionCode = versionCode;
        this.username = username;
        this.disableSync = disableSync;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChvId() {
        return chvId;
    }

    public void setChvId(String chvId) {
        this.chvId = chvId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
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

    public Date getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Date recordedAt) {
        this.recordedAt = recordedAt;
    }

    public boolean getDisableSync() {
        return disableSync;
    }

    public void setDisableSync(boolean disableSync) {
        this.disableSync = disableSync;
    }

    @XmlTransient
    public Collection<Stats> getStatsCollection() {
        return statsCollection;
    }

    public void setStatsCollection(Collection<Stats> statsCollection) {
        this.statsCollection = statsCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.goods.living.tech.devicehealthdb.Users[ id=" + id + " ]";
    }
    
}
