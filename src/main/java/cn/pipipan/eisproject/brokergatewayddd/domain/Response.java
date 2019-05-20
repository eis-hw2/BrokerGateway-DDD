package cn.pipipan.eisproject.brokergatewayddd.domain;

public class Response<T> {
    private T body;
    private int status;
    private String description;

    public Response(){

    }

    public Response(T body, int status, String description) {
        this.body = body;
        this.status = status;
        this.description = description;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
