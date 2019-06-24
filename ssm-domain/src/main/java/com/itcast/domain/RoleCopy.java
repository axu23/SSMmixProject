package com.itcast.domain;

public class RoleCopy extends Role{
    private Integer tags;

    public Integer getTags() {
        return tags;
    }

    public void setTags(Integer tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "RoleCopy{" +
                "tags=" + tags +
                '}';
    }
}
