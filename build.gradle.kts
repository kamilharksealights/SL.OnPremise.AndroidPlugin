plugins {
    id("java-gradle-plugin")
    id("maven-publish")
}

group="io.sealights.android"
version="3.0.0"

repositories {
    google()
    jcenter()
}

dependencies {
    implementation("com.android.tools.build:gradle:4.0.1")
    implementation("com.android.tools.build:gradle-api:4.0.1")
}

gradlePlugin {
    plugins {
        create("sealights") {
            id = "sealights-android"
            implementationClass = "io.sealights.android.SLPlugin"
        }
    }
}