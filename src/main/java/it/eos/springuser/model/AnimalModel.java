package it.eos.springuser.model;

import java.util.Objects;

public class AnimalModel {

    private long id;

    private String type;

    private String family;

    private String genus;

    @Override
    public String toString() {
        return "AnimalModel{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", family='" + family + '\'' +
                ", genus='" + genus + '\'' +
                ", species='" + species + '\'' +
                '}';
    }

    private String species;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalModel that = (AnimalModel) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(family, that.family) &&
                Objects.equals(genus, that.genus) &&
                Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, family, genus, species);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}