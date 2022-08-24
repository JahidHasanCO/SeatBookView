# SeatBookView
SeatBookView is an Android Studio Library that helps to make it easier to create Bus, Train, Cinema Theater Seat UI and all functionalities are given. [![](https://jitpack.io/v/JahidHasanCO/SeatBookView.svg)](https://jitpack.io/#JahidHasanCO/SeatBookView)


## Preview 
[Home Page](https://github.com/JahidHasanCO/SeatBookView/blob/master/ART/Home.jpg) | 

## Installation
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```sh
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```sh
dependencies {
	        implementation 'com.github.JahidHasanCO:SeatBookView:1.0.2'
	}
```

# Usage

```xml
     <dev.jahidhasanco.seatbookview.SeatBookView
            android:layout_marginTop="10dp"
            android:id="@+id/layoutSeat"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            />
```

```kotlin
    private var seats = (
            "/U___S" +
                    "/_____" +
                    "/AA_AA" +
                    "/UA_AR" +
                    "/AA_AA" +
                    "/RU_AA" +
                    "/AA_AR" +
                    "/AU_AA" +
                    "/AA_AA" +
                    "/AAAAA"
            )
```

```kotlin
    private var title = listOf(
        "/", "I1", "", "", "", "E5",
        "/", "", "", "", "", "",
        "/", "A1", "A2", "", "A3", "A4",
        "/", "B1", "B2", "", "B2", "B4",
        "/", "C1", "C2", "", "C3", "C4",
        "/", "D1", "D2", "", "D3", "D4",
        "/", "E1", "E2", "", "E3", "E4",
        "/", "F1", "F2", "", "F3", "F4",
        "/", "G1", "G2", "", "G3", "G4",
        "/", "H1", "H2", "H3", "H4", "H5"
    )
```


```kotlin
    private lateinit var seatBookView: SeatBookView
    private lateinit var viewGroupLayout: ViewGroup
        
    viewGroupLayout = findViewById(R.id.layoutSeat)
    seatBookView = SeatBookView(this)
        .setSeatGaping(5)
        .setSeatSizeBySeatsColumn(6)
        .setSeatsLayoutString(seats)
        .isCustomTitle(true)
        .setCustomTitle(title)
        .setSelectSeatLimit(2)
        
    seatBookView.setSeatViewLayout(viewGroupLayout)
    seatBookView.show()
```
