package com.bn.taipeitravelinfo.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attraction(
    val id: Int,
    val name: String,
    val address: String,
    val introduction: String,
    val tel: String,
    val url: String,
    val ticket: String,
    @Json(name = "official_site") val officialSite: String,
    val facebook: String,
    val remind: String,
    val images: List<ImageSource>,
): Parcelable {
    val localTel = tel.let {
        if (it.startsWith(TAIWAN_CALLING_CODE)) {
            it.replace("-", "").replace(TAIWAN_CALLING_CODE, "0")
        } else it
    }

    @Parcelize
    data class ImageSource(val src: String): Parcelable
    companion object {
        private const val TAIWAN_CALLING_CODE = "+886"
    }
}

/*
{
            "id": 243,
            "name": "彩虹橋",
            "name_zh": null,
            "open_status": 1,
            "introduction": "彩虹橋全長167公尺，是連接內湖區及松山區，橫跨基隆河的一座橋樑。造型特殊，由橋中央的四條主要鋼纜吊索支撐，橋的結構為S型的曲線橋體，鮮紅色鋼肋拱型梁結構，附屬設施為木質欄杆、粉紅及淡藍色地磚，為一座新穎的地標性公共設施。\r\n\r\n彩虹橋現只開放人及腳踏車通行，不開放汽機車通行，不時可見行人悠閒漫步，也是往返內湖、饒河街夜市及松山火車站的捷徑橋梁，大大縮短了松山及內湖區居民互通的時間。\r\n\r\n不論是白天還是夜晚，彩虹橋皆有不同的美。尤其是入夜後，彩虹橋上燈影投射，為臺北的夜景增添不少美麗，成為不少攝影愛好者捕捉臺北夜景的好題材。另外彩虹橋亦設置藍色公路終點站碼頭，吸引更多遊河觀光人潮，成為臺北市另一個觀光新地標。",
            "open_time": "開放空間",
            "zipcode": "105",
            "distric": "松山區",
            "address": "105 臺北市松山區連結饒河街與內湖區新明路",
            "tel": "+886-2-27258149",
            "fax": "",
            "email": "",
            "months": "01,07,02,08,03,09,04,10,05,11,06,12",
            "nlat": 25.05215,
            "elong": 121.57665,
            "official_site": "",
            "facebook": "",
            "ticket": "",
            "remind": "",
            "staytime": "",
            "modified": "2022-07-12 15:31:13 +08:00",
            "url": "https://www.travel.taipei/zh-tw/attraction/details/243",
            "category": [
                {
                    "id": 12,
                    "name": "單車遊蹤"
                },
                {
                    "id": 16,
                    "name": "戶外踏青"
                },
                {
                    "id": 17,
                    "name": "藍色水路"
                },
                {
                    "id": 19,
                    "name": "親子共遊"
                }
            ],
            "target": [
                {
                    "id": 61,
                    "name": "親子共學"
                },
                {
                    "id": 62,
                    "name": "校外教學"
                },
                {
                    "id": 63,
                    "name": "單車族"
                },
                {
                    "id": 66,
                    "name": "健行族"
                }
            ],
            "service": [
                {
                    "id": 146,
                    "name": "公廁"
                }
            ],
            "friendly": [],
            "images": [
                {
                    "src": "https://www.travel.taipei/image/65047",
                    "subject": "",
                    "ext": ".jpg"
                }
                */