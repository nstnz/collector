package com.nstnz.collector.common.design.image

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import com.nstnz.collector.common.toImageBitmap
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

@Composable
internal fun AsyncImage(
    url: String,
    contentDescription: String? = "",
    modifier: Modifier = Modifier
) {
    if (!url.startsWith("http")) {
        return
    }

    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    image?.let {
        androidx.compose.foundation.Image(
            modifier = modifier,
            bitmap = it,
            contentDescription = contentDescription
        )
    } ?: run {
        Spacer(modifier = modifier)
    }
    LaunchedEffect(key1 = url, block = {
        val bytes: ByteArray = HttpClient().use { client ->
            client.get(url).body()
        }
        image = bytes.toImageBitmap()
    })
}