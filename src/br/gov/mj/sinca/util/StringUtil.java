package br.gov.mj.sinca.util;

/**
 * Utilidades para o uso da String
 * 
 * @author bruno.melo
 * 
 */
public class StringUtil {

	/**
	 * transforma a primeira letra de um texto em minuscula
	 * 
	 * @param texto
	 * @return
	 */
	public static String lowerCaseFirstLetter(String texto) {
		return texto.substring(0, 1).toLowerCase() + texto.substring(1);
	}

	// Coloca Mascara
	public static String format(String format, String value, char c) {
		if (format == null || format.trim().equals(""))
			return null;
		int index = 0;
		StringBuilder s = new StringBuilder();
		char charsFormat[] = format.toCharArray();
		char charsValue[] = value.toCharArray();
		char arr$[] = charsFormat;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			char element = arr$[i$];
			if (element != c)
				s.append(element);
			else
				s.append(charsValue[index++]);
		}

		return s.toString();
	}

	public static String format(String format, String value) {
		return format(format, value, '#');
	}

	public static String unformat(final String format, final String value) {
		return StringUtil.unformat(format, value, '#');
	}

	public static String unformat(final String format, final String value,
			final char c) {

		if (format == null || format.trim().equals(""))
			return null;

		if (value == null || value.trim().equals(""))
			return null;

		StringBuilder s = new StringBuilder();

		char[] charsFormat = format.toCharArray();
		char[] charsValue = value.toCharArray();

		for (int i = 0; i < charsFormat.length; i++) {
			if (charsFormat[i] == c) {
				s.append(charsValue[i]);
			}
		}

		return s.toString();
	}

	public static boolean ehInteiro(String s) {

		// cria um array de char
		char[] c = s.toCharArray();
		boolean d = true;

		for (int i = 0; i < c.length; i++)
			// verifica se o char não é um dígito
			if (!Character.isDigit(c[i])) {
				d = false;
				break;
			}
		return d;

	}
}
