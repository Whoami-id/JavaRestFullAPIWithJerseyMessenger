
package org.java.jersey.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {

    private long id;
    private String message;
    private Date created;
    private String autor;
    private Map<Long, Comment> comments = new HashMap<>();
    private List<Link> links = new ArrayList<>();

    public Message() {

    }

    public Message(final long id, final String message, final String autor) {
        this.id = id;
        this.message = message;
        this.created = new Date();
        this.autor = autor;
    }

    public void addLink(final String url, final String rel) {
        final Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(final String autor) {
        this.autor = autor;
    }

    @XmlTransient // ignoring
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(final Map<Long, Comment> comments) {
        this.comments = comments;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(final List<Link> links) {
        this.links = links;
    }

}
