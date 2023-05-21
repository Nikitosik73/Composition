package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    private val args by navArgs<GameFinishedFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnClickListener()
        bindViews()
    }

    private fun getSmileResId(): Int {
        return if (args.gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    private fun bindViews() {
        with(binding) {
            with(args) {
                emojiResult.setImageResource(getSmileResId())

                tvRequiredAnswers.text = String.format(
                    getString(R.string.required_score),
                    gameResult.gameSettings.minCountOfRightAnswers
                )

                tvScoreAnswers.text = String.format(
                    getString(R.string.score_answers),
                    gameResult.countOfQuestions
                )

                tvRequiredPercentage.text = String.format(
                    getString(R.string.required_percentage),
                    gameResult.gameSettings.minPercentOfRightAnswers
                )

                tvScorePercentage.text = String.format(
                    getString(R.string.score_percentage),
                    getPercentOfRightAnswer()
                )
            }
        }
    }

    private fun getPercentOfRightAnswer() = with(args.gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers.toDouble() / countOfQuestions) * 100).toInt()
        }
    }

    private fun setupOnClickListener() {
        // регагирование на нажатие кнопки назад
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}