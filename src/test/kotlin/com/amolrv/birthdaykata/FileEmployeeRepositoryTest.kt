package com.amolrv.birthdaykata

import arrow.core.filterOption
import arrow.core.getOrElse
import arrow.core.identity
import arrow.core.toOption
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/**
 * Integration tests, touches the file system
 */
class FileEmployeeRepositoryTest : StringSpec({

    "all employees are read from a valid CSV file" {
        val allEmployees: List<Employee> = readEmployeeFile("input.txt").filterValidOnly()
        val expectedEmails = listOf("john.doe@foobar.com", "mary.ann@foobar.com").map(::EmailAddress)
        allEmployees.size shouldBe 2
        allEmployees.map { it.emailAddress } shouldBe expectedEmails
    }


    "EmployeeRepositoryException when reading an invalid CSV file" {
        val allEmployees = readEmployeeFile("invalid_csv_input.txt")
        allEmployees.filter { it.isValid }.size shouldBe 2
        allEmployees.filter { it.isInvalid }.size shouldBe 2
        allEmployees.filter { it.isInvalid }
            .map { it.fold(::identity) { emptyList<String>() } }
            .first().size shouldBe 2
    }
})

fun List<ValidationResult<Employee>>.filterValidOnly() =
    map { it.getOrElse { null }.toOption() }.filterOption()
