package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ContractService {

    protected OnlinePayment onlinePayment;

    public ContractService(OnlinePayment onlinePayment) {
        this.onlinePayment = onlinePayment;
    }

    public void processContract(Contract contract, Integer months){
        Double quota = contract.getTotalValue() / months;

        for (int i = 0; i < months; i++) {

            Calendar cal = Calendar.getInstance();
            cal.setTime(contract.getDate());
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);

            LocalDate dayOfPayment = LocalDate.of(year, month, day);
            LocalDate paymentDay = dayOfPayment.plusMonths(i + 1);
            Date date1 = Date.from(paymentDay.atStartOfDay(ZoneId.systemDefault()).toInstant());


            Double interest = onlinePayment.interest(quota, i + 1);
            Double totalPaymentMonth = onlinePayment.paymentFee(interest);

            Installment installment = new Installment(date1, totalPaymentMonth);
            contract.addInstallment(installment);
        }
    }
}
