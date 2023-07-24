package com.example.yumyum.presentation.category_list.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.yumyum.domain.model.Category

@Composable
fun SingleCategoryItem(
    categoryItem: Category,
    onCategoryItemClick: (String) -> Unit
) {

    Card(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { onCategoryItemClick(categoryItem.strCategory) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(Modifier.fillMaxSize()) {
            AsyncImage(
                model = categoryItem.strCategoryThumb,
                contentDescription = "category-image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(10.dp)
            )
            Spacer(
                modifier = Modifier
                    .width(10.dp)
            )
            Divider(
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .height(75.dp)
                    .width(1.dp)
                    .align(CenterVertically)
            )
            Text(
                text = categoryItem.strCategory,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically)
            )
        }
    }
}