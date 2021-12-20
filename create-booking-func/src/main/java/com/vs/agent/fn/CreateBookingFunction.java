package com.vs.agent.fn;

import com.fnproject.fn.api.FnConfiguration;
import com.fnproject.fn.api.RuntimeContext;

public class CreateBookingFunction {
    private String bookingEndpoint;

    public String handleRequest(String bookingJsonString) {
        System.out.println("Creating new booking - " + bookingJsonString);
        return RestUtil.postJsonRequest(bookingEndpoint, bookingJsonString);
    }

    @FnConfiguration
    public void config(RuntimeContext ctx) {
        bookingEndpoint = ctx.getConfigurationByKey("BOOKING_ENDPOINT").orElse("NOTSET");
    }

}
