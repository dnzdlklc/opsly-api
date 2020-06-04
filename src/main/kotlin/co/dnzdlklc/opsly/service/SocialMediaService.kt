package co.dnzdlklc.opsly.service

import co.dnzdlklc.opsly.dto.FBResponse
import co.dnzdlklc.opsly.dto.InstagramResponse
import co.dnzdlklc.opsly.dto.TwitterResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import kotlin.properties.Delegates

/**
 * Created by denizdalkilic on 2020-06-01 @ 14:25.
 */
@Service
class SocialMediaService() {
    var baseURL : String by Delegates.notNull()

    init {
        this.baseURL = "https://takehome.io"
    }

    constructor(baseURL: String) : this() {
        this.baseURL = baseURL
    }

    private val logger = LoggerFactory.getLogger(javaClass)

    fun fetchTwitter(): Flux<TwitterResponse> = fetch("/twitter").bodyToFlux(TwitterResponse::class.java)
    fun fetchFacebook(): Flux<FBResponse> = fetch("/facebook").bodyToFlux(FBResponse::class.java)
    fun fetchInstagram(): Flux<InstagramResponse> = fetch("/instagram").bodyToFlux(InstagramResponse::class.java)

    private fun fetch(path: String): WebClient.ResponseSpec {
        logger.info("Retrieving data from path :$path")
        return WebClient.create(baseURL)
                .get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
    }
}