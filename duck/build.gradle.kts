@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("org.jetbrains.dokka")
    `maven-publish`
    signing
}

val libraryVersion = "0.5.0"
val libraryName = "compose-duck"
val libraryDescription = "This is a UI library for Jetpack Compose using random duck api."

android {
    namespace = "com.sanao.compose.duck"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.debug)
    implementation(libs.bundles.image)
    implementation(libs.bundles.other)
    implementation(libs.bundles.test)
}


val androidSourcesJar = tasks.register<Jar>("androidSourcesJar") {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}

tasks.dokkaJavadoc {
    outputDirectory.set(File(buildDir, "dokkaJavadoc"))
}

val javadocJar = tasks.register<Jar>("dokkaJavadocJar") {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

artifacts {
    archives(androidSourcesJar)
    archives(javadocJar)
}

signing {
    useInMemoryPgpKeys(
        rootProject.extra["signing.keyId"] as String,
        rootProject.extra["signing.key"] as String,
        rootProject.extra["signing.password"] as String,
    )
    sign(publishing.publications)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "io.github.sanao1006"
                artifactId = libraryName
                version = libraryVersion

                if (project.plugins.hasPlugin("com.android.library")) {
                    from(components["release"])
                } else {
                    from(components["java"])
                }
                artifact(androidSourcesJar)
                artifact(javadocJar)
                pom {
                    name.set(artifactId)
                    description.set(libraryDescription)
                    url.set("https://github.com/sanao1006/compose-duck")

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("sanao1006")
                            name.set("sanao1006")
                            url.set("https://sanao.dev/")
                        }
                    }
                    scm {
                        connection.set("scm:git:github.com/sanao1006/compose-duck.git")
                        developerConnection.set("scm:git:ssh://github.com/sanao1006/compose-duck.git")
                        url.set("github.com/sanao1006/compose-duck")
                    }
                }

            }
        }
    }
}