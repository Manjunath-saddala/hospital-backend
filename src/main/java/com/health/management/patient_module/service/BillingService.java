package com.health.management.patient_module.service;

import com.health.management.patient_module.model.Billing;
import com.health.management.patient_module.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    public Billing createBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Transactional(readOnly = true)
    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }

    public Optional<Billing> getBillingById(Long id) {
        return billingRepository.findById(id);
    }

    public Billing updateBilling(Long id, Billing updatedBilling) {
        return billingRepository.findById(id).map(bill -> {
            bill.setPatientName(updatedBilling.getPatientName());
            bill.setBillingDate(updatedBilling.getBillingDate());
            bill.setTotalAmount(updatedBilling.getTotalAmount());
            bill.setPaymentStatus(updatedBilling.getPaymentStatus());
            bill.setServices(updatedBilling.getServices());
            return billingRepository.save(bill);
        }).orElse(null);
    }

    public void deleteBilling(Long id) {
        billingRepository.deleteById(id);
    }
}
