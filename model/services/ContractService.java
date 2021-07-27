package com.rodrigo.aula198.model.services;

import java.util.Calendar;
import java.util.Date;

import com.rodrigo.aula198.model.entities.Installment;
import com.rodrigo.aula198.model.entities.Contract;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue() / months;
		for(int i=1; i<=months; i++) {
			double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
			double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
			Date dueDate = addMonths(contract.getDate(), i);
			contract.getInstallment().add(new Installment(dueDate, fullQuota));
		}
		
	}
	
	private Date addMonths(Date data, int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, N);
		return calendar.getTime();
	}
	
}
