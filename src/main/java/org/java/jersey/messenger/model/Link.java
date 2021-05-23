
package org.java.jersey.messenger.model;

public class Link {

    private String link;
    private String rel;

    public Link() {

    }

    public String getLink() {
        return link;
    }

    public String getRel() {
        return rel;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public void setRel(final String rel) {
        this.rel = rel;
    }

}
