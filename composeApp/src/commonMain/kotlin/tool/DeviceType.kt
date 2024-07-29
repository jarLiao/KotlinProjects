package tool

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.X0015
import kotlinproject.composeapp.generated.resources.X0016
import kotlinproject.composeapp.generated.resources.X0017
import kotlinproject.composeapp.generated.resources.ds2
import kotlinproject.composeapp.generated.resources.ds3
import kotlinproject.composeapp.generated.resources.ev04
import kotlinproject.composeapp.generated.resources.ev05
import kotlinproject.composeapp.generated.resources.ev06
import kotlinproject.composeapp.generated.resources.ev07b
import kotlinproject.composeapp.generated.resources.ev10
import kotlinproject.composeapp.generated.resources.ev201
import kotlinproject.composeapp.generated.resources.ev201m
import kotlinproject.composeapp.generated.resources.ev206m
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


enum class DeviceType(val typeName: String, val icon: DrawableResource) {
    EV04("EV-04", Res.drawable.ev04),
    EV05("EV-05", Res.drawable.ev05),
    EV06("EV-06", Res.drawable.ev06),
    EV07B("EV-07B", Res.drawable.ev07b),
    EV10("EV-10", Res.drawable.ev10),
    EV201("EV-201", Res.drawable.ev201),
    EV201M("EV-201M", Res.drawable.ev201m),
    EV206M("EV-206M", Res.drawable.ev206m),
    DS2("DS2", Res.drawable.ds2),
    DS3("DS3", Res.drawable.ds3);

    companion object {

        /**
         * 根据设备名称获取设备类型
         * 去除设备名称中的"-"和"_"
         * 如果设备名称以EV201M开头，则直接返回EV201M 避免EV201MA和EV201的判断错误
         * 如果设备名称以其他设备类型开头，则返回对应的设备类型
         * @param deviceName 设备名称
         * @return 设备类型
         */
        fun getDeviceTypeDeviceByName(deviceName: String?): DeviceType {
            return deviceName?.let {
                val name = it.replace("-", "").replace("_", "")
                if (name.startsWith(EV201M.name)) {
                    return EV201M
                }
                DeviceType.values().find { deviceType ->
                    name.startsWith(deviceType.name)
                } ?: EV07B
            } ?: EV07B
        }
    }
}


val personal = listOf(
    DeviceType.EV07B,
    DeviceType.EV04,
    DeviceType.EV05,
    DeviceType.EV10,
    DeviceType.EV06,
)

val pet = listOf(
    DeviceType.EV206M,
    DeviceType.EV201M,
    DeviceType.EV201,
)

val chargingBase = listOf(
    DeviceType.DS2,
    DeviceType.DS3,
)

val deviceTypeMap = mapOf<StringResource, List<DeviceType>>(
    Res.string.X0015 to personal,
    Res.string.X0016 to pet,
    Res.string.X0017 to chargingBase
)
