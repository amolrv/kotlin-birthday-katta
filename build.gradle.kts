plugins {
    kotlin("jvm") version "1.5.30"
}

group = "com.amolrv"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    val arrow_version = "0.13.2"
    implementation("io.arrow-kt:arrow-core:$arrow_version")
    implementation("io.arrow-kt:arrow-optics:$arrow_version")
    implementation("io.arrow-kt:arrow-fx-coroutines:$arrow_version")

    implementation("com.sun.mail:javax.mail:1.6.2")
    implementation("io.github.microutils:kotlin-logging:1.7.7")
    implementation("org.slf4j:slf4j-simple:1.7.26")

    val kotest = "4.6.2"
    testImplementation("io.kotest:kotest-runner-junit5:$kotest") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:$kotest") // for kotest core jvm assertions
    testImplementation("com.github.kirviq:dumbster:1.7.1")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
