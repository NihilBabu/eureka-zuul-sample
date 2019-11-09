package com.nihil.stock.dbservice.model;


import javax.persistence.*;

@Entity
@Table(name = "quote", catalog = "test")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "qotes")
    private String qotes;

    public Quote(String userName, String qotes) {
        this.userName = userName;
        this.qotes = qotes;
    }

    public Quote(String userName) {
        this.userName = userName;
    }

    public Quote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQotes() {
        return qotes;
    }

    public void setQotes(String qotes) {
        this.qotes = qotes;
    }
}


