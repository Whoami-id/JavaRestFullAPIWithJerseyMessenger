
package org.java.jersey.messenger.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.java.jersey.messenger.model.Message;
import org.java.jersey.messenger.resource.beans.MessageFilterBean;
import org.java.jersey.messenger.service.MessageService;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    // @GET
    // public List<Message> getMessages(@QueryParam("year") final int year, @QueryParam("start") final int start,
    // @QueryParam("size") final int size) {
    // if (year > 0) {
    // return messageService.getAllMessagesForYear(year);
    // }
    // if (start >= 0 && size >= 0) {
    // return messageService.getAllMessagesPaginated(start, size);
    // }
    // return messageService.getAllMessages();
    // }

    @GET
    public List<Message> getMessages(@BeanParam final MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }
        if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0) {
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") final long id, @Context final UriInfo uriInfo) {
        final Message message = messageService.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComment(uriInfo, message), "comments");
        return message;
    }

    @POST
    public Response addMessage(final Message message, @Context final UriInfo uriInfo) {
        final Message newMessage = messageService.addMessage(message);
        final String newId = String.valueOf(newMessage.getId());
        final URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(newMessage).build();
        // return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") final long id, final Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") final long id) {
        messageService.removeMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

    private String getUriForSelf(final UriInfo uriInfo, final Message message) {
        final String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId()))
                .build().toString();
        return uri;
    }

    private String getUriForProfile(final UriInfo uriInfo, final Message message) {
        final URI uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAutor()).build();
        return uri.toString();
    }

    private String getUriForComment(final UriInfo uriInfo, final Message message) {
        final URI uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource").path(CommentResource.class)
                .resolveTemplate("messageId", message.getId()).build();
        return uri.toString();

    }

}
