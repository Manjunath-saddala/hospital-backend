package com.health.management.patient_module.controller;


import com.health.management.patient_module.model.Billing;
import com.health.management.patient_module.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin(origins = "http://localhost:3000")// Enable CORS for frontend
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping
    public ResponseEntity<Billing> createBill(@RequestBody Billing billing) {
        Billing newBill = billingService.createBilling(billing);
        return ResponseEntity.ok(newBill);
    }

    @GetMapping
    public ResponseEntity<List<Billing>> getAllBills() {
        return ResponseEntity.ok(billingService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBillById(@PathVariable Long id) {
        Optional<Billing> bill = billingService.getBillingById(id);
        return bill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Billing> updateBill(@PathVariable Long id, @RequestBody Billing billing) {
        Billing updatedBill = billingService.updateBilling(id, billing);
        return updatedBill != null ? ResponseEntity.ok(updatedBill) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billingService.deleteBilling(id);
        return ResponseEntity.noContent().build();
    }
}
