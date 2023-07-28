package com.example.yumyum.presentation.screens

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.yumyum.R
import com.example.yumyum.presentation.HeadingTextComponent
import com.example.yumyum.presentation.category_list.CategoryListViewModel
import com.example.yumyum.presentation.category_list.components.SingleCategoryItem
import com.example.yumyum.presentation.navigation.Screen

@Composable
fun CategoriesScreen(
    onCategoryClick: (String) -> Unit,
    navController: NavController,
    viewModel: CategoryListViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val stateLazyColumn = rememberLazyListState()
    val firstItemVisible by remember {
        derivedStateOf {
            stateLazyColumn.firstVisibleItemIndex == 0
        }
    }
    val transitionState = remember { MutableTransitionState(firstItemVisible) }

    val transition = updateTransition(
        targetState = transitionState,
        label = "visibilityTransition"
    )
    val rowHeight by transition.animateDp(label = "") {
        if (it.currentState != firstItemVisible) 0.dp else 50.dp
    }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(rowHeight),
                verticalAlignment = CenterVertically,
            ) {
                HeadingTextComponent(
                    text = "Categories",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.account_icon),
                    contentDescription = "profile_icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.ProfileScreen.route)
                        }
                        .size(50.dp)
                )
            }
            LazyColumn(
                state = stateLazyColumn,
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                items(state.categories) { category ->
                    SingleCategoryItem(
                        categoryItem = category,
                        onCategoryItemClick = onCategoryClick
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }

}



