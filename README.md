# PF_NG_AND_24
Movies Challenge

You'll need Android Studio as an IDE environment to open this project and be able to run it.

### **Important Note**
To run the Project you must provide an API_KEY from that should be pasted in root/apiKey.properties on API_KEY for field. The key can be obtained in https://developer.themoviedb.org/docs/getting-started.

Project should build fine after this initial step.

## Running the App:
As per features request User will find the home page to be a grid list of Popular Movies. A bottom bar navigation that will support navigation. The search feature can be found in this bottom bar. Searching will require input and can be done via both the trail search icon and the keyboard search button. In any listing of Movies those can be pressed for a detailed view of that Movie. The detail view allows the TopAppBar the back feature and hides the bottom navigation as it is part of a different navigation flow. The overview of the movie is limited by default to 3 lines but can be expanded by clicking on it. In this detail view similiar movies, accordingly with the API parameters, can be found and can also be clicked.

## Testing:
Unit Tests were provided for both ViewModels, Paging sources and UseCases of all main features, and can all be found in UnitTest folder.
