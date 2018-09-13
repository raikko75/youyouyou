package com.training.spring.bigcorp.model;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Transactional
public class Captor {

 private Site site;
    /**
     * Captor id
     */
    private String id = UUID.randomUUID().toString();

    /**
     * Captor name
     */
    private String name;

    public PowerSource powerSource;
    @Deprecated
    public Captor() {
        // Use for serializer or deserializer
    }

    /**
     * Constructor to use with required property
     * @param name
     */
    public Captor(String name,PowerSource powerSource) {
        this.name = name;
        this.powerSource= powerSource;
    }

    public Captor(Site site, String id, String name, PowerSource powerSource) {
        this.site = site;
        this.id = id;
        this.name = name;
        this.powerSource = powerSource;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Captor site = (Captor) o;
        return Objects.equals(name, site.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Captor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
