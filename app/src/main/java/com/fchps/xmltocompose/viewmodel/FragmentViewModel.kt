import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel



class FragmentViewModel : ViewModel() {

    private var _message = MutableLiveData<String>("")
    val message: LiveData<String> = _message

    fun onMessageChanged(newValue: String) {
        _message.value = newValue
    }
}