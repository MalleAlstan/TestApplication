# TestApplication - A list demo app

## [Functions]：

This app is a list showing prototype app. 

## [Environments]：

Android Version：> Android 21
Programming Language：Kotlin  
DI：Hilt
Framework：MVVM with Coroutine, Flow and LiveData
Unit/UI test: Mockito, JUnit  
Other Libraries: Room, Moshi

## [Structure]：
### [Model]
**source** Local & Remote
**repo** CurrencyRepository

### [View/ViewModel]
**activity** MainActivity & MainViewModel  
**fragment** CurrencyListFragment
**adapter** CurrencyInfoAdapter

### [Utils]
**util** 

## [DEMO]：

### [Get Currency List]
The data is automatically mocked and saved into local db once the 
app is launch (but not shown on the screen). Press the **[Get]** button
to load and show data on the screen.

### [Sor Currency List]
Press the **[Sort]** button sort the loaded data on the screen.