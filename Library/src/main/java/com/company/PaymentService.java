package com.company;

public class PaymentService {
    boolean paid = false;

    // Checks and sets 'paid'
    public boolean isPaid() {
        return paid;
    }
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    // Functions for purchase
    public boolean pay(int amount, int cost) {
        if (amount < cost) {
            return false;
        }
        paid = true;
        return true;
    }
    public void pay(int cost) {
        if (5 > cost) {
            throw new IllegalStateException();
        }
    }
}
