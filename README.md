# TestApplication - A list demo app

## [Description]：

This is a list showing demo app. 

## [Environments]：

**Android Version** - Later than Android 21  
**Programming Language** - Kotlin  
**Data Injection** - Hilt  
**Framework** - MVVM with Coroutine and LiveData  
**Unit/UI test** - Mockito, JUnit  
**Other Libraries** - Room, Moshi  

## [Structure]：
### [Model]
**source** - Local & Remote  
**repo** - CurrencyRepository  

### [View/ViewModel]
**activity** - MainActivity & MainViewModel  
**fragment** - CurrencyListFragment  
**adapter** - CurrencyInfoAdapter  

## [DEMO]：

### [Get Currency List]
The data is automatically mocked and saved into local DB once the  
app is launch (but not shown on the screen). Press the **[Get]** button  
to load data from DB and show it on the screen.  

### [Sor Currency List]
Press the **[Sort]** button sort the loaded data on the screen.  
