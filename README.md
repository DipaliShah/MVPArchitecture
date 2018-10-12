# android-MVPArchitecture-dagger-sample
This project contains detailed implementation of MVP(Model-View-Presenter) with Dagger2 , Retrofit (Networking Library),RxJava2, ButterKnife,Glide  

The app has following packages:
1. api : API related Retrofit Singleton class and all needed webService call in Interface. 
2. application : Application class.
3. data: It contains all the data accessing and manipulating components.(Data manager and Sharedpreference)
4. di: Dependency providing classes using Dagger2.
5. main: View classes along with their corresponding Presenters.
6. service: Services for the application.
7. NetworkUtils: Utility class.


Classes have been designed in such a way that it could be inherited and maximize the code reuse.
