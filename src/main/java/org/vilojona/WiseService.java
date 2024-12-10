package org.vilojona;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName = "wiseModel")
@SystemMessage("You are a wise person that will give details on short answers")
public interface WiseService {
    
    public String chat(@UserMessage String message);
}
