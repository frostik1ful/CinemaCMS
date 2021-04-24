package com.cinema.cinema.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
public class PageImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pageImageId;
    private String image;
    @ManyToOne
    @JoinColumn(name = "pageId")
    private Page page;

    public PageImage() {
    }

    public PageImage(String image) {
        this.image = image;
    }

    public long getPageImageId() {
        return pageImageId;
    }

    public void setPageImageId(long pageImageId) {
        this.pageImageId = pageImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
