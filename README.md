![BreakingBad](https://user-images.githubusercontent.com/58938625/91903261-db80ee00-ec9a-11ea-9298-f74beb959b6c.png)



# BreakingBad
## About
<p>It simply loads Characters data from API and stores it in persistence storage (i.e SQLite Database). Characters will be always loaded from local database. 
  Remote data(from API) and local data is always synchronized. Also it includes Search character by name and Filter Characters by Appearance features. </p>
  <ul>
  <li>This makes it offline capable 😃.</li>
  </ul>

## Project Structure
#### Coroutines
<ul>
  <li>|--viewmodel -> CharacterListViewModel -Used Coroutines launch for call API service and insert data into local database in a background thread. </li>
  <li>|--MyApplication - To start Workmanager job and run in the background thread.</li>
  </ul>
  
#### ---Android Architecture Components---

  #### LiveData 
  
  <ul>
  <li>|--viewmodel ->CharacterListViewModel- LiveData has been used for search, it observes the search items and update the displayed items when search results change  
    It has been used for filter, it observes the filtered items and update the displayed items when result chnage
    It has been used for observe loading set and display progress bar</li>
  <li>|--viewmodel -> CharacterDetailViewModel - Here, LiveData has been used for observe selected Character </li>
  </ul>
  
  #### ViewModel 
  <ul>
  <li>|--viewmodel ->CharacterListViewModel - It is preparing and managing data for display Characterlist, search and filter. </li>
  </li>|--viewmodel ->CharacterDetailViewModel - It is preparing and managing data for display selected character details.</li>
  </ul>
  
  #### Workmanager
  <ul>
  <li>|--background ->SyncDatabaseWM - Workmanager has been used here for periodically syncing application data with server on a background thread.</li>
  </ul>
  
  #### ViewBinding/DataBinding 
  <ul>
  <li>|--CharacterList_Fragment - ViewBinding has been used here for resource layout, recyclerView and searchView.</li>
  <li>Databinding has been used for bind UI components of row_characters.xml and fragment_chardetail.xml with data source.</li>
  </ul>
  
  #### Room 
  <ul>
  <li>|--database -> DatabaseCharacter - Created @Entity for Room Database with expected fields.</li>
  <li>|--database -> Room - Created @Ddatabse &  @Dao which contains all database related queries to manage data with database.
  </ul>
  
  #### Koin
  <ul>
  <li>|--di -> Use Koin to perform API Service module injection, database module injection, network module injection, repository module injection and viewmodel module injection.</li>
  </ul>
  
#### Retrofit 
<ul>
  <li>|--di -> NetworkModule - Retrofit has been used to perform network calls</li>
  </ul>
  
#### GSON
<ul>
  <li>|--di -> NetworkModule - Gson has been used for json data parsing.</li> 
  </ul>
  
#### Glide 
<ul>
  <li>|--util ->BindingAdapters - Here, Glide image loading library has been used to load image into imageview.</li>
  </ul>
  
#### SafeArgs
<ul>
  <li>Here, SafeArgs has been used for passed data of selected Character(CharacterList_Fragment) to CharacterDetail_Fragment.</li>
  </ul>
  
#### Material Components for Android 
<ul>
  <li>Material Cardview has been used to display Characters in RecyclerView. </li>
  </ul>
  
 #### Espresso 
 <ul>
  <li>Espresso has been used for instrumentation testing for check, is Activity Displayed?, is CharacterList_Fragment_Layout is visible?, is RecyclerView visible?, is CharacterData visible?, is ProgressBar is showing?, progressbar is not showing when success.
  </ul>
  
#### Mockito
<ul>
  <li>Mockito has been used for implement Unit Test cases and mock the objects. Test cases has been written for repository to check- character repository retrieves expected data, search retrieves expected data, list is empty when filter not found, filter returns expected values. Test cases has been written for viewmodel to check - when viewmodel is created it gets the data correctly, check for loading state. </li>
  </ul>


## Built With 🛠
<p> Kotlin - First class and official programming language for Android development.</p>
<p>Coroutines - For concurrency and multithreading</p>
<p>Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.</p>
<ul>
  <li>LiveData - Data objects that notify views when the underlying database changes.</li>
  <li>ViewModel - Stores UI-related data that it's survive configuration changes.</li>
  <li>Workmanager - It is for background work that's deferrable and requires guranteed execution.</li>
  <li>ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.</li>
  <li>Room - SQLite object relational mapping library.</li>
  </ul>
<p>Koin - It's a lightweight dependency injection framework for kotlin.<p>
<p>Retrofit - A type-safe HTTP client for Android and Java.</p>
<p>GSON - Gson is a Java library that can be used to convert Java Objects into their JSON representation.<p>
<p>Glide - An image loading library for Android </p>
<p>SafeArgs - To pass data between fragments
<p>Material Components for Android - Modular and customizable Material Design UI components for Android.</p>
<p>Espresso - Espresso is a Testing Framework for Android to make it easy to write reliable user inteface tests.</p>
<p>Mockito - Mockito is a popular mocking library for clean and readable unit tests. 

## Architecture
<p>This app uses MVVM (Model View View-Model) architecture.</p>

![68747470733a2f2f646576656c6f7065722e616e64726f69642e636f6d2f746f7069632f6c69627261726965732f6172636869746563747572652f696d616765732f66696e616c2d6172636869746563747572652e706e67](https://user-images.githubusercontent.com/58938625/91903352-ff443400-ec9a-11ea-8fd0-853d6336bcf4.png)

