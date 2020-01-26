package org.codecraftlabs.sqs.data;

import java.time.Instant;

public class SampleData {
    private String id;
    private String name;
    private String programmingLanguage;
    private Instant creationDate;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "";
    }
}
