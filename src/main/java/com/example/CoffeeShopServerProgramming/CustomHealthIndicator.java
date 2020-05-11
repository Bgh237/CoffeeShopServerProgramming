package com.example.CoffeeShopServerProgramming;


import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // Below is a custom response when using the actuator to check the health of the app
        // If you throw an exception, the status will be DOWN with the exception message.
        
        builder.up()
                .withDetail("app", "YEAH BOI! The app is up and running")
                .withDetail("error", "Pffffft! Behave! Nothing wrong!");
    }
}
