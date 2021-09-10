package com.amolrv.birthdaykata

import arrow.fx.coroutines.bracket
import java.io.BufferedReader
import java.io.File

suspend fun readEmployeeFile(fileName: String): List<ValidationResult<Employee>> =
    bracket({ File(fileName).bufferedReader() }, readFile, { it.close() })

val readFile: suspend (BufferedReader) -> List<ValidationResult<Employee>> = { br: BufferedReader ->
    br.readLines().drop(1).map(employeeParser)
}

val employeeParser: (String) -> ValidationResult<Employee> = { row ->
    val parts = row.split(", ")
    val lastName = parts.getOrNull(0)
    val firstName = parts.getOrNull(1)
    val dateOfBirth = parts.getOrNull(2)
    val email = parts.getOrNull(3)
    Employee(firstName, lastName, dateOfBirth, email)
}

val double: (Int) -> Int = { it * 2 }

val doubleList = { xs: List<Int> -> xs.map(double) }
