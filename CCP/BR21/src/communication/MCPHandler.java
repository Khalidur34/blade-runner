package communication;

import org.json.simple.JSONObject;

interface MCPHandler {
    void handle(JSONObject json);
}

class AKINHandler implements MCPHandler {
    @Override
    public void handle(JSONObject json) {
    }
}

class AKSTHandler implements MCPHandler {
    @Override
    public void handle(JSONObject json) {
    }
}

class STRQHandler implements MCPHandler {
    @Override
    public void handle(JSONObject json) {
    }
}

class EXECHandler implements MCPHandler {
    @Override
    public void handle(JSONObject json) {
    }
}

class NOIPHandler implements MCPHandler {
    @Override
    public void handle(JSONObject json) {
    }
}
