package model.entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Installment { //parcela
    protected Date dueDate; //datadevencimento
    protected Double amount; //valor
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Installment(Date dueDate, Double amount) {
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return sdf.format(this.getDueDate()) + " - " +this.getAmount();
    }
}
