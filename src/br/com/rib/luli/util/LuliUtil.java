package br.com.rib.luli.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LuliUtil {

	public static String dateFormatter(LocalDateTime time)
	{
		DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return dataFormatter.format(time);
	}
	
}
