package shared.messages.response;

import shared.messages.BaseMessage;

import java.util.List;

public class ResponseStart implements BaseMessage {
    private List<Integer> id;
    private List<String> name;
    private List<Boolean> ready;

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public void addId(int id) {
        this.id.add(id);
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public void addName(String name) {
        this.name.add(name);
    }

    public List<Boolean> getReady() {
        return ready;
    }

    public void setReady(List<Boolean> ready) {
        this.ready = ready;
    }

    public void addReady(boolean ready) {
        this.ready.add(ready);
    }
}
