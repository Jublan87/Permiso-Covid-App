package com.unla.grupo21.helpers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Funciones {

	public static boolean esBisiesto(int anio) {
		return anio%4==0 && !(anio%100==0 && anio%400!=0);
	}
	
	public static String traerFechaCorta(LocalDate fecha) {
		return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static String traerFechaHql(LocalDate fecha) {
		return fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static String traerHoraCorta(LocalTime hora) {
		return hora.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	
	public static boolean esDiaHabil(LocalDate fecha) {
		return fecha.getDayOfWeek().getValue() <= 5;
	}
	
	public static String traerDiaDeLaSemana(LocalDate fecha) {
		int dia = fecha.getDayOfWeek().getValue();
		String diaNombre[] = {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"};
		return diaNombre[dia-1];
	}
	
	public static String traerMesEnLetras(LocalDate fecha) {
		int mes = fecha.getMonthValue();
		String mesNombre[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		return mesNombre[mes-1];
	}
	
	public static String traerFechaLarga(LocalDate fecha) {
		return Funciones.traerDiaDeLaSemana(fecha) + " " + fecha.getDayOfMonth() + " de " + Funciones.traerMesEnLetras(fecha) + " de " + fecha.getYear();
	}
	
	public static int traerCantDiasDeUnMes(int anio, int mes) {
		return YearMonth.of(anio,mes).lengthOfMonth();
	}
	
	public static double aproximar2Decimal(double valor) {
		return (double)Math.round(valor * 100) / 100;
	}
	
	public static boolean esNumero(char c) {
		return Character.isDigit(c);
	}
	
	public static boolean esLetra(char c) {
		return Character.isLetter(c);
	}
	
	public static boolean esCadenaNros(String cadena) {
		boolean esCadenaN = true;
		try {
	        Double.parseDouble(cadena);
	    } catch (NumberFormatException nfe) {
	        esCadenaN = false;
	    }
		return esCadenaN;
	}
	
	public static boolean esCadenaLetras(String cadena) {
		return cadena.matches("[a-zA-Z]+");
	}
}
