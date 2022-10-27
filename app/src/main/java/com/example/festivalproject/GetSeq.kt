package com.example.festivalproject

import android.content.ClipData
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.http.Header
import java.net.Inet4Address
import java.net.URL

@Xml(name = "response")
data class GetSeq(
    @Element(name = "msgBody")
    val msgBody: MsgBodyBySeq,
)

@Xml(name = "msgBody")
data class MsgBodyBySeq(
    @Element(name = "perforInfo")
    val perforInfo: PerforInfo,
)

@Xml
data class PerforInfo(
    @PropertyElement(name = "seq")
    var seq: String?,

    @PropertyElement(name = "title")
    var title: String?,

    @PropertyElement(name = "startDate")
    var startDate: String?,

    @PropertyElement(name = "endDate")
    var endDate: String?,

    @PropertyElement(name = "place")
    var place: String?,

    @PropertyElement(name = "area")
    var area: String?,

    @PropertyElement(name = "subTitle")
    var subTitle: String?,

    @PropertyElement(name = "price")
    var price: String?,

    @PropertyElement(name = "contents1")
    var contents1: String?,

    @PropertyElement(name = "contents2")
    var contents2: String?,

    @PropertyElement(name = "phone")
    var phone: String?,

    @PropertyElement(name = "url")
    var url:String?,

    @PropertyElement(name = "imgUrl")
    var imgUrl: String?,

    @PropertyElement(name = "placeUrl")
    var placeUrl: String?,

    @PropertyElement(name = "placeAddr")
    var placeAddr: String?

) {
    constructor() : this(null, null,null, null, null, null, null, null,null,null,null,null,null,null,null)
}
