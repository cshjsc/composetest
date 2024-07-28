package com.example.fbimostwanted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.fbimostwanted.ui.theme.FbimostwantedTheme
import com.example.fbimostwanted.ui.HomeViewModel
import com.example.fbimostwanted.ui.nav.NavigationDestination
import com.example.fbimostwanted.ui.nav.NavigationGraph
import kotlinx.serialization.Serializable

@Serializable
object  Home

//object HomeDestination : NavigationDestination {
//    override val route = "home"
//    override val titleRes = R.string.app_name
//}
//
//object DetailDestination : NavigationDestination {
//    override val route = "detail"
//    override val titleRes = R.string.detail
//}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FbimostwantedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    NavigationGraph(navController = rememberNavController(),
                        modifier = Modifier.padding(padding))
                    //HomeScreen(Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetails : (PersonItem)-> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val state by viewModel.state
    val page = viewModel.page
    HomeScreenUI(state, page,
        {viewModel.decreasePage()},
        {viewModel.advancePage()},
        navigateToDetails,
        modifier)
}

@Composable
private fun HomeScreenUI(
    state: WantedList,
    page: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    navigateToDetails: (PersonItem) -> Unit,
    modifier: Modifier
) {
    Box(modifier.padding(bottom = 25.dp)) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.most_wanted_title))
            Text(text = "Page: " + page)
            WantedGrid(state, navigateToDetails, modifier)
        }
        PageAction(
            page,
            onLeftClick = onLeftClick,
            onRightClick = onRightClick,
            modifier = Modifier
                // .padding(0.dp)
                .align(Alignment.BottomCenter)
                .wrapContentWidth()
                .wrapContentHeight()
        )
    }
}

@Composable
fun WantedGrid(
    wanted: WantedList,
    navigateToDetails: (PersonItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(wanted.items) { item ->
            PersonUIItem(item, navigateToDetails)
        }
    }

}

@Composable
fun PageAction(
    page: Int, modifier: Modifier,
    shape: Shape = FloatingActionButtonDefaults.largeShape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    onLeftClick : () -> Unit,
    onRightClick : () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        shape = shape,
        tonalElevation = 6.dp,
    ) {
        Row(
            Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onLeftClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = page.toString(), style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(onRightClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Back",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PageActionPreview() {
    FbimostwantedTheme {
        Box {
            PageAction(
                1,
                onLeftClick = {},
                onRightClick = {},
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.BottomCenter)
                    .wrapContentWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PersonUIItem(personItem: PersonItem,
                 navigateToDetails: (PersonItem) -> Unit,
                 modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        onClick = {navigateToDetails(personItem)},
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Column(
            modifier = modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            personItem.imageBaseUrl?.let {
                GlideImage(
                    model = it,
                    contentDescription = "a",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(20.dp)),
                )
            } ?: Icon(
                imageVector = Icons.Default.Person, contentDescription = personItem.name,
                Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.name) + ":" + personItem.name,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.gender) + ": " + personItem.gender,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.race) + ": " + personItem.race,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FbimostwantedTheme {
        Greeting("Android")
    }
}


@Preview(showBackground = true)
@Composable
fun PersonUIItemPreview() {
    FbimostwantedTheme {
        PersonUIItem(personItem = fake_items[0], {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FbimostwantedTheme {
        HomeScreenUI(state = WantedList(fake_items), 1,
            {},
            {},
            {},
            Modifier)
        //WantedGrid(
        //    WantedList(fake_items)
        //)
    }
}

val fake_items = listOf(
    PersonItem(
        "suspect martinez",
//    "Mexico",
        "Hispanian",
        "100kg",
        "Conspiracy to Commit Hobbs Act Robbery; Firearms Offenses",
        "Male",
        null
    ),
    PersonItem(
        "suspect martinez",
        //  "Mexico",
        "Hispanian",
        "100kg",
        "Conspiracy to Commit Hobbs Act Robbery; Firearms Offenses",
        "Male",
        "https://www.fbi.gov/wanted/cyber/emilio-jose-corredor-lopez/@@images/image"
    ),
    PersonItem(
        "suspect martinez",
        //   "Mexico",
        "Hispanian",
        "100kg",
        "Conspiracy to Commit Hobbs Act Robbery; Firearms Offenses",
        "Male",
        "https://www.fbi.gov/wanted/cyber/emilio-jose-corredor-lopez/@@images/image"
    ),
    PersonItem(
        "suspect martinez",
        //  "Mexico",
        "Hispanian",
        "100kg",
        "Conspiracy to Commit Hobbs Act Robbery; Firearms Offenses",
        "Male",
        "https://www.fbi.gov/wanted/cyber/emilio-jose-corredor-lopez/@@images/image"
    ),
    PersonItem(
        "suspect martinez",
        // "Mexico",
        "Hispanian",
        "100kg",
        "Conspiracy to Commit Hobbs Act Robbery; Firearms Offenses",
        "Male",
        "https://www.fbi.gov/wanted/cyber/emilio-jose-corredor-lopez/@@images/image"
    )
)
