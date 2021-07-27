package com.rodrigo.aula198.model.services;



public class PaypalService  implements OnlinePaymentService{
	
	public static final double PAYMENT_FEE = 0.01;
	public static final double MONTHLY_FEE = 0.02;

	@Override
	public double paymentFee(double amount) {
		return amount * PAYMENT_FEE;
	}

	@Override
	public double interest(double amount, int months) {
		return amount * months * MONTHLY_FEE;
	}

}
