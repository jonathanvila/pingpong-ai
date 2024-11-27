package org.vilojona;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;


@RegisterAiService
@SystemMessage("You are a curious person that creates a short question for every message you receive.")
public interface CuriousService {
    public String chat(@UserMessage String message);
}
