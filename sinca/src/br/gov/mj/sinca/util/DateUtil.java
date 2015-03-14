package br.gov.mj.sinca.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String DD_MM_YYYY = "dd/MM/yyyy";
    public static String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:MM:ss";

    public static Date parseDate(String data, String formato) {
	try {
	    return new SimpleDateFormat(formato).parse(data);
	} catch (Exception e) {
	    return null;
	}
    }

    public static String formataDate(Date data, String formato) {
	try {
	    return new SimpleDateFormat(formato).format(data);
	} catch (Exception e) {
	    return "";
	}
    }

    public static String dataHoraAtual() {
	try {
	    return formataDate(new Date(), DD_MM_YYYY_HH_MM_SS);
	} catch (Exception e) {
	    return null;
	}
    }

    public static void main(String[] args) throws ParseException {
	    Date dataInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("02/02/2015 21:33:45"); 
	    Date dataFim = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/02/2015 21:33:45");
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date((dataInicio.getTime() - dataFim.getTime()))); 
	    System.out.println(formataDate(c.getTime(), "HH:mm:ss"));
	    
	    
    }

}
