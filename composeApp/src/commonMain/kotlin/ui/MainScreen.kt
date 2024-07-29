package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eview.common.theme.smallGray
import com.eview.common.theme.smallTheme
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.X0001
import kotlinproject.composeapp.generated.resources.X0002
import kotlinproject.composeapp.generated.resources.ic_home_nor
import kotlinproject.composeapp.generated.resources.ic_home_sel
import kotlinproject.composeapp.generated.resources.ic_more_nor
import kotlinproject.composeapp.generated.resources.ic_more_sel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.BackgroundColor

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val appNavHostController = rememberNavController()

    val navController = rememberNavController() //导航控制器
    val navBackStackEntry by navController.currentBackStackEntryAsState()//导航回退堆栈
    val currentDestination = navBackStackEntry?.destination//当前导航目的地

    val mainPageLists = listOf(MainPage.Home, MainPage.More)

    //监听底部视图切换意图
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                //底部导航切换
                is MainIntent.ClickBottomMenu -> {
                    navController.navigate(event.route) {
                        popUpTo(navController.graph.findStartDestination().route ?: "") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                //跳转历史设备
                is MainIntent.ClickDeviceHistory -> {
                    event.route.let { appNavHostController.navigate(it) }
                }
                //跳转设备类型扫描
                is MainIntent.ClickDeviceItem -> {
                    event.route.let { appNavHostController.navigate(it) }
                }

                is MainIntent.ClickMoreFun -> {
                    event.route.let { appNavHostController.navigate(it) }
                }
            }
        }
    }

    //脚手架
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        //顶部栏
        topBar = {
            TopAppBar(
                title = {},
                modifier = Modifier.size(0.dp),
            )
        },
        //底部栏
        bottomBar = {
            //底部导航栏
            Row (modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .background(BackgroundColor)){
                //绑定底部导航视图
                mainPageLists.forEach { page ->
                    //配置底部导航栏单项UI
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == page.route } == true
                    MainNavigationBarItem(
                        modifier = Modifier.weight(1f),
                        selected = selected,
                        onClick = { viewModel.handleIntent(MainIntent.ClickBottomMenu(page.route)) },
                        iconRes = page.iconRes,
                        iconSelectRes = page.iconSelectRes,
                        nameRes = page.nameRes
                    )
                }
            }

        }) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = MainPage.Home.route,
        ) {
            //主页内容视图
            composable(MainPage.Home.route) { DeviceView(viewModel) }
            //更多内容视图
            composable(MainPage.More.route) { MoreView(viewModel) }
        }
    }
}

@Preview
@Composable
fun MainNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    iconRes: DrawableResource = MainPage.Home.iconRes,
    iconSelectRes: DrawableResource = MainPage.Home.iconSelectRes,
    nameRes: StringResource = MainPage.Home.nameRes,
) {
    Column (modifier.clickable{ onClick() }, horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            modifier = Modifier
                .size(30.dp),
            painter = painterResource(resource = if (selected) iconSelectRes else iconRes),
            contentDescription = stringResource(resource = nameRes),
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(resource = nameRes),
            style = if (selected) smallTheme else smallGray
        )
        Spacer(modifier = Modifier.height(4.dp))

    }
}

/**
 * 主页子view
 */
sealed class MainPage(
    val route: String,//导航路由
    val nameRes: StringResource,//名称
    val iconRes: DrawableResource,//默认图标
    val iconSelectRes: DrawableResource //选中图标
) {
    data object Home : MainPage("home", Res.string.X0001, Res.drawable.ic_home_nor, Res.drawable.ic_home_sel)
    data object More : MainPage("more", Res.string.X0002, Res.drawable.ic_more_nor, Res.drawable.ic_more_sel)
}