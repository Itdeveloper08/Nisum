package com.appservices.service.impl;

import com.appservices.model.phones;
import com.appservices.model.user;
import com.appservices.repo.IUser;
import com.appservices.response.InfoResponse;
import com.appservices.repo.IGenericRepo;
import com.appservices.service.IUserService;
import com.appservices.util.Codes;
import com.appservices.util.UserInfo;
import com.appservices.util.Utilities;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CRUDImpl<user, UUID> implements IUserService {

	@Value("${app.regularexp}")
	private String passwordex;


	@Autowired
	public Utilities utilities;
	
	
    @Autowired
    private IUser repo;

    @Override
    protected IGenericRepo<user, UUID> getRepo() {
        return repo;
    }

    @Override
    public Page<user> listarPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }
    
    @Override
	public user findByEmail(String email) {
		return repo.findByEmail(email);
	}
    
    @Override
    public InfoResponse saveUser(user user) {
    	String Method = "Endpoint Registe-saveUser";
		InfoResponse infoResponse = new InfoResponse();
		List<String> message = new ArrayList<String>();
		boolean flag = true;
		
		try {
			String name = user.getName();
			String email = user.getEmail();
			String password = user.getPassword();

			if (!utilities.isValidPassword(password)) {
				utilities.PrintLogger(Method, "Password is not valid");
				message.add("Password is not valid: " + passwordex);
				infoResponse.setMessage(message);
				flag = false;
			}

			if (!utilities.emailValidator(email)) {
				utilities.PrintLogger(Method, "The email address is not valid");
				message.add("The email address  is not valid");
				infoResponse.setCode(Codes._error);
				infoResponse.setMessage(message);
				flag = false;
			} else {

				try {
					user user_r = repo.findByEmail(email);
					if (user_r != null) {
						utilities.PrintLogger(Method, "user_r: " + user_r);
						message.add("This Email " + email + " is already exist!");
						infoResponse.setCode(Codes._error);
						infoResponse.setMessage(message);
						flag = false;
					}
				} catch (Exception ex) {
					// TODO: handle exception
					String queyry = Method + "Execute-findByEmail";
					utilities.PrintLoggerExeption(queyry, "  Error{");
					StackTraceElement[] elementRaster = ex.getStackTrace();
					for (int i = 0; i < elementRaster.length; i++) {
						StackTraceElement elementSTD = elementRaster[i];
						utilities.PrintLoggerExeption(queyry,
								"   " + i + "- getClassName= " + elementSTD.getClassName());
						utilities.PrintLoggerExeption(queyry, "   getMethodName=" + elementSTD.getMethodName());
						utilities.PrintLoggerExeption(queyry, "   getLineNumber=" + elementSTD.getLineNumber());
						utilities.PrintLoggerExeption(queyry, "   errorMSG=" + ex.getMessage());
					}
					utilities.PrintLoggerExeption(queyry, "  }");
					message.add("Error Execute in  " + queyry);
					infoResponse.setCode(Codes._code_error);
					infoResponse.setMessage(message);
					return infoResponse;
				}
			}

			if (!utilities.nameValidator(name)) {
				utilities.PrintLogger(Method, "The name is not valid");
				message.add("The name is not valid");
				infoResponse.setCode(Codes._error);
				infoResponse.setMessage(message);
				flag = false;
			}

			if (flag) {
				UUID ID = UUID.randomUUID();
				try {

					user.setId(ID);
					user.setCreated(new Date());
					user.setLast_login(new Date());
					user.setModified(null);
					user.setToken(GenerateToken(name, email, "users"));
					
					for (phones phone : user.getPhones()) {
						phone.setUser(user);
					}
					repo.save(user);

					infoResponse.setCode(Codes._execute);
					message.add("User Register Successfull!!");
					infoResponse.setMessage(message);
				} catch (Exception ex) {
					// TODO: handle exception
					String queyry = Method + "Execute-registrar User";
					utilities.PrintLoggerExeption(queyry, "  Error{");
					StackTraceElement[] elementRaster = ex.getStackTrace();
					for (int i = 0; i < elementRaster.length; i++) {
						StackTraceElement elementSTD = elementRaster[i];
						utilities.PrintLoggerExeption(queyry,
								"   " + i + "- getClassName= " + elementSTD.getClassName());
						utilities.PrintLoggerExeption(queyry, "   getMethodName=" + elementSTD.getMethodName());
						utilities.PrintLoggerExeption(queyry, "   getLineNumber=" + elementSTD.getLineNumber());
						utilities.PrintLoggerExeption(queyry, "   errorMSG=" + ex.getMessage());
					}
					utilities.PrintLoggerExeption(queyry, "  }");
					message.add("Error Execute in  " + queyry);
					infoResponse.setCode(Codes._code_error);
					infoResponse.setMessage(message);
					return infoResponse;
				}

				try {

					user user_r = repo.getById(ID);

					UserInfo UserInfo = new UserInfo();
					UserInfo.setId(user_r.getId());
					UserInfo.setIsactive(user_r.getIsactive());
					UserInfo.setCreated(user_r.getCreated());
					UserInfo.setLast_login(user_r.getLast_login());
					UserInfo.setModified(user_r.getModified());
					UserInfo.setToken(user_r.getToken());
					UserInfo.setUser(user_r.getEmail());

					infoResponse.setUserInfo(UserInfo);

				} catch (Exception ex) {
					// TODO: handle exception
					String queyry = Method + "Execute-ListarPorId User";
					utilities.PrintLoggerExeption(queyry, "  Error{");
					StackTraceElement[] elementRaster = ex.getStackTrace();
					for (int i = 0; i < elementRaster.length; i++) {
						StackTraceElement elementSTD = elementRaster[i];
						utilities.PrintLoggerExeption(queyry,
								"   " + i + "- getClassName= " + elementSTD.getClassName());
						utilities.PrintLoggerExeption(queyry, "   getMethodName=" + elementSTD.getMethodName());
						utilities.PrintLoggerExeption(queyry, "   getLineNumber=" + elementSTD.getLineNumber());
						utilities.PrintLoggerExeption(queyry, "   errorMSG=" + ex.getMessage());
					}
					utilities.PrintLoggerExeption(queyry, "  }");
					message.add("Error Execute in  " + queyry);
					infoResponse.setCode(Codes._code_error);
					infoResponse.setMessage(message);
					return infoResponse;
				}

			}

			return infoResponse;
		} catch (Exception ex) {
			// TODO: handle exception
			utilities.PrintLoggerExeption(Method, "  Error{");
			StackTraceElement[] elementRaster = ex.getStackTrace();
			for (int i = 0; i < elementRaster.length; i++) {
				StackTraceElement elementSTD = elementRaster[i];
				utilities.PrintLoggerExeption(Method, "   " + i + "- getClassName= " + elementSTD.getClassName());
				utilities.PrintLoggerExeption(Method, "   getMethodName=" + elementSTD.getMethodName());
				utilities.PrintLoggerExeption(Method, "   getLineNumber=" + elementSTD.getLineNumber());
				utilities.PrintLoggerExeption(Method, "   errorMSG=" + ex.getMessage());
			}
			utilities.PrintLoggerExeption(Method, "  }");
			message.add("Error Execute in  " + Method);
			infoResponse.setCode(Codes._code_error);
			infoResponse.setMessage(message);
			return infoResponse;
		}
    	
    }
    
	
	  @Override
	    public InfoResponse searchUser(String email) {
	    	String Method = "Endpoint Search-searchUser";
			InfoResponse infoResponse = new InfoResponse();
			List<String> message = new ArrayList<String>();
			boolean flag = true;
			
			try {
						user user_r = repo.findByEmail(email);
						
						if(user_r!=null) {
							UserInfo UserInfo = new UserInfo();
							UserInfo.setId(user_r.getId());
							UserInfo.setIsactive(user_r.getIsactive());
							UserInfo.setCreated(user_r.getCreated());
							UserInfo.setLast_login(user_r.getLast_login());
							UserInfo.setModified(user_r.getModified());
							UserInfo.setToken(user_r.getToken());
							UserInfo.setUser(user_r.getEmail());

							infoResponse.setUserInfo(UserInfo);
							infoResponse.setCode(Codes._execute);
							message.add("User Found!!");
							infoResponse.setMessage(message);
						}else {
							infoResponse.setCode(Codes._execute);
							message.add("User Not Register!!");
							infoResponse.setMessage(message);
						}
						
						return infoResponse;

					} catch (Exception ex) {
						// TODO: handle exception
						String queyry = Method + "Execute-Search User";
						utilities.PrintLoggerExeption(queyry, "  Error{");
						StackTraceElement[] elementRaster = ex.getStackTrace();
						for (int i = 0; i < elementRaster.length; i++) {
							StackTraceElement elementSTD = elementRaster[i];
							utilities.PrintLoggerExeption(queyry,
									"   " + i + "- getClassName= " + elementSTD.getClassName());
							utilities.PrintLoggerExeption(queyry, "   getMethodName=" + elementSTD.getMethodName());
							utilities.PrintLoggerExeption(queyry, "   getLineNumber=" + elementSTD.getLineNumber());
							utilities.PrintLoggerExeption(queyry, "   errorMSG=" + ex.getMessage());
						}
						utilities.PrintLoggerExeption(queyry, "  }");
						message.add("Error Execute in  " + queyry);
						infoResponse.setCode(Codes._code_error);
						infoResponse.setMessage(message);
						return infoResponse;
					}

				}
	  
	  
	  @Override
	    public InfoResponse deleteUser(String email) {
	    	String Method = "Endpoint Delete-deleteUser";
			InfoResponse infoResponse = new InfoResponse();
			List<String> message = new ArrayList<String>();
			boolean flag = true;
			
			try {
						user user_r = repo.findByEmail(email);
						
						if(user_r!=null) {
							UserInfo UserInfo = new UserInfo();
							UserInfo.setId(user_r.getId());
							UserInfo.setIsactive(user_r.getIsactive());
							UserInfo.setCreated(user_r.getCreated());
							UserInfo.setLast_login(user_r.getLast_login());
							UserInfo.setModified(user_r.getModified());
							UserInfo.setToken(user_r.getToken());
							UserInfo.setUser(user_r.getEmail());

							infoResponse.setUserInfo(UserInfo);
							infoResponse.setCode(Codes._execute);
							message.add("User Found!!");
							infoResponse.setMessage(message);
							
							repo.deleteById(user_r.getId());
							
						}else {
							infoResponse.setCode(Codes._execute);
							message.add("User Not Deleted!!");
							infoResponse.setMessage(message);
						}
						
						return infoResponse;

					} catch (Exception ex) {
						// TODO: handle exception
						String queyry = Method + "Execute-Delete User";
						utilities.PrintLoggerExeption(queyry, "  Error{");
						StackTraceElement[] elementRaster = ex.getStackTrace();
						for (int i = 0; i < elementRaster.length; i++) {
							StackTraceElement elementSTD = elementRaster[i];
							utilities.PrintLoggerExeption(queyry,
									"   " + i + "- getClassName= " + elementSTD.getClassName());
							utilities.PrintLoggerExeption(queyry, "   getMethodName=" + elementSTD.getMethodName());
							utilities.PrintLoggerExeption(queyry, "   getLineNumber=" + elementSTD.getLineNumber());
							utilities.PrintLoggerExeption(queyry, "   errorMSG=" + ex.getMessage());
						}
						utilities.PrintLoggerExeption(queyry, "  }");
						message.add("Error Execute in  " + queyry);
						infoResponse.setCode(Codes._code_error);
						infoResponse.setMessage(message);
						return infoResponse;
					}

				}

		public String GenerateToken(String name, String user, String scope) {
			String Method= "Endpoint Registe-GenerateToken";
			try {
				SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
				String signatureString = signatureAlgorithm.toString();
				// Creating the Header of
				HashMap<String, Object> header = new HashMap<String, Object>();
				header.put("alg", signatureString);
				header.put("typ", "JWT");
				JwtBuilder tokenJWT = Jwts.builder().setHeader(header).setId("1").setSubject(user)
						.claim("name", name).claim("scope", scope)
						.setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
						.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)));
				String tokenJWTString = tokenJWT.compact();
				System.out.println(tokenJWTString);

				return tokenJWTString;
			} catch (Exception ex) {
				String queyry = Method ;
				utilities.PrintLoggerExeption(Method, "  Error{");
				StackTraceElement[] elementRaster = ex.getStackTrace();
				for (int i = 0; i < elementRaster.length; i++) {
					StackTraceElement elementSTD = elementRaster[i];
					utilities.PrintLoggerExeption(Method, "   " + i + "- getClassName= " + elementSTD.getClassName());
					utilities.PrintLoggerExeption(Method, "   getMethodName=" + elementSTD.getMethodName());
					utilities.PrintLoggerExeption(Method, "   getLineNumber=" + elementSTD.getLineNumber());
					utilities.PrintLoggerExeption(Method, "   errorMSG=" + ex.getMessage());
				}
				utilities.PrintLoggerExeption(Method, "  }");
				return "Error creating the token JWT";
			}
		}
		
	    	
	    
	    
		

}
