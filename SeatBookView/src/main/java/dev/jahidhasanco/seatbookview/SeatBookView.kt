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
import androidx.core.content.withStyledAttributes


class SeatBookView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    private var viewGroupLayout: ViewGroup
    private var seats = ""

    private var title = listOf<String>()

    private var selectedIdList: ArrayList<Int> = arrayListOf()
    private var bookedIdList: ArrayList<Int> = arrayListOf()
    private var reservedIdList: ArrayList<Int> = arrayListOf()

    private var selectSeatLimit = Int.MAX_VALUE
    private var selectedSeats = 0


    private var isCustomTitle = false
    private var count = 0

    private var seatViewList: ArrayList<TextView> = arrayListOf()
    private var seatSize = 0
    private var seatGaping = 0
    private var layout_padding = 0

    private val STATUS_AVAILABLE = 1
    private val STATUS_BOOKED = 2
    private val STATUS_RESERVED = 3


    //displayMetrics .density ... display size
    private var pxWidth = 0
    private var pxHeight = 0
    private var scaledDensity = 0f
    private var density = 0f

    // seats Drawable
    private var bookDrawable = R.drawable.seats_book
    private var bookedDrawable = R.drawable.seats_booked
    private var reservedDrawable = R.drawable.seats_reserved
    private var selectedDrawable = R.drawable.seats_selected


    // Seat Text
    private var textSize = 0f
    private var reservedTextColor = Color.WHITE
    private var bookTextColor = Color.BLACK
    private var bookedTextColor = Color.WHITE


    private var listener: SeatClickListener? = null
    private var listenerLong: SeatLongClickListener? = null


    init {

        inflate(context, R.layout.layout_view_group, this)
        viewGroupLayout = findViewById(R.id.layout_viewGroup)

        getViewSize()

        if (attrs != null) {
            context.withStyledAttributes(attrs, R.styleable.SeatBookView, 0, 0) {

                textSize =
                    getDimensionPixelSize(
                        R.styleable.SeatBookView_seat_text_size,
                        15
                    ).toFloat() / scaledDensity

                seatSize = (getDimensionPixelSize(
                    R.styleable.SeatBookView_seat_size,
                    250
                ) / density).toInt()

                seatGaping = (getDimensionPixelSize(
                    R.styleable.SeatBookView_seat_gaping,
                    10
                ) / density).toInt()

                reservedDrawable = getResourceId(
                    R.styleable.SeatBookView_reserved_seat_background,
                    reservedDrawable
                )

                bookDrawable =
                    getResourceId(
                        R.styleable.SeatBookView_available_seat_background,
                        bookDrawable
                    )

                bookedDrawable =
                    getResourceId(
                        R.styleable.SeatBookView_booked_seat_background,
                        bookedDrawable
                    )

                selectedDrawable =
                    getResourceId(
                        R.styleable.SeatBookView_selected_seats_background,
                        selectedDrawable
                    )

                reservedTextColor =
                    getColor(
                        R.styleable.SeatBookView_reserved_seats_text_color,
                        reservedTextColor
                    )

                bookTextColor =
                    getColor(
                        R.styleable.SeatBookView_available_seats_text_color,
                        bookTextColor
                    )

                bookedTextColor =
                    getColor(
                        R.styleable.SeatBookView_booked_seats_text_color,
                        bookedTextColor
                    )

                selectSeatLimit = getInt(R.styleable.SeatBookView_seat_select_limit, Int.MAX_VALUE)
            }
        }


    }


    private fun getViewSize() {

        val displayMetrics = context.resources.displayMetrics
        pxWidth = displayMetrics.widthPixels
        pxHeight = displayMetrics.heightPixels
        scaledDensity = displayMetrics.scaledDensity
        density = displayMetrics.density


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
        seatGaping = (size / density).toInt()
        return this
    }

    fun setSeatLayoutPadding(size: Int): SeatBookView {
        layout_padding = (size / density).toInt()
        return this
    }

    fun setSeatSize(size: Int): SeatBookView {
        seatSize = (size / density).toInt()
        return this
    }

    fun setSeatSizeBySeatsColumnAndLayoutWidth(seatsInColumn: Int, parentLayoutWeight: Int) {
        seatSize = if (parentLayoutWeight != -1) {
            (parentLayoutWeight / seatsInColumn) - ((seatGaping * (seatsInColumn - 1)) + (layout_padding * 2))
        } else {
            (pxWidth / seatsInColumn) - ((seatGaping * (seatsInColumn - 1)) + (layout_padding * 2))
        }
    }

    fun setSeatsLayoutString(seats: String): SeatBookView {
        this.seats = seats
        return this
    }


    fun getSelectedIdList(): List<Int> {
        return selectedIdList
    }

    fun getBookedIdList(): List<Int> {
        return bookedIdList
    }

    fun getReservedIdList(): List<Int> {
        return reservedIdList
    }

    fun setBookedIdList(list: List<Int>) {
        for (id in list) {
            markAsBooked(seatViewList[id - 1])
        }
    }

    fun setReservedIdList(list: List<Int>) {
        for (id in list) {
            markAsReserved(seatViewList[id - 1])
        }
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
        textSize = size / scaledDensity
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

    public fun setSeatClickListener(listener: SeatClickListener) {
        this.listener = listener
    }

    public fun setSeatLongClickListener(listener: SeatLongClickListener) {
        this.listenerLong = listener
    }


    fun show() {
        val layoutSeat = LinearLayout(context)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutSeat.orientation = LinearLayout.VERTICAL
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
                val paramsV = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layout.layoutParams = paramsV
                layout.orientation = LinearLayout.HORIZONTAL
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
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setBackgroundColor(Color.TRANSPARENT)
                view.text = ""
                layout!!.addView(view)
            }
        }

    }

    private fun setSeatAttrs(index: Int, view: TextView, layout: LinearLayout?) {
        val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
        view.layoutParams = layoutParams
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

        view.setOnLongClickListener {
            seatLongClick(it)
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
                selectedSeats--

                listener?.onAvailableSeatClick(selectedIdList, view)

            } else {
                if (selectedSeats < selectSeatLimit) {
                    selectedIdList.add(view.id)
                    view.setBackgroundResource(selectedDrawable)
                    selectedSeats++

                    listener?.onAvailableSeatClick(selectedIdList, view)

                }

            }
        } else if (view.tag as Int == STATUS_BOOKED) {
            listener?.onBookedSeatClick(view)

        } else if (view.tag as Int == STATUS_RESERVED) {
            listener?.onReservedSeatClick(view)
        }
    }

    private fun seatLongClick(view: View): Boolean {
        if (view.tag as Int == STATUS_AVAILABLE) {
            listenerLong?.onAvailableSeatLongClick(view)
            return true
        } else if (view.tag as Int == STATUS_BOOKED) {
            listenerLong?.onBookedSeatLongClick(view)
            return true
        } else if (view.tag as Int == STATUS_RESERVED) {
            listenerLong?.onReservedSeatLongClick(view)
            return true
        }
        return false
    }


}


