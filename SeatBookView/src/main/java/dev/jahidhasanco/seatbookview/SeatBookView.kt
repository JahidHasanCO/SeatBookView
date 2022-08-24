package dev.jahidhasanco.seatbookview


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.withStyledAttributes


class SeatBookView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    private lateinit var viewGroupLayout: ViewGroup
    private var seats = (
            "/UU_RR" +
                    "/AA_AA" +
                    "/UA_AR" +
                    "/AA_AA" +
                    "/AA_AU" +
                    "/RA_AA" +
                    "/AA_AA" +
                    "/AAAAA")

    private var title = listOf<String>()

    private var selectedIdList: ArrayList<Int> = arrayListOf()
    private var selectSeatLimit = Int.MAX_VALUE
    private var selectedSeats = 0

    private var isCustomTitle = false
    private var count = 0

    private var seatViewList: ArrayList<TextView> = arrayListOf()
    private var seatSize = 300
    private var seatGaping = 10
    private var layout_padding = 0

    private val STATUS_AVAILABLE = 1
    private val STATUS_BOOKED = 2
    private val STATUS_RESERVED = 3


    //displayMetrics .density ... display size
    private var pxWidth = 0
    private var dpWidth = 0f
    private var pxHeight = 0
    private var dpHeight = 0f

    // seats Drawable
    private var bookDrawable = R.drawable.seats_book
    private var bookedDrawable = R.drawable.seats_booked
    private var reservedDrawable = R.drawable.seats_reserved
    private var selectedDrawable = R.drawable.seats_selected


    // Seat Text
    private var textSize = 14f
    private var reservedTextColor = Color.WHITE
    private var bookTextColor = Color.BLACK
    private var bookedTextColor = Color.WHITE


    private var listener: SeatClickListener? = null


    init {
        getDisplaySize()
        if (attrs != null) {
            context.withStyledAttributes(attrs, R.styleable.SeatBookView) {

                when {
                    hasValue(R.styleable.SeatBookView_reserved_seat_background) -> {
                        reservedDrawable =
                            getResourceId(
                                R.styleable.SeatBookView_reserved_seat_background,
                                reservedDrawable
                            )
                    }
                }
                when {
                    hasValue(R.styleable.SeatBookView_available_seat_background) -> {
                        bookDrawable =
                            getResourceId(
                                R.styleable.SeatBookView_available_seat_background,
                                bookDrawable
                            )
                    }
                }
                when {
                    hasValue(R.styleable.SeatBookView_booked_seat_background) -> {
                        bookedDrawable =
                            getResourceId(
                                R.styleable.SeatBookView_booked_seat_background,
                                bookedDrawable
                            )
                    }
                }
                when {
                    hasValue(R.styleable.SeatBookView_selected_seats_background) -> {
                        selectedDrawable =
                            getResourceId(
                                R.styleable.SeatBookView_selected_seats_background,
                                selectedDrawable
                            )
                    }
                }

                when {
                    hasValue(R.styleable.SeatBookView_reserved_seats_text_color) -> {
                        reservedTextColor =
                            getColor(
                                R.styleable.SeatBookView_reserved_seats_text_color,
                                reservedTextColor
                            )
                    }
                }

                when {
                    hasValue(R.styleable.SeatBookView_available_seats_text_color) -> {
                        bookTextColor =
                            getColor(
                                R.styleable.SeatBookView_available_seats_text_color,
                                bookTextColor
                            )
                    }
                }

                when {
                    hasValue(R.styleable.SeatBookView_booked_seats_text_color) -> {
                        bookedTextColor =
                            getColor(
                                R.styleable.SeatBookView_booked_seats_text_color,
                                bookedTextColor
                            )
                    }
                }

                when {
                    hasValue(R.styleable.SeatBookView_seat_size) -> {
                        seatSize = getDimensionPixelSize(R.styleable.SeatBookView_seat_size, 300)
                    }
                }

                when {
                    hasValue(R.styleable.SeatBookView_seat_size_by_seats_column) -> {
                        seatSize = (pxWidth / getInt(
                            R.styleable.SeatBookView_seat_size_by_seats_column,
                            5
                        ) + 1) - (layout_padding * seatGaping)

                        Toast.makeText(context, "$seatSize", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }


    private fun getDisplaySize() {
        val displayMetrics = context.resources.displayMetrics
        pxWidth = displayMetrics.widthPixels
        dpWidth = pxWidth / displayMetrics.density
        pxHeight = displayMetrics.heightPixels
        dpHeight = pxHeight / displayMetrics.density
    }

    fun isCustomTitle(r: Boolean): SeatBookView {
        isCustomTitle = r
        return this
    }

    fun setCustomTitle(titles: List<String>): SeatBookView {
        title = titles
        return this
    }

    fun setSelectSeatLimit(limit: Int): SeatBookView {
        selectSeatLimit = limit
        return this
    }


    fun setSeatGaping(size: Int): SeatBookView {
        seatGaping = size
        return this
    }

    fun setSeatLayoutPadding(size: Int): SeatBookView {
        layout_padding = size
        return this
    }

    fun setSeatSize(size: Int): SeatBookView {
        seatSize = size
        return this
    }

    fun setSeatSizeBySeatsColumn(seatsInColumn: Int): SeatBookView {
        seatSize = (pxWidth / seatsInColumn + 1) - (layout_padding * seatGaping)
        return this
    }

    fun setSeatsLayoutString(seats: String): SeatBookView {
        this.seats = seats
        return this
    }

    fun setSeatViewLayout(layout: ViewGroup) {
        viewGroupLayout = layout
    }

    fun getSelectedIdList(): List<Int> {
        return selectedIdList
    }

    fun setAvailableSeatsBackground(drawable: Int): SeatBookView {
        bookDrawable = drawable
        return this
    }

    fun setBookedSeatsBackground(drawable: Int): SeatBookView {
        bookedDrawable = drawable
        return this
    }

    fun setReservedSeatsBackground(drawable: Int): SeatBookView {
        reservedDrawable = drawable
        return this
    }

    fun setSelectedSeatsBackground(drawable: Int): SeatBookView {
        selectedDrawable = drawable
        return this
    }

    fun setSeatTextSize(size: Float) {
        textSize = size
    }


    fun setReservedSeatsTextColor(color: Int): SeatBookView {
        reservedTextColor = color
        return this
    }

    fun setAvailableSeatsTextColor(color: Int): SeatBookView {
        bookTextColor = color
        return this
    }

    fun setBookedSeatsTextColor(color: Int): SeatBookView {
        bookedTextColor = color
        return this
    }

    fun getSeatView(id: Int): View {
        return seatViewList[id - 1]
    }

    fun show() {
        val layoutSeat = LinearLayout(context)
        val params = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutSeat.orientation = VERTICAL
        layoutSeat.layoutParams = params
        layoutSeat.setPadding(
            layout_padding * seatGaping,
            layout_padding * seatGaping,
            layout_padding * seatGaping,
            layout_padding * seatGaping
        )
        viewGroupLayout.addView(layoutSeat)

        var layout: LinearLayout? = null
        for (index in seats.indices) {
            if (seats[index] == '/') {
                layout = LinearLayout(context)
                val paramsV = LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layout.layoutParams = paramsV
                layout.orientation = HORIZONTAL
                layout.gravity = Gravity.CENTER

                layoutSeat.addView(layout)
            } else if (seats[index] == 'U') {
                count++
                val view = TextView(context)
                setSeatAttrs(index, view, layout)
                markAsBooked(view)

            } else if (seats[index] == 'A') {
                count++
                val view = TextView(context)
                setSeatAttrs(index, view, layout)
                markAsAvailable(view)
            } else if (seats[index] == 'R') {
                count++
                val view = TextView(context)
                setSeatAttrs(index, view, layout)
                markAsReserved(view)


            } else if (seats[index] == 'S') {
                count++
                val view = TextView(context)
                setSeatAttrs(index, view, layout)
                markAsTransparentSeat(view)
            } else if (seats[index] == '_') {
                val view = TextView(context)
                val layoutParams = LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setBackgroundColor(Color.TRANSPARENT)
                view.text = ""
                layout!!.addView(view)
            }
        }

    }

    private fun setSeatAttrs(index: Int, view: TextView, layout: LinearLayout?) {
        val layoutParams = LayoutParams(seatSize, seatSize)
        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
        view.layoutParams = layoutParams
        view.setPadding(0, 0, 0, 2 * seatGaping)
        view.gravity = Gravity.CENTER
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        seatViewList.add(view)
        layout!!.addView(view)
        view.id = count
        if (isCustomTitle) {
            view.text = title[index]
        } else {
            view.text = "$count"
        }

        view.setOnClickListener {
            seatClick(it)
        }

    }

    fun markAsBooked(view: TextView) {
        view.setBackgroundResource(bookedDrawable)
        view.setTextColor(bookedTextColor)
        view.tag = STATUS_BOOKED
    }

    fun markAsAvailable(view: TextView) {
        view.setBackgroundResource(bookDrawable)
        view.setTextColor(bookTextColor)
        view.tag = STATUS_AVAILABLE
    }

    fun markAsReserved(view: TextView) {
        view.setBackgroundResource(reservedDrawable)
        view.setTextColor(reservedTextColor)
        view.tag = STATUS_RESERVED
    }

    fun markAsTransparentSeat(view: TextView) {
        view.setBackgroundColor(Color.TRANSPARENT)
        view.text = ""
        view.tag = 0
    }

    private fun seatClick(view: View) {
        if (view.tag as Int == STATUS_AVAILABLE) {
            if (view.id in selectedIdList) {
                selectedIdList.remove(view.id)
                view.setBackgroundResource(bookDrawable)
                listener!!.onAvailableSeatClick(selectedIdList, view)
                selectedSeats--
            } else {
                if (selectedSeats < selectSeatLimit) {
                    selectedIdList.add(view.id)
                    view.setBackgroundResource(selectedDrawable)
                    listener!!.onAvailableSeatClick(selectedIdList, view)
                    selectedSeats++
                    Toast.makeText(
                        context,
                        "You Can Not select Seat more than $selectSeatLimit",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        } else if (view.tag as Int == STATUS_BOOKED) {
            listener!!.onBookedSeatClick(view)
        } else if (view.tag as Int == STATUS_RESERVED) {
            listener!!.onReservedSeatClick(view)
        }
    }

    fun setSeatClickListener(listener: SeatClickListener) {
        this.listener = listener
    }

    interface SeatClickListener {
        fun onAvailableSeatClick(selectedIdList: List<Int>, view: View)
        fun onBookedSeatClick(view: View)
        fun onReservedSeatClick(view: View)
    }


}


