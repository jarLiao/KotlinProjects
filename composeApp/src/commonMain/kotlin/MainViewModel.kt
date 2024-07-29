import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ui.MainIntent

class MainViewModel {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun handleIntent(intent: MainIntent2) {
        when (intent) {
            is MainIntent2.ClickFirstPage -> {
                viewModelScope.launch {
                    _navigationEvent.emit(NavigationEvent.FirstPage)
                }
            }
            is MainIntent2.ClickSecondPage -> {
                viewModelScope.launch {
                    _navigationEvent.emit(NavigationEvent.SecondPage)
                }
            }
        }
    }
}

