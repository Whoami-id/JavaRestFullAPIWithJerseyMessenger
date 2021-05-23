
package org.java.jersey.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.java.jersey.messenger.model.Comment;
import org.java.jersey.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

    private final CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") final long messageId) {
        return commentService.getAllCommments(messageId);
    }

    @POST
    public Comment addMessage(@PathParam("messageId") final long messageId, final Comment comment) {
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updataMessage(@PathParam("messageId") final long messageId, @PathParam("commentId") final long id,
            final Comment comment) {
        comment.setId(id);
        return commentService.updataComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") final long messageId,
            @PathParam("commentId") final long commentId) {
        commentService.removeComment(messageId, commentId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getMessage(@PathParam("messageId") final long messageId,
            @PathParam("commentId") final long commentId) {
        return commentService.getComment(messageId, commentId);
    }

}