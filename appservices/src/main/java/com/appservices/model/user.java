package com.appservices.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;





@Entity
@Table(name = "USER", catalog = "", schema = "PUBLIC")
public class user {
	
	    private static final long serialVersionUUID = 1L;

	    @Id
	    @Column(columnDefinition = "BINARY(16)")
	    private UUID id;
	      
	    @Column(name = "NAME")
	    private String name;
	    @Column(name = "EMAIL")
	    private String email;
	    @Column(name = "PASSWORD")
	    private String password;
	    @Column(name = "CREATED")
	    private Date created;
	    @Column(name = "MODIFIED")
	    private Date modified;
	    @Column(name = "LAST_LOGIN")
	    private Date last_login;
	    @Column(name = "TOKEN")
	    private String token;
	    @Column(name = "ISACTIVE")
	    private int isactive;
	    @OneToMany(mappedBy="user", cascade = CascadeType.ALL,orphanRemoval = true)	   
	    private List<phones> phones;
	    
	       
	    public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public Date getCreated() {
			return created;
		}
		
		public void setCreated(Date created) {
			this.created = created;
		}
		
		public Date getModified() {
			return modified;
		}
		
		public void setModified(Date modified) {
			this.modified = modified;
		}
		
		public Date getLast_login() {
			return last_login;
		}
		
		public void setLast_login(Date last_login) {
			this.last_login = last_login;
		}
		
		public String getToken() {
			return token;
		}
		
		public void setToken(String token) {
			this.token = token;
		}
		
		public int getIsactive() {
			return isactive;
		}
		
		public void setIsactive(int isactive) {
			this.isactive = isactive;
		}

		public List<phones> getPhones() {
			return phones;
		}

		public void setPhones(List<phones> phones) {
			this.phones = phones;
		}

}
