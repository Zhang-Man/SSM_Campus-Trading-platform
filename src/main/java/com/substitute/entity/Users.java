package com.substitute.entity;

public class Users {
    private Integer id;

    private Integer account;

    private String username;

    private String password;

    private String profileimg;

    private String gender;

    private String email;

    private String tel;

    private String wechat;

    private String qq;

    private Integer usertype;

    private Integer status;

    private Integer creditpoints;
    
    public void setUandP(String username,String password) 
    {
    	this.username = username;
    	this.password = password;
    }
    
    public void setUandPandTandE(String username,String password,String tel,String email) 
    {
    	this.username = username;
    	this.password = password;
    	this.tel = tel;
    	this.email = email;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg == null ? null : profileimg.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreditpoints() {
        return creditpoints;
    }

    public void setCreditpoints(Integer creditpoints) {
        this.creditpoints = creditpoints;
    }
}