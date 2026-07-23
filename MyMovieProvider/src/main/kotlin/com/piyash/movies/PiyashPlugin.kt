package com.piyash.movies

import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin
import android.content.Context

@CloudstreamPlugin
class PiyashPlugin: Plugin() {
    override fun load(context: Context) {
        // আপনার প্রোভাইডারটি রেজিস্টার করা হলো
        registerMainAPI(PiyashProvider())
    }
}
