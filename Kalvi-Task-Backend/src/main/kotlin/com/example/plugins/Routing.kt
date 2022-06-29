package com.example.plugins

import com.example.models.KalviInput
import com.example.models.KalviOutput
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Kalvi API is UP :)")
        }

        post("/run") {
            val inputGiven = call.receive<KalviInput>()
            val code = inputGiven.code
            when (val language = inputGiven.language) {
                "python" -> {
                    withContext(Dispatchers.IO) {
                        val processBuilder = ProcessBuilder("venv/bin/python", "main.py", code)
                        val process = processBuilder.start()
                        val exitCode = process.waitFor()

                        if (exitCode == 0) {
                            val output = String(process.inputStream.readBytes())
                            if (output == "") {
                                call.respond(
                                    status = HttpStatusCode.OK,
                                    message = KalviOutput(
                                        success = true,
                                        output = "Code Executed Successfully.",
                                        language = language,
                                        timestamp = System.currentTimeMillis()
                                    )
                                )
                            } else {
                                call.respond(
                                    status = HttpStatusCode.OK,
                                    message = KalviOutput(
                                        success = true,
                                        output = output,
                                        language = language,
                                        timestamp = System.currentTimeMillis()
                                    )
                                )
                            }
                        } else {
                            val output = String(process.errorStream.readBytes())
                            if (output == "") {
                                call.respond(
                                    status = HttpStatusCode.OK,
                                    message = KalviOutput(
                                        success = false,
                                        output = "Something Went Wrong.",
                                        language = language,
                                        timestamp = System.currentTimeMillis()
                                    )
                                )
                            } else {
                                call.respond(
                                    status = HttpStatusCode.OK,
                                    message = KalviOutput(
                                        success = false,
                                        output = output,
                                        language = language,
                                        timestamp = System.currentTimeMillis()
                                    )
                                )
                            }
                        }
                    }
                }
                "kotlin" -> {
                    withContext(Dispatchers.IO) {
                        call.respond(
                            status = HttpStatusCode.OK,
                            message = KalviOutput(
                                success = true,
                                output = "Kotlin support coming soon.",
                                language = language,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                    }
                }
                "java" -> {
                    withContext(Dispatchers.IO) {
                        call.respond(
                            status = HttpStatusCode.OK,
                            message = KalviOutput(
                                success = true,
                                output = "Java support coming soon.",
                                language = language,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                    }
                }
                "cpp" -> {
                    withContext(Dispatchers.IO) {
                        call.respond(
                            status = HttpStatusCode.OK,
                            message = KalviOutput(
                                success = true,
                                output = "C++ support coming soon.",
                                language = language,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                    }
                }
            }
        }
    }
}
