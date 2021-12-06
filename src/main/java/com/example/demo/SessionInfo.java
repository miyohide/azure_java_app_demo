package com.example.demo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class SessionInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  private long id;
  private String name;
  private Date createdAt;
  private String instanceName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getInstanceName() {
    return instanceName;
  }

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }

  @Override
  public String toString() {
    return "SessionInfo id=[" + id + "], instanceName=[" + instanceName + "], name=[" + name + "], createdAt=[" + createdAt
        + "]";
  }
}
