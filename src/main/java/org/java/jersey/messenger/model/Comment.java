
package org.java.jersey.messenger.model;

import java.util.Date;

public class Comment {

    private long id;
    private String message;
    private Date created;
    private String autor;

    public Comment() {

    }

    public Comment(final long id, final String message, final String autor) {
        this.id = id;
        this.message = message;
        this.created = new Date();
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreated() {
        return created;
    }

    public String getAutor() {
        return autor;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public void setAutor(final String autor) {
        this.autor = autor;
    }

}
