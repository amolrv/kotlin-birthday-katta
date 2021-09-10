package com.amolrv.birthdaykata

import java.time.LocalDate
import java.time.Month

fun birthdayMessages(employees: List<Employee>, date: LocalDate, sender: EmailAddress): List<EmailMessage> =
    employees.filter { birthDateFilter(date, it.dateOfBirth) }
        .map { emailMessage(sender, it) }

fun emailMessage(sendFrom: EmailAddress, to: Employee) = EmailMessage(
    sendFrom,
    to.emailAddress,
    "Happy Birthday!",
    "Happy birthday, dear ${to.firstName}!"
)

fun birthDateFilter(date: LocalDate, birthday: LocalDate): Boolean =
    if (!date.isLeapYear && date.isFeb28th) birthday.isSameDay(date) || birthday.isFeb29th
    else birthday.isSameDay(date)

fun LocalDate.isSameDay(date: LocalDate): Boolean =
    this.month == date.month && this.dayOfMonth == date.dayOfMonth

private val LocalDate.isFeb28th: Boolean
    get() = this.month == Month.FEBRUARY && this.dayOfMonth == 28

private val LocalDate.isFeb29th: Boolean
    get() = this.month == Month.FEBRUARY && this.dayOfMonth == 29
