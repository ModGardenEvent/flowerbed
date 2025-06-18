import net.modgarden.flowerbed.gradle.Properties
import net.modgarden.flowerbed.gradle.Versions

plugins {
	id("fabric-loom") version "1.10-SNAPSHOT"
	id("maven-publish")
}

base.archivesName.set(Properties.MOD_ID)
version = "${Versions.MOD}+${Versions.MINECRAFT}"
group = Properties.GROUP

loom {
	splitEnvironmentSourceSets()
	mods {
		create(Properties.MOD_ID) {
			sourceSet(sourceSets["main"])
			sourceSet(sourceSets["client"])
		}
	}
}

repositories {
	maven("https://maven.terraformersmc.com/") {
		name = "TerraformersMC"
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${Versions.MINECRAFT}")
	mappings(loom.officialMojangMappings())

	modImplementation("net.fabricmc:fabric-loader:${Versions.FABRIC_LOADER}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${Versions.FABRIC_API}")
	modLocalRuntime("com.terraformersmc:modmenu:${Versions.MOD_MENU}")
}

tasks {
	named<Jar>("jar").configure {
		from(rootProject.file("LICENSE")) {
			rename { "${it}_${Properties.MOD_NAME}" }
		}

	}

	val expandProps = mapOf(
			"mod_version" to Versions.MOD,
			"group" to project.group,
			"minecraft_version" to Versions.MINECRAFT,
			"fabric_api_version" to Versions.FABRIC_API,
			"fabric_loader_version" to Versions.FABRIC_LOADER,
			"fabric_minecraft_version_range" to Versions.FABRIC_MINECRAFT_RANGE,
			"fabric_loader_range" to Versions.FABRIC_LOADER_RANGE,
			"mod_name" to Properties.MOD_NAME,
			"mod_author" to Properties.MOD_AUTHOR,
			"fabric_mod_contributors" to Properties.MOD_CONTRIBUTORS.joinToString(separator = "\",\n\t\t\""),
			"mod_id" to Properties.MOD_ID,
			"mod_license" to Properties.LICENSE,
			"mod_description" to Properties.DESCRIPTION,
			"java_version" to Versions.JAVA
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
