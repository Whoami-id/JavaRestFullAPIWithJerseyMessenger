
package org.java.jersey.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.java.jersey.messenger.database.DatabaseClass;
import org.java.jersey.messenger.exception.DataNotFoundException;
import org.java.jersey.messenger.model.Message;

public class MessageService {

    private final Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1, "Hallo World", "Janatan"));
        messages.put(2L, new Message(2, "Hallo Jersey", "Janatan"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(final int year) {
        final List<Message> messageForYear = new ArrayList<>();
        final Calendar cal = Calendar.getInstance();
        for (final Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                messageForYear.add(message);
            }
        }
        return messageForYear;
    }

    public List<Message> getAllMessagesPaginated(final int start, final int size) {
        final ArrayList<Message> list = new ArrayList<>(messages.values());
        return list.subList(start, size);
    }

    public Message getMessage(final long id) {
        final Message message = messages.get(id);
        if (message == null) {
            throw new DataNotFoundException("Message with id " + id + " not found");
        }
        return message;
    }

    public Message addMessage(final Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(final Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(final long id) {
        return messages.remove(id);

    }
}
