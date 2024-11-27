package org.vilojona;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
//@SystemMessage("You are a wise person that will give details on short answers")
public interface WiseService {
    
    public String chat(@UserMessage String message);
}
