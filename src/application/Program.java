package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data");

        System.out.print("Number: ");
        int number = scan.nextInt();

        System.out.print("Date (dd/MM/yyy): ");
        Date date = sdf.parse(scan.next());

        System.out.print("Contract value: ");
        Double contractValue = scan.nextDouble();

        System.out.print("Enter number of installments: ");
        int numberOfInstallments = scan.nextInt();

        Contract contract = new Contract(number, date, contractValue);

        ContractService cs = new ContractService(new PaypalService());

        cs.processContract(contract, numberOfInstallments);

        System.out.println("Installments: ");

        List<Installment> installments = contract.getInstallments();

        for (Installment installment : installments) {
            System.out.println(sdf.format(installment.getDueDate()) + " - " +
                    installment.getAmount());
        }

    }
}
