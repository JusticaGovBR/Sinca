package br.gov.mj.sinca.util;


/**
 * Valida CNPJ sem mascara
 * 
 * @author Igor Galv�o
 * 
 */
public class CpfCnpjUtil {
	static public boolean validarCNPJ(String cnpj) {
		int soma = 0, dig;
		String cnpj_calc = cnpj.substring(0, 12);

		if (cnpj.length() != 14)
			return false;

		char[] chr_cnpj = cnpj.toCharArray();

		/* Primeira parte */
		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);

		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		/* Segunda parte */
		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		
		
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		return cnpj.equals(cnpj_calc);
	}

	
	static public String FormatCPF(String cpf){
		String sCpf = String.valueOf(cpf);
		while(sCpf.length() < 11){
			sCpf = "0" + sCpf;
		}
		
		return StringUtil.format("###.###.###-##", sCpf);
	}
	
	public static boolean ValidateCPF(String cpf){
		int priVer;
		int segVer;
		
		// Ajustada rotina de validação do CPF para tratar tanto 
		// número formatado quanto não formatado. 
		String cpfVerificar = null;
		String digito = null;
		// CPF inválido
		if (cpf.length() < 11) {
			return false;
		// CPF formatado
		} else if (cpf.length() > 11) {
			cpfVerificar = cpf.substring(0,11);
			digito = cpf.substring(12, 14);
			cpfVerificar = cpfVerificar.replaceAll("[^0-9]", "");
		// CPF não formatado
		}else {
			cpfVerificar = cpf.substring(0,9);
			digito = cpf.substring(9, 11);
		}

		char[] auxCpf = cpfVerificar.toCharArray();  
		
		int[] valPri = {10,9,8,7,6,5,4,3,2};
		int[] valSeg = {11,10,9,8,7,6,5,4,3,2};	
		
		int i = 0;
		
		Double sum = 0d;		
		for (char c : auxCpf) {
			sum += (double) (valPri[i] *  Character.getNumericValue(c)); 
			i++;
		}		
		if((sum % 11) < 2){
			priVer = 0;
		}else{
			priVer = (int) (11 - (sum % 11));
		}
		
		//Adiciona o digito verificador para calculo do segundo digito
		cpfVerificar = cpfVerificar + priVer;
		auxCpf = cpfVerificar.toCharArray();		
		
		//Reseta a somatória e calcula o segundo digito.
		sum = 0d;
		i = 0;
		for (char c : auxCpf) {
			sum += (double) (valSeg[i] *  Character.getNumericValue(c));
			i++;
		}		
		if((sum % 11) < 2){
			segVer = 0;
		}else{
			segVer = (int) (11 - (sum % 11));
		}	
		
		
		return digito.equals(String.valueOf(priVer) + String.valueOf(segVer));
	}
	
	public static void main(String[] args) {
	    System.out.println(FormatCPF("043.013.619-60"));
		//if(CpfCnpjUtil.ValidateCPF("04301361960"))
		if(CpfCnpjUtil.ValidateCPF("043.013.619-60"))
			System.out.println("OK");
		else
			System.out.println("CPF Inválido");
	}
	
}
