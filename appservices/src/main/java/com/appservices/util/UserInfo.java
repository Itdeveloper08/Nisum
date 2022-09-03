package com.appservices.util;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;

public class UserInfo {
	
	
	    private UUID id;
	    private String user;
	    private Date created;
	    private Date modified;
	    private Date last_login;
	    private String token;
	    private int isactive;
	    
	    
		public UUID getId() {
			return id;
		}
		public void setId(UUID id) {
			this.id = id;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
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
	    
	    
	    
	    

}
