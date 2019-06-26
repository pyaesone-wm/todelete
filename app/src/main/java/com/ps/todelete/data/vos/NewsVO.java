package com.ps.todelete.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pyaesone on 2019-06-23
 */
public class NewsVO {
    @SerializedName("source")
    private SourceVO source;

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String newsUrl;

    @SerializedName("urlToImage")
    private String newsImage;

    @SerializedName("publishedAt")
    private String publishedDate;

    @SerializedName("content")
    private String content;

    private int publishedDateOrder = getPublishedDateOrder();

    public NewsVO() {
    }

    public SourceVO getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getContent() {
        return content;
    }

    public int getPublishedDateOrder() {
        if (publishedDate != null && !publishedDate.isEmpty()) {
            publishedDateOrder = Integer.parseInt(publishedDate.split("T")[0].replace("-", ""));
        }

        return publishedDateOrder;
    }
}
