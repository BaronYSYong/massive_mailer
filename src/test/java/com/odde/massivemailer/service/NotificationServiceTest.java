package com.odde.massivemailer.service;

import com.odde.massivemailer.model.Notification;
import com.odde.massivemailer.service.impl.NotificationServiceSqlite;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class NotificationServiceTest {
    private NotificationService service;

    @Before
    public void setUp() {
        service = new NotificationServiceSqlite();
    }

    @Test
    public void NotificationMustBeSaved() {
        Notification notification = new Notification();
        notification.setSubject("Subject");
        notification.setNotificationId(123456789);

        Notification savedNotification = service.save(notification);

        assertNotNull(savedNotification);
        assertNotNull(savedNotification.getId());

        assertThat(savedNotification.getSubject(), is("Subject"));
        assertThat(savedNotification.getNotificationId(), is(123456789));
    }
}