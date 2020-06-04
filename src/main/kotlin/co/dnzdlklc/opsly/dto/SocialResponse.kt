package co.dnzdlklc.opsly.dto

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by denizdalkilic on 2020-06-01 @ 14:21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SocialResponse(var twitter: List<TwitterResponse>? = null, var facebook: List<FBResponse>? = null, var instagram: List<InstagramResponse>? = null, var error: String? = null){
}