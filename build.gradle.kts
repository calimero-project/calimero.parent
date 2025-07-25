import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
	`java-library`
	id("com.github.ben-manes.versions") version "0.52.0"
	id("com.github.spotbugs") version "6.2.2"
	`maven-publish`
}

val desc = "Calimero composite build"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
	dependsOn(gradle.includedBuilds.map { it.task(":dependencyUpdates") })
}

tasks.named<DefaultTask>("clean") {
	dependsOn(gradle.includedBuilds.map { it.task(":clean") })
}

tasks.named<DefaultTask>("assemble") {
	dependsOn(gradle.includedBuilds.map { it.task(":assemble") })
}

tasks.named<DefaultTask>("publishToMavenLocal") {
	dependsOn(gradle.includedBuilds.map { it.task(":publishToMavenLocal") })
}

tasks.named<Wrapper>("wrapper") {
	gradleVersion = "9.0.0-rc-3"
    dependsOn(gradle.includedBuilds.map { it.task(":wrapper") })
}
