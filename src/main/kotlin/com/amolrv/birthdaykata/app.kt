package com.amolrv.birthdaykata

import arrow.core.filterOption
import java.time.LocalDate
import javax.mail.Session

suspend fun main() {
    val env = buildSession("localhost", 8080)
    env.sendGreetingsUseCase(
        date = LocalDate.now(),
        file = "input.txt",
        sender = EmailAddress("birthday@corp.com")
    )
}

suspend fun Session.sendGreetingsUseCase(date: LocalDate, file: String, sender: EmailAddress): Unit {
    val result = readEmployeeFile(file)
    result
        .map { it.toOption() }
        .filterOption() // or log errors
        .let { birthdayMessages(it, date, sender) }
        .forEach { sendMessage(it) }
}
