package by.automation.models;

import java.util.Objects;

public class Tunnel {
    private static int generator = 1;
    private final int id;

    public Tunnel() {
        this.id = generator++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tunnel tunnel = (Tunnel) o;
        return id == tunnel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tunnel{" +
                "id=" + id +
                '}';
    }
}