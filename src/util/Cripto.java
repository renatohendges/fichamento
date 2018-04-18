package util;

import java.util.Scanner;

public class Cripto {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a senha para criptografar");
		String senha = sc.nextLine();
		System.out.println(CritpoUtil.stringParaMd5(senha));
		sc.close();
	}
}
