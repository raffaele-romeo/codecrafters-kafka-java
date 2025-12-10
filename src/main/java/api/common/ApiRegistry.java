package api.common;

import model.ApiKey;

import java.util.HashMap;
import java.util.Map;

public class ApiRegistry {
    private final Map<ApiKey, RequestHandler<?, ?>> handlers;

    public ApiRegistry() {
        this.handlers = new HashMap<>();
    }

    public void add(ApiKey apiKey, RequestHandler<?, ?> requestHandler) {
        handlers.put(apiKey, requestHandler);
    }

    public RequestHandler<?, ?> getHandler(ApiKey apiKey) {
        if (handlers.containsKey(apiKey)) {
            return handlers.get(apiKey);
        }

        throw new UnsupportedOperationException(String.format("Api key %s not supported yet", apiKey));
    }
}
