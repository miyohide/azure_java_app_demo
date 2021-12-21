package com.example.demo;

import java.util.Date;

public class Greeting {
    private final long id;
    private final String content;
    private final Date createdAt;
    private final String instanceName;

    public Greeting(long id, String content, Date createdAt, String instanceName) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.instanceName = instanceName;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getInstanceName() {
        return instanceName;
    }
}
