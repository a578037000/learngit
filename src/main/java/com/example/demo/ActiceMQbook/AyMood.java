package com.example.demo.ActiceMQbook;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="ay_mood")
public class AyMood implements Serializable {
  @Id
  public String id;
  private String content;
  private String userId;
  private int praiseNum;
  private java.sql.Timestamp publishTime;

  public AyMood() {
  }

  public AyMood(String id, String content, String userId, int praiseNum, Timestamp publishTime) {
    this.id = id;
    this.content = content;
    this.userId = userId;
    this.praiseNum = praiseNum;
    this.publishTime = publishTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getPraiseNum() {
    return praiseNum;
  }

  public void setPraiseNum(int praiseNum) {
    this.praiseNum = praiseNum;
  }

  public Timestamp getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(Timestamp publishTime) {
    this.publishTime = publishTime;
  }
}
