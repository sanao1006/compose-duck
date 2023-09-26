// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    id("org.jetbrains.dokka").version("1.7.10")
    id("io.github.gradle-nexus.publish-plugin").version("1.1.0")
}
true // Needed to make the Suppress annotation work for the plugins block

tasks.register("clean") {
    doFirst {
        delete(rootProject.buildDir)
    }
}

extra["signing.keyId"] = ""
extra["signing.password"] = ""
extra["signing.key"] = ""
extra["ossrhUsername"] = ""
extra["ossrhPassword"] = ""
extra["sonatypeStagingProfileId"] = ""

val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    val properties = java.util.Properties().apply {
        load(secretPropsFile.inputStream())
    }
    properties.forEach { name, value -> extra[name as String] = value }
} else {
    extra["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
    extra["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
    extra["sonatypeStagingProfileId"] = System.getenv("SONATYPE_STAGING_PROFILE_ID")
    extra["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    extra["signing.password"] = System.getenv("SIGNING_PASSWORD")
    extra["signing.key"] = System.getenv("SIGNING_KEY")
}

nexusPublishing.repositories.sonatype {
    stagingProfileId.set(extra["sonatypeStagingProfileId"] as String)
    username.set(extra["ossrhUsername"] as String)
    password.set(extra["ossrhPassword"] as String)
    nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
    snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
}
