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
                    "AA_AA/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewGroupLayout = findViewById(R.id.layoutSeat)

        seatBookView = SeatBookView(this)
            .setSeatSizeBySeatsColumn(5)
            .setSeatGaping(10)
            .setSeatsLayoutString(seats)


        seatBookView.setSeatViewLayout(viewGroupLayout)
        seatBookView.show()

    }


}