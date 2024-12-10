package org.vilojona;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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
    @Path("/{numberOfQuestions}")
    public String chat(@PathParam("numberOfQuestions") Integer numberOfQuestions, String topic) {
        StringBuilder sb = new StringBuilder();
        sb.append("> Topic: ").append(topic).append("\n");
        while (numberOfQuestions-- > 0) {
            String question = curiousService.chat(topic);
            sb.append("> Question: ").append(question).append("\n");
            topic = wiseService.chat(question);
            sb.append("> Answer: ").append(topic).append("\n");
        }
 
        return sb.toString();
    }
}
