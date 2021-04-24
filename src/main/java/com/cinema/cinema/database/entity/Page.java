package com.cinema.cinema.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data

public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pageId;
    private String name;
    private Date creationDate;
    private String description;
    private String mainImage;
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_seoId")
    private Seo seo;
    @OneToMany(mappedBy = "page",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PageImage> images = new ArrayList<>();

    public Page() {
    }

    public Page(String name, Date creationDate, String description, boolean active,Seo seo) {
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
        this.active = active;
        this.seo = seo;
    }
    public void addPageImage(PageImage pageImage){
        images.add(pageImage);
        pageImage.setPage(this);
    }

}
