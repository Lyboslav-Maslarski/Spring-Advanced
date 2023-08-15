package com.example.webclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @JsonProperty("data")
    private Data data;
    @JsonProperty("support")
    private Support support;

    public Data getData() {
        return data;
    }

    public User setData(Data data) {
        this.data = data;
        return this;
    }

    public Support getSupport() {
        return support;
    }

    public User setSupport(Support support) {
        this.support = support;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }
}
