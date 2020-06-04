package co.dnzdlklc.opsly.controller

import co.dnzdlklc.opsly.dto.FBResponse
import co.dnzdlklc.opsly.dto.InstagramResponse
import co.dnzdlklc.opsly.dto.SocialResponse
import co.dnzdlklc.opsly.dto.TwitterResponse
import co.dnzdlklc.opsly.service.SocialMediaService
import kotlinx.coroutines.reactive.collect
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClientResponseException

/**
 * Created by denizdalkilic on 2020-06-01 @ 13:16.
 */
@RestController
class EndpointController (private val socialMediaService: SocialMediaService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/")
    suspend fun getResponse(): ResponseEntity<SocialResponse> {
        val arrayOfTweets = ArrayList<TwitterResponse>()
        val arrayOfFBResponse = ArrayList<FBResponse>()
        val arrayOfInstagramResponse = ArrayList<InstagramResponse>()

        return try {
            logger.info("Attempting to get data")
            socialMediaService.fetchTwitter().collect { arrayOfTweets.add(it)}.runCatching { arrayOfTweets }
            socialMediaService.fetchFacebook().collect { arrayOfFBResponse.add(it)}.runCatching { arrayOfFBResponse }
            socialMediaService.fetchInstagram().collect { arrayOfInstagramResponse.add(it)}.runCatching { arrayOfInstagramResponse }

            ResponseEntity.ok().body(SocialResponse(arrayOfTweets, arrayOfFBResponse, arrayOfInstagramResponse))
        } catch (e: WebClientResponseException) {
            ResponseEntity.status(e.statusCode).body(SocialResponse(null,null, null, e.responseBodyAsString))
        }
    }
}
