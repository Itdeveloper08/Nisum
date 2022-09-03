package com.appservices.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Utilities {

	@Value("${app.debug}")
	private boolean debug_system;
	
	
//	@Value("${app.formatpass}")
//	private static  String pass_regex;
	

	private static final String email_regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static final String name_regex = "^[\\p{L} .'-]+$";
	private static final String pass_regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$";

	public static boolean emailValidator(String email) {
		Pattern email_patern = Pattern.compile(email_regex);
		if (email == null) {
			return false;
		}

		Matcher matcher = email_patern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean nameValidator(String name) {
		Pattern name_patern = Pattern.compile(name_regex);
		
		if (name == null) {
			return false;
		}

		Matcher matcher = name_patern.matcher(name);
		return matcher.matches();
	}
	
	public static boolean isValidPassword(String password) {
		Pattern pass_patern = Pattern.compile(pass_regex);

		if (password == null) {
			return false;
		}

		Matcher m = pass_patern.matcher(password);

		return m.matches();
	}


	public void PrintLogger(String origen, String mensaje) {
		if (debug_system) {
			System.out.println("[ LOG-APPSERVICE ]--" + Getdate(FormatoFecha.yyyy_MM_dd_HH_mm_ss) + "--[ " + origen
					+ " ] " + mensaje);
		}
	}

	public static void PrintLoggerExeption(String origen, String mensaje) {

		System.out.println("[ LOG-APPSERVICE-ERROR ]--" + Getdate(FormatoFecha.yyyy_MM_dd_HH_mm_ss) + "--[ " + origen
				+ " ] " + mensaje);

	}

	public static String Getdate(String format) {
		String fecha = "";
		Date fechaActual = new Date();
		DateFormat formatoHora = new SimpleDateFormat(format);
		fecha = formatoHora.format(fechaActual);
		return fecha;
	}

	
}
