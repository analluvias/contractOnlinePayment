package model.services;

public interface OnlinePayment {

    Double paymentFee(Double amount);

    Double interest(Double amount, Integer months);
}
