package dev.shushant.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.shushant.designsystem.theme.DynamicAsyncImage
import dev.shushant.model.Article


/**
 * A composable that represents a news item card in the dashboard screen.
 *
 * @param article The [Article] object representing the news item.
 * @param onPostClick The lambda function to be invoked when the news item card is clicked.
 */

@Composable
fun NewsItem(article: Article, onPostClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp)
            .testTag(article.title),
        shape = RoundedCornerShape(16.dp),
        onClick = { onPostClick.invoke(article.url) },
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                DynamicAsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = article.title,
                    imageUrl = article.urlToImage ?: "",
                    placeholder = painterResource(id = R.drawable.baseline_error_24)
                )
                AssistChip(
                    onClick = {},
                    label = { Text(text = article.source.name, textAlign = TextAlign.Center) },
                    modifier = Modifier
                        .align(
                            Alignment.TopEnd
                        )
                        .testTag(article.source.name)
                        .padding(end = 10.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_news_icon),
                            contentDescription = article.source.name,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors()
                        .copy(containerColor = MaterialTheme.colorScheme.onSecondary)
                )
            }
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .testTag(article.title)
            )
            val annotatedString = buildAnnotatedString {
                append(article.desc)
                withStyle(style = SpanStyle(Color.Magenta)) {
                    append(stringResource(R.string.read_more))
                }
            }
            Text(
                text = annotatedString,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .testTag(article.desc.toString())
            )


            Text(
                text = "Published At: ${article.date}",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .testTag(article.date)
            )

            AssistChip(
                onClick = {},
                label = { Text(text = article.authorName ?: stringResource(R.string.unknown)) },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = article.author)
                },
                modifier = Modifier
                    .padding(start = 10.dp)
                    .testTag(article.authorName.toString()),
                colors = AssistChipDefaults.assistChipColors()
                    .copy(containerColor = MaterialTheme.colorScheme.onSecondary)
            )
        }
    }
}