package com.cj.smartworker.fcm

import com.cj.smartworker.annotation.ExternalAdapter
import com.cj.smartworker.business.fcm.port.out.FcmPushPort
import com.cj.smartworker.fcm.dto.FcmMessage
import com.cj.smartworker.fcm.dto.Message
import com.cj.smartworker.fcm.dto.Notification
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.auth.oauth2.GoogleCredentials
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient

@ExternalAdapter
internal class FcmAdapter(
    private val objectMapper: ObjectMapper,
    private val restClient: RestClient,
    @Value("\${fcm.project-id}")
    private val projectId: String,
): FcmPushPort {
    private val apiKey = "https://fcm.googleapis.com/v1/projects/$projectId/messages:send"

    companion object {
        private const val FILE_BASE_CONFIG_PATH = "firebase/smartworker-78dd9-firebase-adminsdk-fy0l2-687218fd65.json"
        private val SYSTEM_RESOURCE_AS_STREAM = ClassLoader.getSystemResourceAsStream(FILE_BASE_CONFIG_PATH)
        private var GOOGLE_CREDENTIALS = GoogleCredentials.fromStream(SYSTEM_RESOURCE_AS_STREAM)
            .createScoped(listOf("https://www.googleapis.com/auth/cloud-platform"))
        private const val MEDIA_TYPE = "application/json; charset=utf-8"
        private const val IMAGE = "data:image/jpeg;base64,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"
    }

    val accessToken: String
        get() {
            GOOGLE_CREDENTIALS.refreshIfExpired()
            return GOOGLE_CREDENTIALS.accessToken.tokenValue
        }

    /**
     * if return is true then message is sent successfully
     */
    override fun sendMessage(targetToken: String, title: String, body: String): Boolean {
        val fcmMessage = FcmMessage(
            message = Message(
                token = targetToken,
                notification = Notification(
                    title = title,
                    body = body,
                    image = IMAGE,
                )
            )
        ).let { objectMapper.writeValueAsString(it) }

        val retrieve = restClient.post()
            .uri(apiKey)
            .accept(MediaType.parseMediaType(MEDIA_TYPE))
            .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
            .retrieve()

        try {
            retrieve.onStatus(HttpStatusCode::is4xxClientError) { _, response ->
                throw RuntimeException("Failed to send message to FCM: ${response.statusCode}")
            }
            retrieve.onStatus(HttpStatusCode::is5xxServerError) { _, response ->
                throw RuntimeException("Failed to send message to FCM: ${response.statusCode}")
            }
        } catch (e: RuntimeException) {
            return false
        }

        return true
    }
}
