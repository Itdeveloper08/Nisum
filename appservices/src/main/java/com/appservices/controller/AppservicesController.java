package com.appservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import com.appservices.model.user;
import com.appservices.repo.IUser;
import com.appservices.response.InfoResponse;
import com.appservices.service.IUserService;
import com.appservices.util.Codes;
import com.appservices.util.UserInfo;
import com.appservices.util.Utilities;
import com.google.gson.Gson;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AppservicesController {

	@Value("${app.regularexp}")
	private String passwordex;

	@Autowired
	private IUserService serviceuser;

	@Autowired
	public Utilities utilities;

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public InfoResponse registeruser(@Valid @RequestBody user user) throws Exception {
		InfoResponse infoResponse = new InfoResponse();
		List<String> message = new ArrayList<String>();
		try {

			utilities.PrintLogger("Endpoint Register", "Start process!!");
			infoResponse = serviceuser.saveUser(user);

			return infoResponse;
		} catch (Exception ex) {
			// TODO: handle exception
			utilities.PrintLoggerExeption("Endpoint Register", "  Error{");
			StackTraceElement[] elementRaster = ex.getStackTrace();
			for (int i = 0; i < elementRaster.length; i++) {
				StackTraceElement elementSTD = elementRaster[i];
				utilities.PrintLoggerExeption("Endpoint Register",
						"   " + i + "- getClassName= " + elementSTD.getClassName());
				utilities.PrintLoggerExeption("Endpoint Register", "   getMethodName=" + elementSTD.getMethodName());
				utilities.PrintLoggerExeption("Endpoint Register", "   getLineNumber=" + elementSTD.getLineNumber());
				utilities.PrintLoggerExeption("Endpoint Register", "   errorMSG=" + ex.getMessage());
			}
			utilities.PrintLoggerExeption("Endpoint Register", "  }");
			message.add("Error Execute in Endpoint Registe");
			infoResponse.setCode(Codes._code_error);
			infoResponse.setMessage(message);
			return infoResponse;
		}

	}
	
	
	@RequestMapping(value = "/searchuser", method = RequestMethod.GET)
	public InfoResponse searchuser(@RequestParam("email") String email) throws Exception {
		InfoResponse infoResponse = new InfoResponse();
		List<String> message = new ArrayList<String>();
		try {

			utilities.PrintLogger("Endpoint Search", "Start process!!"+email);
			infoResponse = serviceuser.searchUser(email.trim());

			return infoResponse;
		} catch (Exception ex) {
			// TODO: handle exception
			utilities.PrintLoggerExeption("Endpoint Search", "  Error{");
			StackTraceElement[] elementRaster = ex.getStackTrace();
			for (int i = 0; i < elementRaster.length; i++) {
				StackTraceElement elementSTD = elementRaster[i];
				utilities.PrintLoggerExeption("Endpoint Search",
						"   " + i + "- getClassName= " + elementSTD.getClassName());
				utilities.PrintLoggerExeption("Endpoint Search", "   getMethodName=" + elementSTD.getMethodName());
				utilities.PrintLoggerExeption("Endpoint Search", "   getLineNumber=" + elementSTD.getLineNumber());
				utilities.PrintLoggerExeption("Endpoint Search", "   errorMSG=" + ex.getMessage());
			}
			utilities.PrintLoggerExeption("Endpoint Search", "  }");
			message.add("Error Execute in Endpoint Search");
			infoResponse.setCode(Codes._code_error);
			infoResponse.setMessage(message);
			return infoResponse;
		}

	}
	
	
	@DeleteMapping(value = "/deleteuser")
	public InfoResponse deleteuser(@RequestParam("email") String email) throws Exception {
		InfoResponse infoResponse = new InfoResponse();
		List<String> message = new ArrayList<String>();
		try {

			utilities.PrintLogger("Endpoint Search", "Start process!!"+email);
			infoResponse = serviceuser.deleteUser(email.trim());

			return infoResponse;
		} catch (Exception ex) {
			// TODO: handle exception
			utilities.PrintLoggerExeption("Endpoint Delete", "  Error{");
			StackTraceElement[] elementRaster = ex.getStackTrace();
			for (int i = 0; i < elementRaster.length; i++) {
				StackTraceElement elementSTD = elementRaster[i];
				utilities.PrintLoggerExeption("Endpoint Delete",
						"   " + i + "- getClassName= " + elementSTD.getClassName());
				utilities.PrintLoggerExeption("Endpoint Delete", "   getMethodName=" + elementSTD.getMethodName());
				utilities.PrintLoggerExeption("Endpoint Delete", "   getLineNumber=" + elementSTD.getLineNumber());
				utilities.PrintLoggerExeption("Endpoint Delete", "   errorMSG=" + ex.getMessage());
			}
			utilities.PrintLoggerExeption("Endpoint Delete", "  }");
			message.add("Error Execute in Endpoint Delete");
			infoResponse.setCode(Codes._code_error);
			infoResponse.setMessage(message);
			return infoResponse;
		}

	}

	



}
