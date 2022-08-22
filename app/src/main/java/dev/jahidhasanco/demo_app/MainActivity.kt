package dev.jahidhasanco.demo_app


import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import dev.jahidhasanco.seatbookview.SeatBookView


class MainActivity : AppCompatActivity() {

    private lateinit var seatBookView: SeatBookView
    private lateinit var viewGroupLayout: ViewGroup
    private var seats = (
            "UU_RR/"+
                    "AA_AA/"+
                    "UA_AR/"+
                    "AA_AA/"+
                    "AA_AU/"+
                    "RA_AA/"+
                    "AA_AA/"+
                    "AAAAA/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seatBookView = SeatBookView(this)
            .setSeatSize(100)
            .setSeatGaping(10)
            .setSeatsLayoutString(seats)


        seatBookView.setSeatViewLayout(viewGroupLayout)

    }


}