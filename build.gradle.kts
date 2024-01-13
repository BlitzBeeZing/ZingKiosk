buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath ("com.android.tools.build:gradle:7.4.2")

        // block:start:assets-class-path
        classpath ("in.juspay:hypersdk.plugin:2.0.6")
    }


    repositories{
        maven { url = uri("https://maven.juspay.in/jp-build-packages/hyper-sdk/")}
        mavenCentral()
        google()


    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        //block:start:hyper-sdk-maven-url
        maven {
            url = uri("https://maven.juspay.in/jp-build-packages/hyper-sdk/")
        }
        //block:end:hyper-sdk-maven-url
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
}
