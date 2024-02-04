package dev.shushant.dashboard

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

/**
 * A composable that represents a chip for arranging news articles based on the provided [NewsArrangement].
 *
 * @param text The [NewsArrangement] associated with the chip.
 * @param selected The [MutableState] to manage the selected state of the chip.
 * @param onClick The lambda function to be invoked when the chip is clicked.
 */

@Composable
fun ArticleArrangementChip(
    text: NewsArrangement, selected: MutableState<String>, onClick: (NewsArrangement) -> Unit
) {
    FilterChip(
        onClick = {
            selected.value = text.name
            onClick.invoke(text)
        },
        label = {
            Text(text.name)
        },
        selected = selected.value == text.name,
        leadingIcon = if (selected.value == text.name) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = text.name,
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
        modifier = Modifier.testTag(text.name),
    )
}