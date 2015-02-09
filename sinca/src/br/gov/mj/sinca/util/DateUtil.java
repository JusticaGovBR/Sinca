package br.gov.mj.sinca.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String DD_MM_YYYY = "dd/MM/yyyy";
    
    public static Date parseDate(String data, String formato){
	try {
	    return new SimpleDateFormat(formato).parse(data);
	} catch (Exception e) {
	    return null;
	}	
    }

    public static String dataHoraAtual(){
	try {
	    return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	} catch (Exception e) {
	    return null;
	}	
    }

    
}
