package ru.sbt.mipt.oop;

public class SMSNotifier implements Notifier {
    @Override
    public void sendNotification() {
        System.out.println("Sending sms...");
    }
}
