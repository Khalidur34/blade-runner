package communication;

import com.google.gson.JsonObject;

interface MCPHandler {
    void handle(JsonObject json);
}

class AKINHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
    }
}

class AKSTHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
    }
}

class STRQHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
    }
}

class EXECHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
    }
}

class NOIPHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
    }
}
