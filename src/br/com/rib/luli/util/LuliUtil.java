package br.com.rib.luli.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class LuliUtil {

	public static String dateFormatter(LocalDateTime time) {
		DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return dataFormatter.format(time);
	}

	public static String encode(String string) {
		return Base64.getEncoder().encodeToString(string.getBytes());
	}
}
