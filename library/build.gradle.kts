plugins {
    base
    kotlin("jvm")
}

apply {
    from("$rootDir/gradle/plugins/maven-simple.gradle")
}

configure<JavaPluginConvention> {
    setSourceCompatibility(1.6)
    setTargetCompatibility(1.6)
}

repositories {
    jcenter()
}

val kotlinVersion: String = "1.1.61"

dependencies {
    implementation(kotlin("stdlib", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))
    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.8.0")
    testImplementation("org.jetbrains.spek:spek-api:1.1.2")
    testImplementation("org.jetbrains.spek:spek-junit-platform-engine:1.1.2")
    testImplementation("org.junit.platform:junit-platform-runner:1.0.0-M4")
    testImplementation("com.nhaarman:mockito-kotlin:1.5.0")
}
