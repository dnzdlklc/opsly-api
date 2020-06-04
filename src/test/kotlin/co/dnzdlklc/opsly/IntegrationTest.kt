package co.dnzdlklc.opsly

import co.dnzdlklc.opsly.dto.FBResponse
import co.dnzdlklc.opsly.dto.InstagramResponse
import co.dnzdlklc.opsly.dto.SocialResponse
import co.dnzdlklc.opsly.dto.TwitterResponse
import co.dnzdlklc.opsly.service.SocialMediaService
import com.fasterxml.jackson.databind.ObjectMapper
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.io.IOException


/**
 * Created by denizdalkilic on 2020-06-04 @ 20:48.
 */
class IntegrationTest {

    private var mockBackEnd = MockWebServer()
    private val baseUrl = String.format("http://localhost:%s", mockBackEnd.port)
    private var socialMediaService = SocialMediaService(baseUrl)
    private val MAPPER = ObjectMapper()

    @BeforeAll
    @Throws(IOException::class)
    fun setUp() {
        mockBackEnd.start()
    }

    @AfterAll
    @Throws(IOException::class)
    fun tearDown() {
        mockBackEnd.shutdown()
    }

    @Test
    @Throws(Exception::class)
    fun fbDataFeedShouldCallCorrectURI() {
        val mockFeed = Utils().generateSampleFBResponse()
        mockBackEnd.enqueue(MockResponse().setBody(MAPPER.writeValueAsString(mockFeed)).addHeader("Content-Type", "application/json"))

        val fbFLux: Flux<FBResponse> = socialMediaService.fetchFacebook()

        StepVerifier.create(fbFLux)
                .expectNext(mockFeed[0])
                .expectNext(mockFeed[1])
                .verifyComplete()

        val recordedRequest = mockBackEnd.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assertEquals("/facebook", recordedRequest.path)
    }

    @Test
    @Throws(Exception::class)
    fun twitterDataFeedShouldCallCorrectURI() {
        val mockFeed = Utils().generateSampleTwitterResponse()
        mockBackEnd.enqueue(MockResponse().setBody(MAPPER.writeValueAsString(mockFeed)).addHeader("Content-Type", "application/json"))

        val fbFLux: Flux<TwitterResponse> = socialMediaService.fetchTwitter()

        StepVerifier.create(fbFLux)
                .expectNext(mockFeed[0])
                .expectNext(mockFeed[1])
                .verifyComplete()

        val recordedRequest = mockBackEnd.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assertEquals("/twitter", recordedRequest.path)
    }

    @Test
    @Throws(Exception::class)
    fun instaDataFeedShouldCallCorrectURI() {
        val mockFeed = Utils().generateSampleInstaResponse()
        mockBackEnd.enqueue(MockResponse().setBody(MAPPER.writeValueAsString(mockFeed)).addHeader("Content-Type", "application/json"))

        val fbFLux: Flux<InstagramResponse> = socialMediaService.fetchInstagram()

        StepVerifier.create(fbFLux)
                .expectNext(mockFeed[0])
                .expectNext(mockFeed[1])
                .verifyComplete()

        val recordedRequest = mockBackEnd.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assertEquals("/instagram", recordedRequest.path)
    }

    private fun generateSampleResponse() : SocialResponse {
        var twitterResponse = TwitterResponse("Test", "Test")
        val twitterResponse2 = TwitterResponse("Test2", "Test2")
        var twitterList = listOf<TwitterResponse>(twitterResponse, twitterResponse2)

        var instagramResponse = InstagramResponse("InstaTest", "InstaTest")
        var instagramResponse2 = InstagramResponse("InstaTest2", "InstaTest2")
        var instaList = listOf<InstagramResponse>(instagramResponse, instagramResponse2)

        var facebookResponse = FBResponse("FBTest", "FBTest")
        var facebookResponse2 = FBResponse("FBTest2", "FBTest2")
        var fbList = listOf<FBResponse>(facebookResponse, facebookResponse2)

        return SocialResponse(twitterList, fbList, instaList)
    }

    private fun generateSampleFBResponse() : List<FBResponse> {
        var facebookResponse = FBResponse("FBTest", "FBTest")
        var facebookResponse2 = FBResponse("FBTest2", "FBTest2")

        return listOf(facebookResponse, facebookResponse2)
    }

    private fun generateSampleTwitterResponse() : List<TwitterResponse> {
        var twitterResponse = TwitterResponse("TwitterResponse", "TwitterResponse")
        var twitterResponse2 = TwitterResponse("TwitterResponse2", "TwitterResponse2")

        return listOf(twitterResponse, twitterResponse2)
    }

    private fun generateSampleInstaResponse() : List<InstagramResponse> {
        var instagramResponse = InstagramResponse("InstagramResponse", "InstagramResponse")
        var instagramResponse2 = InstagramResponse("InstagramResponse2", "InstagramResponse2")

        return listOf(instagramResponse, instagramResponse2)
    }
}