package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import tool.DeviceType
import tool.Route


/**
 * 主页操作逻辑
 * 1. 底部导航栏点击跳转
 * 2. 设备历史点击跳转
 * 3. 设备类型点击跳转
 * 4. 更多功能点击跳转
 */
class MainViewModel : ViewModel() {
    //跳转事件
    private val _navigationEvent = MutableSharedFlow<MainIntent>()
    val navigationEvent = _navigationEvent.asSharedFlow()
    //跳转更多功能
    private val _navigationMoreFun = MutableSharedFlow<String?>()
    val navigationMoreFun = _navigationMoreFun.asSharedFlow()

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    /**
     * 处理UI操作意图
     */
    fun handleIntent(mainIntent: MainIntent){
        when (mainIntent) {

            is MainIntent.ClickBottomMenu ->{
                viewModelScope.launch{ _navigationEvent.emit(mainIntent) }
            }

            is MainIntent.ClickDeviceHistory ->{

            }
            is MainIntent.ClickDeviceItem -> {
                when (mainIntent.deviceType) {
                    DeviceType.EV07B,
                    DeviceType.EV04,
                    DeviceType.EV05,
                    DeviceType.EV10,
                    DeviceType.EV06,
                    DeviceType.EV201,
                    DeviceType.EV201M,
                    DeviceType.EV206M-> {
//                        SharedViewModel.saveShowDeviceGuide(true)
//                        SharedViewModel.saveNeedScanDeviceType(mainIntent.deviceType)
//                        viewModelScope.launch{ _navigationEvent.emit(mainIntent) }
                    }
                    else -> {
//                        toastUtils.show(Res.string.X0485)
                    }
                }
            }

            is MainIntent.ClickMoreFun -> {
//                viewModelScope.launch{ _navigationMoreFun.emit(mainIntent.route) }
            }
        }
    }
}

/**
 * 主页操作意图事件
 */
sealed class MainIntent{
    data class ClickBottomMenu(val route: String): MainIntent()//点击底部导航栏
    data class ClickDeviceHistory(val route: String = Route.DeviceHistory.route): MainIntent()//点击连接设备历史
    data class ClickDeviceItem(val deviceType: DeviceType, val route: String = Route.DeviceScan.route): MainIntent()//点击设备类型
    data class ClickMoreFun(val route: String) : MainIntent()//点击更多功能
}

