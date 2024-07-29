package ui

import tool.DeviceType
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eview.common.theme.bigWhiteBold
import com.eview.common.theme.blackBold
import com.eview.common.theme.normalWhite
import com.eview.common.theme.smallBlackBold
import com.eview.common.theme.smallNormalWhite
import tool.deviceTypeMap
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.X0008
import kotlinproject.composeapp.generated.resources.X0009
import kotlinproject.composeapp.generated.resources.X0015
import kotlinproject.composeapp.generated.resources.X0019
import kotlinproject.composeapp.generated.resources.bg_home_top
import kotlinproject.composeapp.generated.resources.ic_guide_arrow_right
import kotlinproject.composeapp.generated.resources.ic_history
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tool.personal
import theme.bgEnd
import theme.bgMore
import theme.bgStart

/**
 * 主页内容视图
 */
@Preview
@Composable
fun DeviceView (viewModel : MainViewModel = MainViewModel()) {
    val listState = rememberLazyListState() // 记住滚动位置

//    UpdateStatusBarColor(isDark = false)//更新白色状态栏

    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(bgStart, bgEnd),
                )
            )
    ){
        Image(
            modifier = Modifier.align(Alignment.TopEnd),
            painter = painterResource(resource = Res.drawable.bg_home_top),
            contentDescription = null
        )
    }
    //垂直顺序排列
    Column(modifier = Modifier.fillMaxSize()) {
        //填充状态栏
        Spacer(modifier = Modifier.statusBarsPadding())

        //顶部文字
        Row {
            Column (modifier = Modifier.weight(1.5f)){
                Text(
                    text = stringResource(resource = Res.string.X0008),
                    style = bigWhiteBold,
                    modifier = Modifier.padding(start = 16.dp, top = 32.dp)
                )
                Text(
                    text = stringResource(resource = Res.string.X0009),
                    style = normalWhite,
                    modifier = Modifier.padding(start = 16.dp,top = 4.dp, bottom = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 45.dp, end = 16.dp),
                horizontalAlignment = Alignment.End
            ) {

                IconButton(onClick = { viewModel.handleIntent(MainIntent.ClickDeviceHistory())  }) {
                    Image(
                        painter = painterResource(resource = Res.drawable.ic_history),
                        contentDescription = "device history",
                        modifier = Modifier.size(27.dp)
                    )
                }

                Text(
                    modifier = Modifier.offset(y = (-6).dp),
                    text = stringResource(resource = Res.string.X0019),
                    style = smallNormalWhite,
                )
            }
        }

        //垂直列表
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .clip(shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(color = bgMore),

        ) {

            deviceTypeMap.forEach{
                item { 
                    DeviceSection(title = stringResource(resource = it.key), devices = it.value){
                        viewModel.handleIntent(MainIntent.ClickDeviceItem(it))
                    }
                }
            }
        }
    }
}

/**
 * 设备类型
 */
@Preview
@Composable
fun DeviceSection(
    title: String = stringResource(resource = Res.string.X0015),
    devices: List<DeviceType> = personal,
    onItemClick: (deviceType : DeviceType) -> Unit = {}
) {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(tween(1500), RepeatMode.Reverse),
        label = ""
    )
    val listState = rememberLazyListState()
    val showArrow = remember { mutableStateOf(true) }

    val scrollState = rememberScrollState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                showArrow.value = visibleItems.lastOrNull()?.index != listState.layoutInfo.totalItemsCount - 1
            }
    }
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = title, style = blackBold, modifier = Modifier.padding(16.dp))
        Box(modifier = Modifier.fillMaxWidth()){
            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                devices.forEach {deviceType ->
                    item {
                        DeviceItem(title = deviceType.typeName, icon = deviceType.icon, onClick = {
                            onItemClick(deviceType)
                        })
                    }
                }
            }

            if (showArrow.value) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 2.dp)
                        .size(12.dp)
                        .alpha(alpha.value),
                    painter = painterResource(resource = Res.drawable.ic_guide_arrow_right),
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))
    }
}

/**
 * 设备单项卡片
 */
@Preview
@Composable
fun DeviceItem(
    title: String = DeviceType.EV07B.typeName,
    icon: DrawableResource = DeviceType.EV07B.icon,
    onClick: () -> Unit = {}
) {
    val roundShape = RoundedCornerShape(10.dp)
    Card(
        shape = roundShape,
        modifier = Modifier
            .size(100.dp, 120.dp)
            .clip(roundShape)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 设备图标
            Image(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 5.dp)
                    .size(80.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(resource = icon),
                contentDescription = "device"
            )
            // 设备名称
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = smallBlackBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

