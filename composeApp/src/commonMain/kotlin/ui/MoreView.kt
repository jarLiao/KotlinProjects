package ui


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.eview.common.theme.bigBlackBold
import com.eview.common.theme.blackBold
import com.eview.common.theme.normalBlack
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.X0003
import kotlinproject.composeapp.generated.resources.X0004
import kotlinproject.composeapp.generated.resources.X0005
import kotlinproject.composeapp.generated.resources.X0006
import kotlinproject.composeapp.generated.resources.X0007
import kotlinproject.composeapp.generated.resources.X0008
import kotlinproject.composeapp.generated.resources.X0010
import kotlinproject.composeapp.generated.resources.X0566
import kotlinproject.composeapp.generated.resources.ic_faq
import kotlinproject.composeapp.generated.resources.ic_feedback
import kotlinproject.composeapp.generated.resources.ic_language
import kotlinproject.composeapp.generated.resources.ic_log
import kotlinproject.composeapp.generated.resources.ic_terms_service
import kotlinproject.composeapp.generated.resources.ic_version
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.bgMore
import tool.Route


/**
 * 更多功能菜单
 */
@Preview
@Composable
fun MoreView(viewModel: MainViewModel = MainViewModel()) {
    val listState = rememberLazyGridState() // 记住滚动位置

    MoreNavigation(viewModel)

//    UpdateStatusBarColor(isDark = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bgMore)
    ) {
        Spacer(modifier = Modifier.statusBarsPadding())
        Text(
            text = stringResource(resource = Res.string.X0008),
            style = bigBlackBold,
            modifier = Modifier.padding(start = 16.dp, top = 32.dp)
        )

        Text(
            text = stringResource(resource = Res.string.X0010),
            style = normalBlack,
            modifier = Modifier.padding(start = 16.dp,top = 4.dp, bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            state = listState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
//                .overScrollVertical(),
//            flingBehavior = rememberOverscrollFlingBehavior { listState }
        ) {
            morePageList.forEach {
                item {
                    MoreItem(nameRes = it.nameRes, iconRes = it.iconRes) {
                        viewModel.handleIntent(MainIntent.ClickMoreFun(it.route))
                    }
                }
            }
        }
    }
}

@Composable
private fun MoreNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        viewModel.navigationMoreFun.collect { route ->
            route?.let {
                navController.navigate(it)
            }
        }
    }
}

@Preview()
@Composable
fun MoreItem(
    nameRes: StringResource = Res.string.X0003,
    iconRes: DrawableResource = Res.drawable.ic_faq,
    onClick: () -> Unit = {},
) {

    val roundShape = RoundedCornerShape(10.dp)
    var pressOffset by remember { mutableStateOf(Offset.Zero) }
    var isPressed by remember { mutableStateOf(false) }
    var isInCorner by remember { mutableStateOf(false) }

    val animatedRotationX by animateFloatAsState(
        targetValue = if (isPressed) pressOffset.x else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "RotationX"
    )
    val animatedRotationY by animateFloatAsState(
        targetValue = if (isPressed) pressOffset.y else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "RotationY"
    )
    val elevation by animateFloatAsState(
        targetValue = if (isPressed) with(LocalDensity.current){20.dp.toPx()} else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "elevation"
    )
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var pressPosition by remember { mutableStateOf(Offset.Zero) }
    val cornerRegion = with(LocalDensity.current){ 20.dp.toPx() } // 角落区域的大小
    val size = with(LocalDensity.current){ 150.dp.toPx() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    pressPosition = interaction.pressPosition
                    isInCorner = pressPosition.x < cornerRegion || pressPosition.x > size - cornerRegion ||
                                pressPosition.y < cornerRegion || pressPosition.y > size - cornerRegion

                    if (isInCorner) {
                        isPressed = true
                        pressOffset = Offset(
                            x = if (pressPosition.x < size / 2) 35f else -35f,
                            y = if (pressPosition.y < size / 2) 35f else -35f
                        )
                    }
                }
                is PressInteraction.Cancel,
                is PressInteraction.Release -> {
                    isPressed = false
                    pressOffset = Offset.Zero
                }
            }
        }
    }
    Card(
        shape = roundShape,
        modifier = Modifier
            .size(150.dp)
            .clip(roundShape)
            .graphicsLayer {
                rotationX = animatedRotationY / 2
                rotationY = -animatedRotationX / 2
//                shadowElevation = elevation
                cameraDistance = 16f * density
            }
            .clickable(
                indication = if (isInCorner) null else LocalIndication.current,
                interactionSource = interactionSource,
            ) {
                if (!isInCorner) onClick()
            }

        ,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(resource = iconRes),
                contentDescription = stringResource(resource = nameRes)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(resource = nameRes),
                style = blackBold,
                textAlign = TextAlign.Center
            )

        }
    }
}

sealed class MorePage(
    val route: String,
    val nameRes: StringResource,
    val iconRes: DrawableResource
) {
    data object Faq : MorePage(Route.Faq.route, Res.string.X0003, Res.drawable.ic_faq)
    data object Feedback : MorePage(Route.Feedback.route, Res.string.X0004, Res.drawable.ic_feedback)
    data object Language : MorePage(Route.Language.route, Res.string.X0005, Res.drawable.ic_language)
    data object ToolVersion : MorePage(Route.ToolVersion.route, Res.string.X0006, Res.drawable.ic_version)
    data object DeveloperLog : MorePage(Route.DeveloperLog.route, Res.string.X0007, Res.drawable.ic_log)
    data object TermsService : MorePage(Route.TermsService.route, Res.string.X0566, Res.drawable.ic_terms_service)

}

val morePageList = listOf(
    MorePage.Faq,
    MorePage.Feedback,
    MorePage.Language,
    MorePage.ToolVersion,
    MorePage.DeveloperLog,
    MorePage.TermsService,
)