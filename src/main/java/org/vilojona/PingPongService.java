package org.vilojona;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class PingPongService {

    @Inject
    CuriousService curiousService;
    
    @Inject
    WiseService wiseService;

    public String sendCurious(String message) {
        return curiousService.chat(message);
    }

    public String sendWise(String message) {
        return wiseService.chat(message);
    }
   
    public String interaction(String message) {
        var curiousAnswer = sendCurious(message);

        return sendWise(curiousAnswer);
    }
}
