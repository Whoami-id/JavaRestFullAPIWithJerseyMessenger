
package org.java.jersey.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.java.jersey.messenger.database.DatabaseClass;
import org.java.jersey.messenger.model.Comment;
import org.java.jersey.messenger.model.Message;

public class CommentService {

    private final Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllCommments(final long messageId) {
        final Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<>(comments.values());
    }

    public Comment getComment(final long messageId, final long commentId) {
        final Message message = messages.get(messageId);
        if (message == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }
        final Map<Long, Comment> comments = messages.get(messageId).getComments();
        final Comment comment = comments.get(commentId);
        if (comment == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }
        return comment;
    }

    public Comment addComment(final long messageId, final Comment comment) {
        final Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updataComment(final long messageId, final Comment comment) {
        final Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(final long messageId, final long commentId) {
        final Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }

}
