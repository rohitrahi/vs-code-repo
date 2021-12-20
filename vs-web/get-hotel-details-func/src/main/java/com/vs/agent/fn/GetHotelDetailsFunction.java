package com.vs.agent.fn;

import com.fnproject.fn.api.FnConfiguration;
import com.fnproject.fn.api.RuntimeContext;

public class GetHotelDetailsFunction {
    private String hotelEndpoint;

    public String handleRequest(String hotelId) {
        System.out.println("Getting Hotel Details for Hotel ID - " + hotelId);
        String invokeEndpoint = hotelEndpoint+hotelId;
        System.out.println("Invoking Endpoint " + invokeEndpoint);
        return RestUtil.getJsonRequest(invokeEndpoint);
    }

    @FnConfiguration
    public void config(RuntimeContext ctx) {
        hotelEndpoint = ctx.getConfigurationByKey("HOTEL_ENDPOINT").orElse("NOTSET");
    }

}
