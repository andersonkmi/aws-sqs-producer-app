package org.codecraftlabs.sqs.data;

public class SampleData {
    private String id;
    private String name;
    private String programmingLanguage;

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

    @Override
    public String toString() {
        var buffer = "{" +
                "\"id\": \"" + getId() + "\"," +
                "\"name\": \"" + getName() + "\"," +
                "\"programmingLanguage\": \"" + getProgrammingLanguage() + "\"" +
                "}";
        return buffer;
    }
}
