package application;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Contract;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato:");
		System.out.print("Numero: ");
		int number = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("Valor do contrato: ");
		double totalValue = sc.nextDouble();
		
		System.out.print("Entre com o numero de parcelas: ");
		int months= sc.nextInt();
		
		Contract contract= new Contract(number, date, totalValue);
		ContractService contractService = new ContractService(new PaypalService()); //Instanciando o serviço e realizando uma injeção de dependencia
		
		contractService.processContract(contract, months); // Chamando o metodo para criar as parcelas
		
		System.out.println(contract);
		
		
		sc.close();
	}
}