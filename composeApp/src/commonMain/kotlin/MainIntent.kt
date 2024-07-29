import ui.MainIntent

sealed class MainIntent2 {
    object ClickFirstPage : MainIntent2()
    object ClickSecondPage : MainIntent2()
}

sealed class NavigationEvent {
    object FirstPage : NavigationEvent()
    object SecondPage : NavigationEvent()
}