package com.example.weatherapp.ui.screen.onBoarding

import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.weatherapp.R
import com.example.weatherapp.ui.navigation.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

data class OnBoardingData(val image: Int, val title: String, val desc: String)

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    val items = ArrayList<OnBoardingData>()

    items.add(
        OnBoardingData(
            R.raw.welcome,
            "Selamat Datang",
            "Pantau Cuaca (PACU) akan membantumu mempersiapkan aktivitasmu untuk hari ini dan besok"
        )
    )

    items.add(
        OnBoardingData(
            R.raw.get_started,
            "Pahami Cuaca",
            "Jangan biarkan cuaca mengganggu aktivitasmu hari ini, amati cuaca hari kedepan agar aktivitasmu lancar"
        )
    )
    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 1,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5A5A5A)),
        item = items,
        pagerState = pagerState,
        onBoardingViewModel = onBoardingViewModel,
        navController = navController
    )
}

@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    modifier: Modifier = Modifier,
    item: List<OnBoardingData>,
    pagerState: PagerState,
    onBoardingViewModel: OnBoardingViewModel,
    navController: NavHostController
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                Column(
                    modifier = Modifier
                        .padding(60.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoaderIntro(
                        modifier = Modifier
                            .size(300.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally),
                        item[page].image
                    )
                    Text(
                        text = item[page].title,
                        modifier = Modifier.padding(top = 50.dp),
                        style = MaterialTheme.typography.h5.copy(
                            color = Color(0xFFFFFFFF)
                        ),
                    )

                    Text(
                        text = item[page].desc,
                        modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        style = MaterialTheme.typography.body1.copy(
                            color = Color(0xFFFFFFFF)
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
            PagerIndicator(item.size, pagerState.currentPage)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomSection(
                pagerState.currentPage,
                onBoardingViewModel = onBoardingViewModel,
                navController = navController
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun rememberPagerState(
    @androidx.annotation.IntRange(from = 0) pageCount: Int,
    @androidx.annotation.IntRange(from = 0) initialPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
    @androidx.annotation.IntRange(from = 1) initialOffscreenLimit: Int = 1,
    infiniteLoop: Boolean = false
): PagerState = rememberSaveable(
    saver = PagerState.Saver
) {
    PagerState(
        pageCount = pageCount,
        currentPage = initialPage,
        currentPageOffset = initialPageOffset,
        offscreenLimit = initialOffscreenLimit,
        infiniteLoop = infiniteLoop
    )
}

@Composable
fun PagerIndicator(
    size: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
    ) {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) Color.Black else Color.White.copy(alpha = 0.5f)
            )
    )
}

@Composable
fun BottomSection(
    currentPager: Int,
    onBoardingViewModel: OnBoardingViewModel,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (currentPager != 1) Arrangement.SpaceBetween else Arrangement.Center
    ) {
        if (currentPager == 1) {
            OutlinedButton(
                onClick = {
                    onBoardingViewModel.saveOnBoardingState(completed = true)
                    navController.popBackStack()
                    navController.navigate(Screen.Detail.route)
                },
                shape = RoundedCornerShape(50),
            ) {
                Text(
                    text = "Mulai",
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.h6.copy(
                        color = Color(0xFFC0C0C0)
                    )
                )
            }
        }
    }
}

@Composable
fun LoaderIntro(modifier: Modifier, image: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(image))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}