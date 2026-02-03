
plugins {
    kotlin("jvm") version libs.versions.kotlin
    id("com.ncorti.ktfmt.gradle") version libs.versions.ktfmt
    application

}

group = "com.vasiniyo"
version = "1.0"
ktfmt {
    kotlinLangStyle()
}
kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

application {
    mainClass = "com.vasiniyo.app.ApplicationKt"
}
dependencies {
    implementation(libs.telegram.bot)
    implementation(libs.chesslib)
    implementation(libs.okhttp)
    implementation(libs.jackson.kotlin)
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.serialization.json)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.batik.transcoder)
    implementation(libs.batik.codec)
    testImplementation(libs.junit)
}

tasks.test {
    useJUnitPlatform()
}
