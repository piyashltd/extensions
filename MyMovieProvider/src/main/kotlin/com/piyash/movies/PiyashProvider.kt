package com.piyash.movies

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.Qualities

class PiyashProvider : MainAPI() {
    override var mainUrl = "https://github.com/piyashltd" 
    override var name = "Piyash Collection"
    override val hasMainPage = true
    override var lang = "bn"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // ১. হোমপেজে মুভি শো করানোর লজিক
    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val movies = listOf(
            MovieSearchResponse(
                name = "Piyash Demo Stream",
                url = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8", // ডিরেক্ট টেস্ট লিংক
                apiName = this.name,
                type = TvType.Movie,
                posterUrl = "https://picsum.photos/400/600", // ডামি পোস্টার
            )
        )
        return HomePageResponse(arrayListOf(HomePageList("My Favorites", movies)))
    }

    // ২. মুভিতে ক্লিক করলে রেটিং, স্পয়লার লোড করার লজিক
    override suspend fun load(url: String): LoadResponse {
        return newMovieLoadResponse("Piyash Demo Stream", url, TvType.Movie, url) {
            this.posterUrl = "https://picsum.photos/400/600"
            this.plot = "এটি একটি ডেমো স্পয়লার। আপনার নিজস্ব m3u8 বা mp4 লিংকগুলো ঠিকমতো কাজ করছে কি না, তা চেক করার জন্য এটি তৈরি করা হয়েছে।"
            this.rating = 90 // ৯.০ রেটিং
        }
    }

    // ৩. ভিডিও প্লেয়ারে স্ট্রিম সেন্ড করার লজিক
    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        callback.invoke(
            ExtractorLink(
                source = this.name,
                name = "Direct M3U8 Stream",
                url = data, 
                referer = "",
                quality = Qualities.Unknown.value,
                isM3u8 = true // .mp4 হলে এটি false করে দিতে হবে
            )
        )
        return true
    }
}
