package com.example.animedex;

public class CompletedListRequest {
    private int user_id;

    public CompletedListRequest(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }
}
