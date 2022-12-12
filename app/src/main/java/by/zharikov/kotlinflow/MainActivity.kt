package by.zharikov.kotlinflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.zharikov.kotlinflow.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        lifecycleScope.launch {
            mainViewModel.countDownTimer.collect { countDownTimer ->
                mBinding.textTimer.text = countDownTimer.toString()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}