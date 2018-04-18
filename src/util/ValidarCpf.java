package util;

public abstract class ValidarCpf {
	private static final int[] PESO = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calcular(final String cpf) {
		int soma = 0;
		for (int i = cpf.length() - 1; i >= 0; i--) {
			soma += Integer.parseInt(cpf.substring(i, i + 1)) * PESO[PESO.length - cpf.length() + i];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isCpfValido(final String cpf) {
		if ((cpf == null) || (cpf.length() != 11) || cpf.matches(cpf.charAt(0) + "{11}")) {
			return false;
		}
		final Integer digit1 = calcular(cpf.substring(0, 9));
		final Integer digit2 = calcular(cpf.substring(0, 9) + digit1);
		return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
	}
}
