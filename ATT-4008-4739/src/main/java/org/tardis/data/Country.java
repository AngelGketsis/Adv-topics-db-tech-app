package org.tardis.data;

import jakarta.persistence.*;

@Entity
@Table(name = "Countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ISO3", columnDefinition = "char", unique = true, nullable = false)
    private char[] ISO3;

    @Column(name = "name", nullable = false)
    private String name;

    public Country() {
    }

    public Country(char[] ISO3, String name) {
        this.ISO3 = ISO3;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char[] getISO3() {
        return ISO3;
    }

    public void setISO3(char[] ISO3) {
        this.ISO3 = ISO3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
