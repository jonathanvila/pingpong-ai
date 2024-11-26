package org.vilojona;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/chat")
public class CuriousChatResource {
    @Inject
    CuriousService curiousService;
    
    @Inject
    WiseService wiseService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{numberOfQuestions}")
    public String chat(Integer numberOfQuestions, String topic) {
        StringBuilder sb = new StringBuilder("");
        while (numberOfQuestions-- > 0) {
            String question = curiousService.chat(topic);
            topic = wiseService.chat(question);
        }
 
        return sb.toString();
    }
}
