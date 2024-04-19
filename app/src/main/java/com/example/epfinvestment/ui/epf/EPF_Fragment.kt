package com.example.epfinvestment.ui.epf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.epfinvestment.R
import com.example.epfinvestment.databinding.FragmentEPFBinding
import java.text.DateFormat
import java.time.LocalDate
import java.time.Year
import java.util.Calendar
import java.util.Date


/**
 * A simple [Fragment] subclass.
 * Use the [EPF_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EPF_Fragment : Fragment()
{
    private var _binding: FragmentEPFBinding? = null
    private val binding get() = _binding!!
    var age:Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEPFBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Write code to handle events here

        binding.buttonDOB.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager
            val calendar = Calendar.getInstance().time
            val dateFormat = DateFormat.getDateInstance().format(calendar)

            var CurrentYear = 0
            var UserBirthYear = 0



            //Insert Fragment Result handler
            supportFragmentManager.setFragmentResultListener("REQUEST_DATE", viewLifecycleOwner){resultKey, bundle->
                if(resultKey == "REQUEST_DATE")
                {
                    val year = bundle.getInt("YEAR")
                    UserBirthYear = bundle.getInt("YEAR")

                    val month = bundle.getInt("MONTH")
                    val day = bundle.getInt("DAY")

                    binding.buttonDOB.text = String.format("%d-%d-%d", day, month, year)

                    //TODO: Calculate the age, determine the basic account balance
                    CurrentYear = Calendar.getInstance().get(Calendar.YEAR)
                    age = CurrentYear - UserBirthYear
                    binding.textViewAge.text = age.toString()
                }
            }
            datePickerFragment.show(supportFragmentManager, "REQUEST_DATE")
            //binding.textViewAge.text = String.format("%d", age)

        }

        binding.buttonCalculate.setOnClickListener {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}