package com.hanson.mockito.entity;

/**
 * @author hanson
 * @date 2024/3/22 15:26
 */
public class User {
    private String name;
    private String phone;
    private String repId;

    public User() {
    }

    public User(String name, String phone, String repId) {
        this.name = name;
        this.phone = phone;
        this.repId = repId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }
}
