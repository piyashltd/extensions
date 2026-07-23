package recloudstream

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.Qualities

class TwitchProvider : MainAPI() {
    override var mainUrl = "https://github.com/piyashltd"
    override var name = "Piyash Collection"
    override val hasMainPage = true
    override var lang = "bn"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val movies = listOf(
            MovieSearchResponse(
                name = "Piyash Demo Stream",
                url = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
                apiName = this.name,
                type = TvType.Movie,
                posterUrl = "https://picsum.photos/400/600",
            )
        )
        return HomePageResponse(arrayListOf(HomePageList("My Favorites", movies)))
    }

    override suspend fun load(url: String): LoadResponse {
        return newMovieLoadResponse("Piyash Demo Stream", url, TvType.Movie, url) {
            this.posterUrl = "https://picsum.photos/400/600"
            this.plot = "ডেমো স্পয়লার। আপনার নিজস্ব m3u8 লিংক ঠিকমতো কাজ করছে।"
            this.rating = 90
        }
    }

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
                isM3u8 = true
            )
        )
        return true
    }
}
