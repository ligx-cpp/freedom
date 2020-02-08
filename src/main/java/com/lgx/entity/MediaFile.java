package com.lgx.entity;

import java.util.Date;

public class MediaFile {
    private String id;
    private String medianame;
    private Date createtime;
    private String mediasize;
    private int broadcnt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedianame() {
        return medianame;
    }

    public void setMedianame(String medianame) {
        this.medianame = medianame;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getMediasize() {
        return mediasize;
    }

    public void setMediasize(String mediasize) {
        this.mediasize = mediasize;
    }

    public int getBroadcnt() {
        return broadcnt;
    }

    public void setBroadcnt(int broadcnt) {
        this.broadcnt = broadcnt;
    }

    @Override
    public String toString() {
        return "MediaFile{" +
                "id='" + id + '\'' +
                ", medianame='" + medianame + '\'' +
                ", createtime=" + createtime +
                ", mediasize='" + mediasize + '\'' +
                ", broadcnt=" + broadcnt +
                '}';
    }
}
