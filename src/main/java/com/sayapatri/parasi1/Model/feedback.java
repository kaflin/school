package com.sayapatri.parasi1.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString
public class feedback {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String email;


    private String message;


    private String phone;




    public feedback()
    {

    }
    public feedback(String email,String message,String phone) {
        this.email=email;
        this.message=message;
        this.phone=phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
