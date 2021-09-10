package com.amolrv.birthdaykata

import java.util.Properties
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

fun Session.sendMessage(emailMessage: EmailMessage): Unit =
    this.createMessage(emailMessage)
        .let { Transport.send(it) }

fun Session.createMessage(emailMessage: EmailMessage): Message =
    MimeMessage(this).apply {
        setFrom(emailMessage.from.toInternetAddress())
        setRecipient(Message.RecipientType.TO, emailMessage.to.toInternetAddress())
        subject = emailMessage.subject
        setText(emailMessage.message)
    }

private fun EmailAddress.toInternetAddress() = InternetAddress(email)

fun buildSession(host: String, port: Int): Session =
    Properties().apply {
        put("mail.smtp.host", host)
        put("mail.smtp.port", port.toString())
    }.let { Session.getInstance(it, null) }
