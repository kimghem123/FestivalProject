package com.example.festivalproject

import android.content.ClipData
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.http.Header
import java.net.URL

@Xml(name = "response")
data class GetRealm(
    @Element(name = "msgBody")
    val msgBody: MsgBody,
)

@Xml(name = "msgBody")
data class MsgBody(
    @PropertyElement(name = "totalCount")
    val totalCount: Int,

    @PropertyElement(name = "realmCode")
    val realmCode: String,

    @Element(name = "perforList")
    val perforList: List<PerforList>,
)

@Xml
data class PerforList(
    @PropertyElement(name = "seq")
    var seq: String?,

    @PropertyElement(name = "title")
    var title: String?,

    @PropertyElement(name = "startDate")
    var startDate: String?,

    @PropertyElement(name = "endDate")
    var endDate: String?,

    @PropertyElement(name = "realmName")
    var realmName: String?,

    @PropertyElement(name = "place")
    var place: String?,

    @PropertyElement(name = "area")
    var area: String?,

    @PropertyElement(name = "thumbnail")
    var thumbnail: String?,
) {
    constructor() : this(null, null, null,null, null, null, null, null)
}
