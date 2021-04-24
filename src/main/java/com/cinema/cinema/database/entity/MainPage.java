package com.cinema.cinema.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class MainPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mainPageId;
    private String name;
    private String firstPhone;
    private String secondPhone;
    private String SEOText;
    private Date creationDate;
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;

    public MainPage(String name, String firstPhone, String secondPhone, String SEOText, Date creationDate, boolean active, Seo seo) {
        this.name = name;
        this.firstPhone = firstPhone;
        this.secondPhone = secondPhone;
        this.SEOText = SEOText;
        this.creationDate = creationDate;
        this.active = active;
        this.seo = seo;
    }
}
