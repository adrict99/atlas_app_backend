package com.adrict99.atlas_app_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AtlasBackendApplication

fun main(args: Array<String>) {
	runApplication<AtlasBackendApplication>(*args)
	println("I'm working lol")
}
