import net.modgarden.flowerbed.gradle.Properties

plugins {
	id("net.fabricmc.fabric-loom") version "1.14-SNAPSHOT"
	id("maven-publish")
}

base.archivesName.set(Properties.MOD_ID)
version = "${Properties.MOD_VERSION}+${libs.versions.minecraft.get()}"
group = Properties.GROUP

loom {
	splitEnvironmentSourceSets()
	mods {
		create(Properties.MOD_ID) {
			sourceSet(sourceSets["main"])
			sourceSet(sourceSets["client"])
		}
	}
	accessWidenerPath = projectDir.resolve("src/main/resources/flowerbed.accesswidener")
}

repositories {
	maven("https://maven.parchmentmc.org") {
		name = "ParchmentMC"
	}
	maven("https://maven.terraformersmc.com/") {
		name = "TerraformersMC"
	}
	exclusiveContent {
		forRepository {
			maven {
				name = "Modrinth"
				url = uri("https://api.modrinth.com/maven")
			}
		}
		filter {
			includeGroup("maven.modrinth")
		}
	}
	maven("https://maven.nucleoid.xyz") {
		name = "Nucleoid"
	}
	maven("https://maven.gegy.dev/releases") {
		name = "Gegy"
	}
	maven("https://maven.cassian.cc") {
		name = "Cassian"
	}
}

dependencies {
	minecraft(libs.minecraft)

	implementation(libs.fabric.loader)
	implementation(libs.fabric.api)
	localRuntime(libs.mod.menu)

	// Testing
	localRuntime(libs.player.roles)

	// Fix Mods

	// Libraries
	implementation(libs.fabric.permissions)
}

tasks {
	named<Jar>("jar").configure {
		from(rootProject.file("LICENSE")) {
			rename { "${it}_${Properties.MOD_NAME}" }
		}

	}

	val expandProps = mapOf(
			"mod_version" to Properties.MOD_VERSION,
			"group" to project.group,
			"minecraft_version" to libs.versions.minecraft.get(),
			"fabric_api_version" to libs.versions.fabric.api.get(),
			"fabric_loader_version" to libs.versions.fabric.loader.get(),
			"fabric_minecraft_version_range" to Properties.FABRIC_MINECRAFT_RANGE,
			"fabric_loader_range" to Properties.FABRIC_LOADER_RANGE,
			"mod_name" to Properties.MOD_NAME,
			"mod_author" to Properties.MOD_AUTHOR,
			"fabric_mod_contributors" to Properties.MOD_CONTRIBUTORS.joinToString(separator = "\",\n\t\t\""),
			"mod_id" to Properties.MOD_ID,
			"mod_license" to Properties.LICENSE,
			"mod_description" to Properties.DESCRIPTION,
			"java_version" to Properties.JAVA_VERSION
	)

	val processResourcesTasks = listOf("processResources", "processDatagenResources")

	withType<ProcessResources>().matching { processResourcesTasks.contains(it.name) }.configureEach {
		inputs.properties(expandProps)
		filesMatching(setOf("fabric.mod.json", "*.mixins.json")) {
			expand(expandProps)
		}
		exclude("\\.cache")
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			artifactId = base.archivesName.get()
			from(components["java"])
		}
	}
	repositories {
		maven {
			name = "Greenhouse"
			url = uri("https://maven.greenhouse.house/private")
			credentials {
				username = System.getenv("MAVEN_USERNAME")
				password = System.getenv("MAVEN_PASSWORD")
			}
			authentication {
				create<BasicAuthentication>("basic")
			}
		}
	}
}
