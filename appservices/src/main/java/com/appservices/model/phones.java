package com.appservices.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="phones", schema="public")
public class phones implements Serializable {


    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "idphone", unique = true, nullable = false)
    private Integer idphone;
    private String number;
    private String  citycode;
    private String  contrycode;
    @ManyToOne
    private user user;
    
    
	public Integer getIdphone() {
		return idphone;
	}
	public void setIdphone(Integer idphone) {
		this.idphone = idphone;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getContrycode() {
		return contrycode;
	}
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	 
}