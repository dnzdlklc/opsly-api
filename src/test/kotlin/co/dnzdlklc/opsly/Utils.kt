package co.dnzdlklc.opsly

import co.dnzdlklc.opsly.dto.FBResponse
import co.dnzdlklc.opsly.dto.InstagramResponse
import co.dnzdlklc.opsly.dto.SocialResponse
import co.dnzdlklc.opsly.dto.TwitterResponse

/**
 * Created by denizdalkilic on 2020-06-04 @ 22:25.
 */
open class Utils {
    open fun generateSampleResponse() : SocialResponse {
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

    open fun generateSampleFBResponse() : List<FBResponse> {
        var facebookResponse = FBResponse("FBTest", "FBTest")
        var facebookResponse2 = FBResponse("FBTest2", "FBTest2")

        return listOf(facebookResponse, facebookResponse2)
    }

    open fun generateSampleTwitterResponse() : List<TwitterResponse> {
        var twitterResponse = TwitterResponse("TwitterResponse", "TwitterResponse")
        var twitterResponse2 = TwitterResponse("TwitterResponse2", "TwitterResponse2")

        return listOf(twitterResponse, twitterResponse2)
    }

    open fun generateSampleInstaResponse() : List<InstagramResponse> {
        var instagramResponse = InstagramResponse("InstagramResponse", "InstagramResponse")
        var instagramResponse2 = InstagramResponse("InstagramResponse2", "InstagramResponse2")

        return listOf(instagramResponse, instagramResponse2)
    }
}