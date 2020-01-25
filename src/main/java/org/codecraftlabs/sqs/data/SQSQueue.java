package org.codecraftlabs.sqs.data;

import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.Objects;

public class SQSQueue {
    private String name;
    private String url;

    public SQSQueue(@NonNull String name, @Nonnull String url) {
        this.name = name;
        this.url = url;
    }

    public String url() {
        return url;
    }

    public String name() {
        return name;
    }

    @Override
    public int hashCode() {
        return url.hashCode() + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        SQSQueue instance = (SQSQueue) obj;
        return  Objects.equals(url, instance.url) && Objects.equals(name, instance.name);
    }

    @Override
    public String toString() {
        return "{" +
                "\"key\":\"" + url() + "\", " +
                "\"name\":\"" + name() + "\"" +
                "\"}";
    }
}
