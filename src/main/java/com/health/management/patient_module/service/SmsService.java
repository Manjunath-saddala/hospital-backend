package com.health.management.patient_module.service;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendSms(String phoneNumber, String message) {
        // Simulating SMS sending (replace with real SMS service)
        System.out.println("Sending SMS to: " + phoneNumber);
        System.out.println("Message: " + message);
    }
}
